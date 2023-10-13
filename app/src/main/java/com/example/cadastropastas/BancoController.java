package com.example.cadastropastas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase database;
    private Banco dbHelper;

    public BancoController(Context context) {
        dbHelper = new Banco(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Adicione métodos para operações no banco de dados, como inserir, atualizar, excluir e recuperar dados.
    // Por exemplo:

    public long inserirPasta(String nome) {
        ContentValues values = new ContentValues();
        values.put(Banco.COLUMN_NOME, nome);

        return database.insert(Banco.TABLE_PASTAS, null, values);
    }

    public Cursor listarPastas() {
        String[] allColumns = {Banco.COLUMN_ID, Banco.COLUMN_NOME};

        return database.query(Banco.TABLE_PASTAS, allColumns, null, null, null, null, null); // Use o nome da tabela do seu banco de dados.
    }
}
