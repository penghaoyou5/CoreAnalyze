package com.example.host;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useDexClassLoader();
            }
        });
    }



    public void useDexClassLoader(){
        Intent intent = new Intent("com.example.plugin",null);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> rInfos = pm.queryIntentActivities(intent, 0);
        ResolveInfo resolveInfo = rInfos.get(0);
        ActivityInfo activityInfo = resolveInfo.activityInfo;


        String div = System.getProperty("path.separator");
        String packageName = activityInfo.packageName;
        String sourceDir = activityInfo.applicationInfo.sourceDir;


        String dataDir = getApplicationInfo().dataDir;
        String nativeLibraryDir = activityInfo.applicationInfo.nativeLibraryDir;

        DexClassLoader dexClassLoader = new DexClassLoader(sourceDir,dataDir,nativeLibraryDir,this.getClass().getClassLoader());

        try {
            Class<?> aClass = dexClassLoader.loadClass("com.example.plugin.PluginClass");


            Object obj = aClass.newInstance();

            Class[] params = new Class[2];
            params[0]=int.class;
            params[1] = int.class;

            Method function = aClass.getMethod("function", params);
            int result = (Integer) function.invoke(obj,3,5);
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

}
