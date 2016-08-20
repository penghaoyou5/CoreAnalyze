package com.example.demo.jnidemo;

import android.widget.Toast;

/**
 * Created by bjhl on 16/8/20.
 */
public class FooCDiaoJava {

    public static void cDiaoJavaJingTaijavaMethod(String str){
        showToast("c调用java静态方法"+str);
    }


    native void cDiaojavaJingTai();

    native void javaDiaoC1();

    native void javaDiaoC2();

    native void javaDiaoC3();

    public void cDiaoJava1(){
       showToast("这是java调用c语言");
    }

    public void cDiaoJava2(String str){
        showToast("这是java调用c语言 有参数"+str);
    }

    public String cDiaoJava3(int i){
        String result = ""+Math.pow(i,2);
        showToast("这是java调用c语言 有返回值"+result);
        return result;
    }

    public static void showToast(String str){
        Toast.makeText(MainActivity.activity,str,Toast.LENGTH_LONG).show();
    }
}
