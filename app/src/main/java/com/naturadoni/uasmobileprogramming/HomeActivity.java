package com.naturadoni.uasmobileprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaDBHelper;

public class HomeActivity extends AppCompatActivity {
    ListView mhsListView;
    private String[] mhsStrings;
    private MahasiswaDBHelper db;
    private ArrayAdapter mhsAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // setup views
        mhsListView = findViewById(R.id.mahasiswa_listview);

        db = new MahasiswaDBHelper(this);

        mhsStrings = db.getAllMahasiswaFirstname();


        mhsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mhsStrings);
        mhsListView.setAdapter(mhsAdapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List Mahasiswa");

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        String name = sharedPreferences.getString("username", "");

        Toast.makeText(HomeActivity.this, "Welcome, " + name, Toast.LENGTH_LONG).show();

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

            case R.id.action_add:
                Intent registerActivity = new Intent(HomeActivity.this, com.naturadoni.uasmobileprogramming.RegisterActivity.class);
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

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}