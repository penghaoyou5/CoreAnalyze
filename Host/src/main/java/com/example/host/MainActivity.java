package com.example.host;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useDexClassLoader2();
            }
        });
    }



    public void useDexClassLoader(){
        Intent intent = new Intent("com.example.host.plugin",null);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> rInfos = pm.queryIntentActivities(intent, 0);
        ResolveInfo resolveInfo = rInfos.get(0);
        ActivityInfo activityInfo = resolveInfo.activityInfo;


        String div = System.getProperty("path.separator");
        String packageName = activityInfo.packageName;
        String sourceDir = activityInfo.applicationInfo.sourceDir;


        String dataDir = getApplicationInfo().dataDir;
        String nativeLibraryDir = activityInfo.applicationInfo.nativeLibraryDir;

//        PathClassLoader pathClassLoader = new PathClassLoader(sourceDir,getClassLoader());
        DexClassLoader dexClassLoader = new DexClassLoader(sourceDir,dataDir,null,this.getClass().getClassLoader());


        try {
//            Class<?> aClass = pathClassLoader.loadClass(packageName + ".PluginClass");
//            Class<?> aClass = dexClassLoader.loadClass("com.example.host.plugin.PluginClass");

            Class<?> aClass = dexClassLoader.loadClass("com.example.host.plugin.MainActivity");

            Object o = aClass.newInstance();

            Class[] params = new Class[2];
            params[0]=int.class;
            params[1] = int.class;

            Method function = aClass.getMethod("function", params);
            int result = (int) function.invoke(aClass,3,5);
            Toast.makeText(getApplicationContext(),result+"",Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static String TAG = MainActivity.class.getSimpleName();
    private Context mContext = null;
    public void useDexClassLoader2(){
        mContext = this;
        Intent intent = new Intent();
        intent.setPackage("com.example.plugin");
        PackageManager pm = mContext.getPackageManager();
        final List<ResolveInfo> plugins = pm.queryIntentActivities(intent,0);
        if(plugins.size() <= 0){
            Log.i(TAG, "resolve info size is:" + plugins.size());
            return;
        }
        ResolveInfo resolveInfo = plugins.get(0);
        ActivityInfo activityInfo = resolveInfo.activityInfo;

        String div = System.getProperty("path.separator");
        String packageName = activityInfo.packageName;
        String dexPath = activityInfo.applicationInfo.sourceDir;
        //目标类所在的apk或者jar的路径，class loader会通过这个路径来加载目标类文件
        String dexOutputDir = mContext.getApplicationInfo().dataDir;
        dexOutputDir = getDir("dex",0).getAbsolutePath();

        //由于dex文件是包含在apk或者jar文件中的,所以在加载class之前就需要先将dex文件解压出来，dexOutputDir为解压路径
        String libPath = activityInfo.applicationInfo.nativeLibraryDir;
        //目标类可能使用的c或者c++的库文件的存放路径

        Log.i(TAG, "div:" + div + "   " +
                "packageName:" + packageName + "   " +
                "dexPath:" + dexPath + "   " +
                "dexOutputDir:" + dexOutputDir + "   " +
                "libPath:" + libPath);

        dexPath = "/mnt/sdcard/Host/plugin.apk";

        DexClassLoader dcLoader = new DexClassLoader(dexPath,dexOutputDir,null,this.getClass().getClassLoader());
        try {
            Class<?> clazz;
//            = dcLoader.loadClass(packageName + ".PluginClass");
            clazz = dcLoader.loadClass("com.example.plugin.PluginClass");
            Object obj = clazz.newInstance();
            Class[] param = new Class[1];
            param[0] = String.class;
            Method action = clazz.getMethod("invoke", param);
            action.invoke(obj, "test this function");
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "ClassNotFoundException");
        } catch (InstantiationException e) {
            Log.i(TAG, "InstantiationException");
        } catch (IllegalAccessException e) {
            Log.i(TAG, "IllegalAccessException");
        } catch (NoSuchMethodException e) {
            Log.i(TAG, "NoSuchMethodException");
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "IllegalArgumentException");
        } catch (InvocationTargetException e) {
            Log.i(TAG, "InvocationTargetException");
        }
    }

}
