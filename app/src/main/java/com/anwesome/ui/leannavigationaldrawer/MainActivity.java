package com.anwesome.ui.leannavigationaldrawer;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.games.leandrawer.DrawerMenu;
import com.anwesome.games.leandrawer.LeanBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeanBar leanBar = LeanBar.newInstance(this);
        List<DrawerMenu> drawerMenus = new ArrayList<DrawerMenu>(){{
            add(DrawerMenu.newInstance(BitmapFactory.decodeResource(getResources(), R.drawable.delivered), "Deliver", new DrawerMenu.OnClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, "Deliver", Toast.LENGTH_SHORT).show();
                }
            }));
            add(DrawerMenu.newInstance(BitmapFactory.decodeResource(getResources(), R.drawable.order), "Order", new DrawerMenu.OnClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, "Order", Toast.LENGTH_SHORT).show();
                }
            }));
            add(DrawerMenu.newInstance(BitmapFactory.decodeResource(getResources(), R.drawable.onway), "OnWay", new DrawerMenu.OnClickListener() {
                @Override
                public void onClick() {
                    Toast.makeText(MainActivity.this, "OnWay", Toast.LENGTH_SHORT).show();
                }
            }));
        }};
        leanBar.setDrawerMenus(drawerMenus);
        leanBar.show();
    }
}
