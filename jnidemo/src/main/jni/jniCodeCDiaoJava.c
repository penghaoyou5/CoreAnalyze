//
// Created by bjhl on 16/8/20.
//
#include "com_example_demo_jnidemo_FooCDiaoJava.h"
#include "jni.h"

JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_cDiaojavaJingTai
        (JNIEnv *env, jobject jobject1){
     jclass clazz = (*env)->FindClass(env,"com/example/demo/jnidemo/FooCDiaoJava");

     jmethodID id = (*env)->GetStaticMethodID(env,clazz,"cDiaoJavaJingTaijavaMethod","([Ljava/lang/String;)V");

     jstring msg = (*env)->NewStringUTF(env,"jniCodeDiao.c calljavaMethod");

     (*env)->CallStaticVoidMethod(clazz,id,msg);
}



JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_javaDiaoC1
        (JNIEnv *env, jobject obj){

}
