package com.example.cadastropastas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ConteudoPasta extends MainActivity {
    private RecyclerView recyclerViewConteudo;
    private ConteudoAdapter conteudoAdapter;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contpasta);
        recyclerViewConteudo = findViewById(R.id.recyclerViewConteudo);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        List<String> pastas = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PASTAS, new String[]{DatabaseHelper.COLUMN_NOME}, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Recupere o valor da coluna pelo índice
                String nomePasta = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME));
                pastas.add(nomePasta);
            } while (cursor.moveToNext());

            cursor.close();
        }
        conteudoAdapter = new ConteudoAdapter(pastas, this);
        recyclerViewConteudo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewConteudo.setAdapter(conteudoAdapter);
    }
}
