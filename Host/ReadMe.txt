插件化Demo运行成功注意  这里因为有版本限制 4.4编译能行 5.0编译不行所以
1.保证 build.gradle如下   compileSdkVersion 19 buildToolsVersion "19.1.0"
2.classpath 'com.android.tools.build:gradle:1.5.0' 改为1.5.0 或者1.3.0
这两点才可以运行插件化成功

只要学的好 自定义ClassLoader即可 就不用管它版本限制了

运行办法 先安装 plugin 的apk在安装Host 的apk即可发现调用已安装apk成功
调用未安装看Demo https://github.com/penghaoyou5/dynamic-load-api.git