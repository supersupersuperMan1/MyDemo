package com.abupdate.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test1() {
        System.out.println("1111");
    }

    public void test2() {
        System.out.println("22222");
    }

    public void test3() {
        System.out.println("bbbbbbbb");
    }
}
