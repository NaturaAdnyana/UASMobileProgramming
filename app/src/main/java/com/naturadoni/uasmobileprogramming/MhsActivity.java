package com.naturadoni.uasmobileprogramming;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaDBHelper;

public class MhsActivity extends AppCompatActivity {
    ListView mhsListView;
    private String[] mhsStrings;
    private MahasiswaDBHelper db;
    private ArrayAdapter mhsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhs);

        // setup views
        mhsListView = findViewById(R.id.mahasiswa_listview);

        db = new MahasiswaDBHelper(this);

        mhsStrings = db.getAllMahasiswaFirstname();


        mhsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mhsStrings);
        mhsListView.setAdapter(mhsAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List Mahasiswa");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#B22727"));
        actionBar.setBackgroundDrawable(colorDrawable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.mhspage_menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_add:
                Intent registerActivity = new Intent(MhsActivity.this, com.naturadoni.uasmobileprogramming.RegisterActivity.class);
                            startActivity(registerActivity);
                return true;

            case R.id.action_refresh:
                mhsStrings = db.getAllMahasiswaFirstname();
                mhsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mhsStrings);
                mhsListView.setAdapter(mhsAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}