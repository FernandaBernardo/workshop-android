package com.example.fernanda.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBar {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = (Button) findViewById(R.id.adicionar);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = (EditText) findViewById(R.id.titulo_lembrete);
                String titulo = edit.getText().toString();

                Lembrete lembrete = new Lembrete();
                lembrete.setTitulo(titulo);

                LembreteDAO dao = new LembreteDAO(MainActivity.this);
                dao.insere(lembrete);
                dao.close();

                Intent intent = new Intent(MainActivity.this, ListaLembretesActivity.class);
                startActivity(intent);
            }
        });
    }
}