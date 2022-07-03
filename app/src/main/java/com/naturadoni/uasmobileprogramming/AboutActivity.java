package com.naturadoni.uasmobileprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity  extends AppCompatActivity {
    LinearLayout btnDoni;
    LinearLayout btnNatura;
    LinearLayout btnRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sang Pencipta Saya");
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnDoni = findViewById(R.id.btn_doni);
        btnNatura = findViewById(R.id.btn_natura);
        btnRepo = findViewById(R.id.repo_link);

        btnDoni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent doniIntent = new Intent(AboutActivity.this, DoniActivity.class);
                startActivity(doniIntent);
            }

        });

        btnNatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent naturaIntent = new Intent(AboutActivity.this, NaturaActivity.class);
                startActivity(naturaIntent);
            }
        });

        btnRepo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/NaturaAdnyana/UASMobileProgramming"));
                startActivity(browserIntent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}