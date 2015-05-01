package com.example.fernanda.helloworld;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernanda on 28/04/2015.
 */
class LembreteDAO extends SQLiteOpenHelper{
    private static final int VERSAO = 1;
    private static final String TABELA = "Lembretes";
    private static final String DATABASE = "CadastroLembretes";

    public LembreteDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA
                + " (id INTEGER PRIMARY KEY, "
                + " titulo TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Lembrete lembrete) {
        ContentValues values = new ContentValues();

        values.put("id", lembrete.getId());
        values.put("titulo", lembrete.getTitulo());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Lembrete> getLista() {
        ArrayList<Lembrete> lembretes = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("select * from " + TABELA + ";", null);

        while (cursor.moveToNext()) {
            Lembrete lembrete = new Lembrete();

            lembrete.setId(cursor.getLong(cursor.getColumnIndex("id")));
            lembrete.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));

            lembretes.add(lembrete);
        }

        cursor.close();
        return lembretes;
    }

    public void deletar(Lembrete lembrete) {
        String[] args = {lembrete.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", args);
    }
}