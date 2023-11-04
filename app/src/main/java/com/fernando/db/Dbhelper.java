package com.fernando.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "db_calculadora";
    private static final String TABLA_CALCULOS = "tbl_calculos";

    public Dbhelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLA_CALCULOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numero1 DOUBLE NOT NULL," +
                "numero2 DOUBLE NOT NULL," +
                "operacion TEXT NOT NULL," +
                "resultado DOUBLE NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLA_CALCULOS);
        onCreate(sqLiteDatabase);
    }
}
