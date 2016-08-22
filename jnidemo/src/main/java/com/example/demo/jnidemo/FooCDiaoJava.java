package com.example.demo.jnidemo;

import android.widget.Toast;

/**
 * Created by bjhl on 16/8/20.
 */
public class FooCDiaoJava {

    /**
     * 提供给c调用的静态方法
     * @param str
     */
    public static void cDiao(String str){
        showToast("c调用java静态方法"+str);
    }


    native void cDiaojavaJingTai();

    native void javaDiaoC1();

    public void javac2(){
        javaDiaoC2();
    }

    native void javaDiaoC2();

    native void javaDiaoC3();

    /**
     * 让c调用时同时反调java方法
     * 对应cNewObjectDiaoJava
     */
    native void cNewObjectDiaoJava();

    public void cDiaoJava1(){
       showToast("这是java调用c语言 cDiaoJava1");
    }

    public void cDiaoJava2(String str){
        showToast("这是java调用c语言2 有参数"+str);
    }

    public String cDiaoJava3(int i){
        String result = ""+Math.pow(i,2);
        showToast("这是java调用c语言 有返回值"+result);
        return result;
    }

    public static void showToast(String str){
        Toast.makeText(MainActivity.activity,str,Toast.LENGTH_LONG).show();
    }

    /**
     * 让c通过new对象调用的非静态方法对应
     * cNewObjectDiaoJava
     */
    public void cNewObjDJ(){
        showToast("c通过new对象的方式调用函数");
    }
}
