package com.example.cadastropastas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper { // Nome da classe do banco de dados alterado
    private static final String DATABASE_NAME = "Banco.db"; // Nome do banco de dados alterado
    private static final int DATABASE_VERSION = 1;

    // Tabela para armazenar pastas
    public static final String TABLE_PASTAS = "pastas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";

    // Tabela para armazenar conteúdo relacionado a pastas
    public static final String TABLE_CONTEUDO = "conteudo";
    public static final String COLUMN_PASTA_ID = "pasta_id";
    public static final String COLUMN_CONTEUDO = "conteudo";

    // Construtor
    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela de pastas
        String CREATE_PASTAS_TABLE = "CREATE TABLE " + TABLE_PASTAS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOME + " TEXT"
                + ")";
        db.execSQL(CREATE_PASTAS_TABLE);

        // Cria a tabela de conteúdo
        String CREATE_CONTEUDO_TABLE = "CREATE TABLE " + TABLE_CONTEUDO + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PASTA_ID + " INTEGER,"
                + COLUMN_CONTEUDO + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTEUDO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASTAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEUDO);
        onCreate(db);
    }
}
