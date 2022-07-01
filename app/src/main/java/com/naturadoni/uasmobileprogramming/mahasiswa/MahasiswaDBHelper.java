package com.naturadoni.uasmobileprogramming.mahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pertemuan14_db";
    private static final String TABLE_MAHASISWA = "mahasiswa";

    public MahasiswaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MAHASISWA_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_MAHASISWA + "("
                + com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_USERNAME + " TEXT, "
                + com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_FULLNAME + " TEXT, "
                + com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_PASSWORD + " TEXT, "
                + com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_NIM + " TEXT "
                + ");";

        Log.d("CREATE MHS", CREATE_MAHASISWA_TABLE);

        db.execSQL(CREATE_MAHASISWA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
    }


    // Fungsi untuk mengambil 1 mahasiswa
    public com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel getMahasiswaByNIM(String nim) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MAHASISWA, new String[] {
                        com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_ID,
                        com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_USERNAME,
                        com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_FULLNAME,
                        com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_PASSWORD,
                        com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_NIM }, com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_NIM + "=?",
                new String[]{ String.valueOf(nim)}, null, null, null, null);

//        if (cursor != null)  cursor.moveToFirst();

        if (cursor.getCount() <= 0) return null;
        cursor.moveToFirst();

        com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel mahasiswa = new com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel(
                cursor.getInt(0), // id
                cursor.getString(1), // username
                cursor.getString(2), // fullname
                cursor.getString(3), // password
                cursor.getString(4) // nim
                );

        return mahasiswa;
    }

    // Fungsi untuk menambahkan record kedalam table
    public void addMahasiswa(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel mahasiswa) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_USERNAME, mahasiswa.getUsername());
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_FULLNAME, mahasiswa.getFullname());
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_PASSWORD, mahasiswa.getPassword());
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_NIM, mahasiswa.getNim());

        db.insert(TABLE_MAHASISWA, null, values);
        db.close();
    }

    // Mendapatkan semua record mahasiswa
    public List<com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel> getAllMahasiswa() {
        List<com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel> mhsList = new ArrayList<com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MAHASISWA, null);


        // Untuk setiap data mahasiswa yang didapat
        // pindahkan kedalam sebuah ArrayList
        if (cursor.moveToFirst()) {
            do {
                com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel mhs = new com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel();
                mhs.setId(cursor.getInt(0));
                mhs.setUsername(cursor.getString(1));
                mhs.setFullname(cursor.getString(2));
                mhs.setPassword(cursor.getString(3));
                mhs.setNim(cursor.getString(4));

                mhsList.add(mhs);
            } while (cursor.moveToNext());
            // cursor move to next menandakan bahwa data selanjutnya masih ada
            // pada list data mahasiswa dalam tabel
        }

        return mhsList;
    }

    public String[] getAllMahasiswaFullname() {
        String selectFullnameQuery = "SELECT fullname FROM " + TABLE_MAHASISWA;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(selectFullnameQuery, null);

        int tbLength = cursor.getCount();

        String[] mhsStrings = new String[tbLength];
        int counter = 0;

        if (cursor.moveToFirst()) {
            do {
                String fullname = cursor.getString(0);
                mhsStrings[counter] = fullname;

                Log.d("MHS STR", mhsStrings[counter]);

                counter++;
            } while (cursor.moveToNext());
        }

        cursor.close();

        return mhsStrings;
    }

    public int getMahasiswaCount() {
        String countQuery = "SELECT COUNT(*) FROM " + TABLE_MAHASISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getInt(0);
    }

    public void updateMahasiswa(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel mhs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_USERNAME, mhs.getUsername());
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_FULLNAME, mhs.getFullname());
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_PASSWORD, mhs.getPassword());
        values.put(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_NIM, mhs.getNim());

        int status = db.update(TABLE_MAHASISWA, values, com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_ID + "=?",
                new String[] { String.valueOf(mhs.getId())});

        Log.d("UPDATE MHS", String.valueOf(status));
    }

    public void deleteMahasiswa(com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaModel mhs) {
        SQLiteDatabase db  = this.getWritableDatabase();
        int status = db.delete(TABLE_MAHASISWA, com.naturadoni.uasmobileprogramming.mahasiswa.MahasiswaTableSchema.KEY_ID + "=?",
                new String[] { String.valueOf(mhs.getId())});

        Log.d("DELETE MHS", String.valueOf(status));
        db.close();

    }


}
