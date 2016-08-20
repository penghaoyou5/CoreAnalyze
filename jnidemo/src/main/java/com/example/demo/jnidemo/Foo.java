package com.example.demo.jnidemo;

/**
 * Created by bjhl on 16/8/20.
 */
public class Foo {

    public Foo(){
        System.loadLibrary("JniTest");
    }

    native void foo1();
    native void foo2(int a,String b);
    native String get_String();
}
