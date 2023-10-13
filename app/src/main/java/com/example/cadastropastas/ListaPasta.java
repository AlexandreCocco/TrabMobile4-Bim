package com.example.cadastropastas;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListaPasta extends MainActivity {
    private RecyclerView recyclerView;
    private PastaAdapter pastaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listpasta);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_NOME};
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PASTAS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<String> listaDePastas = new ArrayList<>();
        while (cursor.moveToNext()) {
            int nomeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME);
            String nomePasta = cursor.getString(nomeIndex);
            listaDePastas.add(nomePasta);
        }

        cursor.close();
        database.close();

        recyclerView = findViewById(R.id.recyclerViewPastas);
        pastaAdapter = new PastaAdapter(listaDePastas, new PastaAdapter.OnPastaClickListener() {
            @Override
            public void onPastaClick(String nomePasta) {
            }
        });
        recyclerView.setAdapter(pastaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
