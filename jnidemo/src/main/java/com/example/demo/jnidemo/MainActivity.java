package com.example.demo.jnidemo;

import android.content.pm.ApplicationInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static MainActivity activity;

    @IdRes
    private int[] resIds = {R.id.javaDiaoc,R.id.cDiaojava1,R.id.cDiaojava2,R.id.cDiaojava3,R.id.cDiaojavajingtai,R.id.cDiaojavajfeijingtai};//点击事件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        init();
    }

    private void init(){
        initView();
        initClick();
    }

    private void initView(){
    }

    private void initClick(){
        for (int id:resIds) {
            findViewById(id).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        Foo foo = new Foo();
        FooCDiaoJava fooCdiaoJava = new FooCDiaoJava();
        switch (v.getId()){
            case R.id.javaDiaoc:
                String string = foo.get_String();
                FooCDiaoJava.showToast(string);
                break;
            case R.id.cDiaojavajingtai:
                fooCdiaoJava.cDiaojavaJingTai();
                break;
            case R.id.cDiaojava1:
                fooCdiaoJava.javaDiaoC1();
                break;
            case R.id.cDiaojava2:
                fooCdiaoJava.javac2();
                break;
            case R.id.cDiaojava3:
                fooCdiaoJava.javaDiaoC3();
                break;
            case R.id.cDiaojavajfeijingtai:
                fooCdiaoJava.cNewObjectDiaoJava();
                break;
        }
    }
}
