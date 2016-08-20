package com.example.plugin;

import android.util.Log;

/**
 * Created by bjhl on 16/8/18.
 */
public class PluginClass {

    public PluginClass(){
        Log.i("Plugin","PluginClass client initialized");
    }

    public int function(int a ,int b){
        return a+a+b;
    }

}
