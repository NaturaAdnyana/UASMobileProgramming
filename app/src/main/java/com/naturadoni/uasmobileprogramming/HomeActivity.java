package com.naturadoni.uasmobileprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity  extends AppCompatActivity {
    LinearLayout btnMhs;
    LinearLayout btnTentang;
    TextView greetTitle;
    TextView greetText;
    SharedPreferences sharedPreferences;
    final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        greetTitle = findViewById(R.id.greet_title);
        greetText = findViewById(R.id.greet_text);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        btnMhs = findViewById(R.id.btn_mhs);
        btnTentang = findViewById(R.id.btn_tentang);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        String name = sharedPreferences.getString("firstname", "");

        greetTitle.setText("Halo, " + name + "👋");

        btnMhs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                    Intent homeIntent = new Intent(HomeActivity.this, MhsActivity.class);
                    startActivity(homeIntent);
            }

        });

        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(registerIntent);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                greetText.setText("Ada yang bisa kami bantu?");
            }
        },4000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homepage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent loginActivity = new Intent(HomeActivity.this, com.naturadoni.uasmobileprogramming.LoginActivity.class);
                startActivity(loginActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}