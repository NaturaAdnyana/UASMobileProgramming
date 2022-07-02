package com.naturadoni.uasmobileprogramming;

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
<<<<<<< HEAD
    EditText usernameET, fullnameET, nimET, passwordET;
    Button btnSave;
    MahasiswaDBHelper db;

    String username, fullname, password, nim;
=======
    EditText firstNameET, lastNameET, nimET, passwordET, reTypeET;
    Button btnSave;
    MahasiswaDBHelper db;

    String firstname, lastname, nim, password, retype;
>>>>>>> 9538cec73dfc99d1aa207fe5b0f2f004f6989f28

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Registrasi");
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = new MahasiswaDBHelper(this);

<<<<<<< HEAD
        usernameET = findViewById(R.id.username_edittext);
        fullnameET = findViewById(R.id.fullname_edittext);
        nimET = findViewById(R.id.nim_edittext);
        passwordET = findViewById(R.id.password_edittext);
=======
        firstNameET = findViewById(R.id.firstname_edittext);
        lastNameET = findViewById(R.id.lastname_edittext);
        nimET = findViewById(R.id.nim_edittext);
        passwordET = findViewById(R.id.password_edittext);
        reTypeET = findViewById(R.id.retype_edittext);
>>>>>>> 9538cec73dfc99d1aa207fe5b0f2f004f6989f28
        btnSave = findViewById(R.id.button_save);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                username = usernameET.getText().toString();
                fullname = fullnameET.getText().toString();
                password = passwordET.getText().toString();
                nim = nimET.getText().toString();

                MahasiswaModel newMahasiswa = new MahasiswaModel(username, fullname, password, nim);
                db.addMahasiswa(newMahasiswa);


                Toast.makeText(RegisterActivity.this, "Sukses Register", Toast.LENGTH_SHORT).show();
=======
                firstname = firstNameET.getText().toString();
                lastname = lastNameET.getText().toString();
                nim = nimET.getText().toString();
                password = passwordET.getText().toString();
                retype = reTypeET.getText().toString();

                MahasiswaModel newMahasiswa = new MahasiswaModel(firstname, lastname, nim, password);
                db.addMahasiswa(newMahasiswa);


                Toast.makeText(RegisterActivity.this, "Sukses Register\nSilahkan login", Toast.LENGTH_SHORT).show();
>>>>>>> 9538cec73dfc99d1aa207fe5b0f2f004f6989f28
                finish();
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