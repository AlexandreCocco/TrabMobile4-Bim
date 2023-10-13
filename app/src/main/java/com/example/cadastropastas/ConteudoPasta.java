package com.example.cadastropastas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConteudoPasta extends MainActivity implements PastaAdapter.OnPastaClickListener {
    private RecyclerView recyclerViewConteudo;
    private PastaAdapter pastaAdapter;
    private List<String> pastas;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contpasta);

        recyclerViewConteudo = findViewById(R.id.recyclerViewConteudo);

        // Inicialize o banco de dados
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        // Carregue as pastas do banco de dados
        pastas = loadPastasFromDatabase();

        pastaAdapter = new PastaAdapter(pastas, this);
        recyclerViewConteudo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewConteudo.setAdapter(pastaAdapter);
    }

    @Override
    public void onPastaClick(String nomePasta) {

        Intent intent = new Intent(this, ExibirConteudo.class);
        intent.putExtra("nomePasta", nomePasta); // Passar o nome da pasta para a próxima atividade
        startActivity(intent);

    }

    // Função para carregar as pastas do banco de dados
    private List<String> loadPastasFromDatabase() {
        List<String> pastaList = new ArrayList<>();
        String[] columns = {DatabaseHelper.COLUMN_NOME};
        Cursor cursor = database.query(DatabaseHelper.TABLE_PASTAS, columns, null, null, null, null, null);

        if (cursor != null && ((Cursor) cursor).moveToFirst()) {
            do {
                String nomePasta = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME));
                pastaList.add(nomePasta);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return pastaList;
    }
}
