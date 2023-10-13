package com.example.cadastropastas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExibirConteudo extends AppCompatActivity {
    private EditText editTextNovoConteudo;
    private Button btnSalvarConteudo;
    private TextView textViewConteudoPasta;
    private String nomePasta;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir);

        // Recupere o nome da pasta da Intent
        nomePasta = getIntent().getStringExtra("nomePasta");

        editTextNovoConteudo = findViewById(R.id.editTextNovoConteudo);
        btnSalvarConteudo = findViewById(R.id.btnSalvarConteudo);
        textViewConteudoPasta = findViewById(R.id.textViewConteudoPasta);

        // Configure seu banco de dados, similar ao que você fez em outras atividades.
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        carregarConteudoDaPasta();
        // Carregue e exiba o conteúdo da pasta aqui, se aplicável.
    }

    private void carregarConteudoDaPasta() {
        // Recupere o conteúdo da pasta a partir do banco de dados com base no nome da pasta
        String conteudo = getConteudoDaPasta(nomePasta);

        // Exibir o conteúdo na textViewConteudoPasta
        textViewConteudoPasta.setText(conteudo);
    }

    // Método para salvar novo conteúdo na pasta
    public void salvarConteudo(View view) {
        String novoConteudo = editTextNovoConteudo.getText().toString();

        if (!novoConteudo.isEmpty()) {
            // Adicione a lógica para salvar 'novoConteúdo' no banco de dados associado a 'nomePasta'.
            // Certifique-se de configurar o banco de dados com as tabelas apropriadas, como 'Conteudo'.

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_PASTA_ID, obterIdPasta(nomePasta)); // Obtém o ID da pasta
            values.put(DatabaseHelper.COLUMN_TEXTO, novoConteudo);

            long novoConteudoId = database.insert(DatabaseHelper.TABLE_CONTEUDO, null, values);

            if (novoConteudoId != -1) {
                // Atualizar a exibição na textViewConteudoPasta
                String conteudoAtual = textViewConteudoPasta.getText().toString();
                conteudoAtual += "\n" + novoConteudo;
                textViewConteudoPasta.setText(conteudoAtual);

                // Limpar o campo de texto
                editTextNovoConteudo.setText("");
            }
        }
    }

    // Método para obter o ID da pasta com base no nome
    private long obterIdPasta(String nomePasta) {
        // Adicione a lógica para recuperar o ID da pasta com base no nome da pasta
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PASTAS,
                new String[]{DatabaseHelper.COLUMN_ID},
                DatabaseHelper.COLUMN_NOME + " = ?",
                new String[]{nomePasta},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            long pastaId = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            cursor.close();
            return pastaId;
        }

        cursor.close();
        return -1;
    }
    private String getConteudoDaPasta(String nomePasta) {
        String conteudo = "";

        String[] columns = {DatabaseHelper.COLUMN_TEXTO};
        String selection = DatabaseHelper.COLUMN_PASTA_ID + " = ?";
        String[] selectionArgs = {String.valueOf(obterIdPasta(nomePasta))};

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_CONTEUDO,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            int textoIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TEXTO);

            do {
                String texto = cursor.getString(textoIndex);
                conteudo += texto + "\n";
            } while (cursor.moveToNext());

            cursor.close();
        }
        return conteudo;
    }

}