package com.naturadoni.uasmobileprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaDBHelper;
import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel;

public class LoginActivity extends AppCompatActivity {
    EditText nimLoginET, passwordLoginET;
    Button btnLogin;
    TextView register;
    MahasiswaDBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        nimLoginET = findViewById(R.id.nim_login_edittext);
        passwordLoginET = findViewById(R.id.password_login_edittext);
        btnLogin = findViewById(R.id.button_login);

        db = new MahasiswaDBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nim = nimLoginET.getText().toString();
                String password = passwordLoginET.getText().toString();

                MahasiswaModel mhs = db.getMahasiswaByNIM(nim);

                if(mhs == null){
                    Toast.makeText(LoginActivity.this, "mahasiswa tidak ditemukan", Toast.LENGTH_LONG).show();
                    return;
//                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(homeIntent);
                }

                if(mhs.getPassword().equals(password)) {
                    Toast.makeText(LoginActivity.this, "password benar", Toast.LENGTH_LONG).show();
                    Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(homeIntent);
                }

            }

        });
    }
}