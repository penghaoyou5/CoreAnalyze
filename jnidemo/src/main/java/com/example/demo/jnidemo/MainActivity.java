package com.example.demo.jnidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.TouchUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String string = new Foo().get_String();
        Toast.makeText(getApplicationContext(),string,Toast.LENGTH_LONG).show();
    }
}
