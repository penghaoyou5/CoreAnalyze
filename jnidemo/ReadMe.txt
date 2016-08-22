java访问C  具体看代码
1.新建类Foo
2.打开Terminal命令客户端 进入java文件层级  执行命令 javah -d ../jni -jni com.example.demo.jnidemo.Foo 生成.h头文件 (-d后面的参数是目标.h头文件存放目录 因该与在main目录下 -jni后面是源文件路径)
3.在/mian/jni目录先新建.c文件 先导入刚才生成的头文件 然后写C代码
4.在 build.gradle 文件中 defaultConfig 标签下加上ndk配置(就像原来的Android.mk文件) 然后build project即可安装运行

简书总结:http://www.jianshu.com/p/3f9b83297fd3

总结就是
1.写java文件写本地方法  并build编译.class文件
2.通过javah命令生成.h头文件
3.写.c的c文件(或者.cpp的c++文件)
4.配置ndk-build  与在build.gradle中进行相应ndk的配置
5.完成编译
        java native方法-->.h头文件-->.c文件-->编译成so文件


//===================================================
在jni的c语言中(不是c++语言)方法调用写法是
返回值类型 返回值 =  (*env)->方法名(env,其他参数);
具体看代码
//===================================================
