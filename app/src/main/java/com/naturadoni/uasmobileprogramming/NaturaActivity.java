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

public class NaturaActivity  extends AppCompatActivity {
    ImageView githubDoni;
    ImageView linkedinDoni;
    TextView webDoni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_natura);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Natura Adnyana");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#B22727"));
        actionBar.setBackgroundDrawable(colorDrawable);

        githubDoni = findViewById(R.id.natura_github);
        linkedinDoni = findViewById(R.id.natura_linkedin);
        webDoni = findViewById(R.id.natura_web);

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
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/natura-adnyana/"));
                startActivity(browserIntent);
            }
        });

        webDoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.naturaadnyana.com/"));
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