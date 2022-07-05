package com.naturadoni.uasmobileprogramming;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaDBHelper;
import com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    EditText firstNameET, lastNameET, nimET, passwordET, reTypeET;
    ImageView addProfile;
    Button btnSave;
    MahasiswaDBHelper db;

    String firstname, lastname, nim, password, retype;

    final Calendar myCalendar= Calendar.getInstance();
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        ColorDrawable   colorDrawable = new ColorDrawable(Color.parseColor("#B22727"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Registrasi");
        actionBar.setDisplayHomeAsUpEnabled(true);

        db = new MahasiswaDBHelper(this);

        addProfile = findViewById(R.id.add_profile);
        firstNameET = findViewById(R.id.firstname_edittext);
        lastNameET = findViewById(R.id.lastname_edittext);
        nimET = findViewById(R.id.nim_edittext);
        passwordET = findViewById(R.id.password_edittext);
        reTypeET = findViewById(R.id.retype_edittext);
        btnSave = findViewById(R.id.button_save);

        Spinner dropdown = findViewById(R.id.jurusan);
        String[] items = new String[]{"TI - Manajemen Data & Informasi", "TI - Komputer Akuntansi dan Bisnis", "Desain Komunikasi Visual"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        sharedPreferences.contains("nim");
        sharedPreferences.contains("username");
        sharedPreferences.contains("password");

        editText=(EditText) findViewById(R.id.tgl_lahir);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this,R.style.date_picker_theme, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProfile.setImageResource(R.drawable.profile);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firstname = firstNameET.getText().toString();
                lastname = lastNameET.getText().toString();
                nim = nimET.getText().toString();
                password = passwordET.getText().toString();
                retype = reTypeET.getText().toString();

                if (firstname.equals("") || lastname.equals("") || nim.equals("")|| password.equals("") || retype.equals("")) {
                    Toast.makeText(RegisterActivity.this, "Data anda masih ada yang kosong.", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(retype)) {
                        MahasiswaModel newMahasiswa = new MahasiswaModel(firstname, lastname, nim, password);
                        db.addMahasiswa(newMahasiswa);

                        Toast.makeText(RegisterActivity.this, "Sukses Register\nSilahkan login", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password anda masih salah.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
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