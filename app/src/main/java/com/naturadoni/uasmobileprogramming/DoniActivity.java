package com.naturadoni.uasmobileprogramming;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DoniActivity  extends AppCompatActivity {
    ImageView githubDoni;
    ImageView linkedinDoni;
    TextView webDoni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doni);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Doni Wirawan");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#B22727"));
        actionBar.setBackgroundDrawable(colorDrawable);

        githubDoni = findViewById(R.id.doni_github);
        linkedinDoni = findViewById(R.id.doni_linkedin);
        webDoni = findViewById(R.id.doni_web);

        githubDoni.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/doniwirawan"));
                startActivity(browserIntent);
            }

        });

        linkedinDoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://id.linkedin.com/in/doniwirawan/en"));
                startActivity(browserIntent);
            }
        });

        webDoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://doniwirawan.com/"));
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