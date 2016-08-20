//
// Created by bjhl on 16/8/20.
//
#include <com_example_demo_jnidemo_Foo.h>
#include <jni.h>

JNIEXPORT jstring JNICALL Java_com_example_demo_jnidemo_Foo_get_1String
  (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env,"HelloFrom Jni");
  }

