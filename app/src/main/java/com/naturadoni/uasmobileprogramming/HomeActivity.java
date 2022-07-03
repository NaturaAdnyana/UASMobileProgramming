package com.naturadoni.uasmobileprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity  extends AppCompatActivity {
    Button btnMhs;
    Button btnTentang;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        btnMhs = findViewById(R.id.btn_mhs);
        btnTentang = findViewById(R.id.btn_tentang);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");

        Toast.makeText(HomeActivity.this, "Welcome, " + name, Toast.LENGTH_LONG).show();

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
                Intent registerIntent = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
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