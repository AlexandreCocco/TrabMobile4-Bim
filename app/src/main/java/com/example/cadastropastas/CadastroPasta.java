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
        // Obtenha o nome da pasta do EditText.
        String nomePasta = editTextNomePasta.getText().toString();

        if (!nomePasta.isEmpty()) {
            // Adicione a pasta aos dados compartilhados.
            PastaData.getInstance().adicionarPasta(nomePasta);

            // Limpe o campo de texto após criar a pasta.
            editTextNomePasta.setText("");

            // Atualize a lista de pastas no ConteudoPastaActivity, se necessário.
        }
    }

    public void salvarPasta(View view) {
        // Obtenha o nome da pasta do EditText.
        String nomePasta = editTextNomePasta.getText().toString();

        if (!nomePasta.isEmpty()) {
            // Crie um ContentValues para inserir a pasta no banco de dados.
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NOME, nomePasta);

            // Insira a pasta no banco de dados.
            long id = database.insert(DatabaseHelper.TABLE_PASTAS, null, values);

            if (id != -1) {
                // Pasta inserida com sucesso.
                Toast.makeText(this, "Pasta salva com sucesso", Toast.LENGTH_SHORT).show();
                editTextNomePasta.setText(""); // Limpe o campo de texto.

                // Atualize a lista de pastas no ConteudoPastaActivity, se necessário.
            } else {
                // Ocorreu um erro ao inserir a pasta no banco de dados.
                Toast.makeText(this, "Erro ao salvar a pasta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}