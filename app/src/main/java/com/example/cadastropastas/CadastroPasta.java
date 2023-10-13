package com.example.cadastropastas;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CadastroPasta extends MainActivity {
    private EditText editTextNomePasta;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadpasta);

        editTextNomePasta = findViewById(R.id.editTextNomePasta);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
    }

    public void criarPasta(View view) {

        String nomePasta = editTextNomePasta.getText().toString();

        if (!nomePasta.isEmpty()) {

            PastaData.getInstance().adicionarPasta(nomePasta);

            editTextNomePasta.setText("");

        }
    }

    public void salvarPasta(View view) {

        String nomePasta = editTextNomePasta.getText().toString();

        if (!nomePasta.isEmpty()) {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NOME, nomePasta);

            long id = database.insert(DatabaseHelper.TABLE_PASTAS, null, values);

            if (id != -1) {

                Toast.makeText(this, "Pasta salva com sucesso", Toast.LENGTH_SHORT).show();
                editTextNomePasta.setText(""); // Limpe o campo de texto.

            } else {
                Toast.makeText(this, "Erro ao salvar a pasta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}