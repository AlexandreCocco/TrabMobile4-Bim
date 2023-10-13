package com.example.cadastropastas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListaConteudo extends MainActivity {
    private RecyclerView recyclerView;
    private ConteudoAdapter conteudoAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcont);

        String nomePasta = getIntent().getStringExtra("nomePasta");

        dbHelper = new DatabaseHelper(this);

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_TEXTO};
        String selection = DatabaseHelper.COLUMN_PASTA_ID + " = ?";
        String[] selectionArgs = {String.valueOf(obterIdPasta(nomePasta))};

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_CONTEUDO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List<String> listaDeConteudo = new ArrayList<>();
        while (cursor.moveToNext()) {
            int textoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEXTO);
            String textoConteudo = cursor.getString(textoIndex);
            listaDeConteudo.add(textoConteudo);
        }

        cursor.close();
        database.close();

        recyclerView = findViewById(R.id.recyclerViewConteudo);
        conteudoAdapter = new ConteudoAdapter(listaDeConteudo, this);

        recyclerView.setAdapter(conteudoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private long obterIdPasta(String nomePasta) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] colunas = {DatabaseHelper.COLUMN_ID};
        String selecao = DatabaseHelper.COLUMN_NOME + " = ?";
        String[] selecaoArgs = {nomePasta};
        long idPasta = -1;

        if (nomePasta != null) {
            Cursor cursor = database.query(
                    DatabaseHelper.TABLE_PASTAS,
                    colunas,
                    selecao,
                    selecaoArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                idPasta = cursor.getLong(idIndex);
            }

            cursor.close();
        } else {
        }

        database.close();

        return idPasta;
    }
}
