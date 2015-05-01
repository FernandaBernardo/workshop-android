package com.example.fernanda.helloworld;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import static android.view.ContextMenu.ContextMenuInfo;
import static android.widget.AdapterView.AdapterContextMenuInfo;

/**
 * Created by Fernanda on 27/04/2015.
 */
public class ListaLembretesActivity extends ActionBar {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_lembrete);

        LembreteDAO dao = new LembreteDAO(this);
        List<Lembrete> lembretes = dao.getLista();
        dao.close();

        lista = (ListView) findViewById(R.id.lista_lembretes);
        ArrayAdapter<Lembrete> adapter = new ArrayAdapter<Lembrete>(this, android.R.layout.simple_list_item_1, lembretes);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

        final Lembrete lembrete = (Lembrete) lista.getAdapter().getItem(info.position);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                LembreteDAO dao = new LembreteDAO(ListaLembretesActivity.this);
                dao.deletar(lembrete);
                dao.close();
                return false;
            }
        });
    }
}
