package com.example.cadastropastas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MinhasPastas.db";
    private static final int DATABASE_VERSION = 1;

    // Tabela Pastas
    public static final String TABLE_PASTAS = "Pastas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";

    // Tabela Conteudo
    public static final String TABLE_CONTEUDO = "Conteudo";
    public static final String COLUMN_CONTEUDO_ID = "conteudo_id";
    public static final String COLUMN_PASTA_ID = "pasta_id";
    public static final String COLUMN_TEXTO = "texto";

    private static final String TABLE_PASTAS_CREATE =
            "CREATE TABLE " + TABLE_PASTAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME + " TEXT " +
                    ")";

    private static final String TABLE_CONTEUDO_CREATE =
            "CREATE TABLE " + TABLE_CONTEUDO + " (" +
                    COLUMN_CONTEUDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PASTA_ID + " INTEGER, " +
                    COLUMN_TEXTO + " TEXT " +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PASTAS_CREATE);
        db.execSQL(TABLE_CONTEUDO_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEUDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASTAS);
        onCreate(db);
    }
}