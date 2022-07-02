package com.naturadoni.uasmobileprogramming;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaDBHelper;
import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel;

public class RegisterActivity extends AppCompatActivity {

    EditText firstNameET, lastNameET, nimET, passwordET, reTypeET;
    Button btnSave;
    MahasiswaDBHelper db;

    String firstname, lastname, nim, password, retype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registrasi");
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = new MahasiswaDBHelper(this);

        firstNameET = findViewById(R.id.firstname_edittext);
        lastNameET = findViewById(R.id.lastname_edittext);
        nimET = findViewById(R.id.nim_edittext);
        passwordET = findViewById(R.id.password_edittext);
        reTypeET = findViewById(R.id.retype_edittext);
        btnSave = findViewById(R.id.button_save);
        SharedPreferences sharedPreferences;

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        sharedPreferences.contains("nim");
        sharedPreferences.contains("username");
        sharedPreferences.contains("password");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstname = firstNameET.getText().toString();
                lastname = lastNameET.getText().toString();
                nim = nimET.getText().toString();
                password = passwordET.getText().toString();
                retype = reTypeET.getText().toString();

                if (password.equals(retype)){
                    MahasiswaModel newMahasiswa = new MahasiswaModel(firstname, lastname, nim, password);
                    db.addMahasiswa(newMahasiswa);

                    Toast.makeText(RegisterActivity.this, "Sukses Register\nSilahkan login", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password anda masih salah.", Toast.LENGTH_SHORT).show();
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nim", nim);
                editor.putString("username", firstname + ' ' + lastname);
                editor.putString("password", password);
                editor.apply();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}