package com.anwesome.ui.leannavigationaldrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.games.leandrawer.LeanBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeanBar leanBar = LeanBar.newInstance(this);
        leanBar.show();
    }
}
