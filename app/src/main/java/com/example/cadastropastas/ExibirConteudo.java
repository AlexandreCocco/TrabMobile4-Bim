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


        nomePasta = getIntent().getStringExtra("nomePasta");

        editTextNovoConteudo = findViewById(R.id.editTextNovoConteudo);
        btnSalvarConteudo = findViewById(R.id.btnSalvarConteudo);
        textViewConteudoPasta = findViewById(R.id.textViewConteudoPasta);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        carregarConteudoDaPasta();
    }

    private void carregarConteudoDaPasta() {
        String conteudo = getConteudoDaPasta(nomePasta);

        textViewConteudoPasta.setText(conteudo);
    }


    public void salvarConteudo(View view) {
        String novoConteudo = editTextNovoConteudo.getText().toString();

        if (!novoConteudo.isEmpty()) {

            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_PASTA_ID, obterIdPasta(nomePasta));
            values.put(DatabaseHelper.COLUMN_TEXTO, novoConteudo);

            long novoConteudoId = database.insert(DatabaseHelper.TABLE_CONTEUDO, null, values);

            if (novoConteudoId != -1) {

                String conteudoAtual = textViewConteudoPasta.getText().toString();
                conteudoAtual += "\n" + novoConteudo;
                textViewConteudoPasta.setText(conteudoAtual);


                editTextNovoConteudo.setText("");
            }
        }
    }

    private long obterIdPasta(String nomePasta) {
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