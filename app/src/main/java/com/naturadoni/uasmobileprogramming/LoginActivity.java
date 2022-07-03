package com.naturadoni.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaDBHelper;
import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel;

public class LoginActivity extends AppCompatActivity {
    EditText nimLoginET, passwordLoginET;
    Button btnLogin;
    TextView btnRegister;
    MahasiswaDBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        nimLoginET = findViewById(R.id.nim_login_edittext);
        passwordLoginET = findViewById(R.id.password_login_edittext);
        btnLogin = findViewById(R.id.button_login);
        btnRegister = findViewById(R.id.register_btn);

        db = new MahasiswaDBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nim = nimLoginET.getText().toString();
                String password = passwordLoginET.getText().toString();
                SharedPreferences sharedPreferences;

                sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
                sharedPreferences.contains("nim");
                sharedPreferences.contains("username");
                sharedPreferences.contains("password");

                MahasiswaModel mhs = db.getMahasiswaByNIM(nim);

                if(mhs == null){
                    Toast.makeText(LoginActivity.this, "Anda salah memasukkan username/password", Toast.LENGTH_LONG).show();
                    return;
                }

                if(mhs.getPassword().equals(password)) {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nim", mhs.getNim());
                    editor.putString("username", mhs.getFirstname() + ' ' + mhs.getLastname());
                    editor.putString("password", mhs.getPassword());
                    editor.apply();

                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homeIntent);

                }

            }

        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}