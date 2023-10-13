package com.example.cadastropastas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button cadastro, conteudo, lista, lConteudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            cadastro = findViewById(R.id.btnCadastroPasta);
            conteudo = findViewById(R.id.btnConteudoPasta);
            lista = findViewById(R.id.btnListaPasta);
            lConteudo = findViewById(R.id.btnListaConteudo);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cad = new Intent(getApplicationContext(), CadastroPasta.class);
                startActivity(cad);
            }
        });

        conteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent con = new Intent(getApplicationContext(), ConteudoPasta.class);
                startActivity(con);
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lis = new Intent(getApplicationContext(), ListaPasta.class);
                startActivity(lis);
            }
        });

        lConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lco = new Intent(getApplicationContext(), ListaConteudo.class);
                startActivity(lco);
            }
        });
    }
}