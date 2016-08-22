//
// Created by bjhl on 16/8/20.
//
#include "com_example_demo_jnidemo_FooCDiaoJava.h"
#include "jni.h"

/**
 * c调用java静态方法成功
 *
 * 命令 javap -s FooCDiaoJava  获取方法参数的签名  (其中-s后的.java对象)
 */
JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_cDiaojavaJingTai
        (JNIEnv *env, jobject jobject1){
     //获取类Class对象
     jclass clazz = (*env)->FindClass(env,"com/example/demo/jnidemo/FooCDiaoJava");

     //获取java函数的id值
     jmethodID id = (*env)->GetStaticMethodID(env,clazz,"cDiao","(Ljava/lang/String;)V");

     //定义参数
     jstring msg = (*env)->NewStringUTF(env,"jniCodeDiao.c calljavaMethod");

     //进行方法的调用
     (*env)->CallStaticVoidMethod(env,clazz,id,msg);
}


/**
 * c调用java中的非静态方法  无参也无返回值
 * 直接通过参数中的对象
 */
JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_javaDiaoC1
        (JNIEnv *env, jobject obj){
     //获得obj对象
     jclass cla = (*env)->GetObjectClass(env,obj);

     jmethodID id = (*env)->GetMethodID(env,cla,"cDiaoJava1","()V");

//     (*env)->CallVoidMethod(env,cla,id);   这种写法是错误的 我把jobject对象传错了
     (*env)->CallVoidMethod(env,obj,id);
}

/**
 * c调用java中的非静态方法 有参 无返回值
 */
JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_javaDiaoC2
        (JNIEnv *env, jobject obj){
     jclass cla = (*env)->GetObjectClass(env,obj);
     jmethodID id = (*env)->GetMethodID(env,cla,"cDiaoJava2","(Ljava/lang/String;)V");
     jstring str = (*env)->NewStringUTF(env,"c的参数");
     (*env)->CallVoidMethod(env,obj,id,str);
}

/**
 * 这是java调用c语言有返回值有参数
 */
JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_javaDiaoC3
        (JNIEnv *env, jobject obj){
     //得到传入对对象的Class对象
     jclass clazz = (*env)->GetObjectClass(env,obj);
     jmethodID methodId = (*env)->GetMethodID(env,clazz,"cDiaoJava3","(I)Ljava/lang/String;");
     jstring str = (*env)->CallObjectMethod(env,obj,methodId,2);
     //经测返回值可用
}


/**
 * c通过new对象的方式调用java的非静态方法
 */
JNIEXPORT void JNICALL Java_com_example_demo_jnidemo_FooCDiaoJava_cNewObjectDiaoJava
        (JNIEnv *env, jobject obj){

     //C 中映射 类
     jclass clazz = (*env)->FindClass(env,"com/example/demo/jnidemo/FooCDiaoJava");
     //C中新建对象
     jmethodID construction_id  = (*env)->GetMethodID(env,clazz,"<init>","()V");
     jobject javaObj = (*env)->NewObject(env,clazz,construction_id);
     //C 中映射非静态方法
     jmethodID foocdjanewobj = (*env)->GetMethodID(env,clazz,"cNewObjDJ","()V");

     //C 中调用 Java非静态 的 方法
     (*env)->CallVoidMethod(env,javaObj,foocdjanewobj);
}

