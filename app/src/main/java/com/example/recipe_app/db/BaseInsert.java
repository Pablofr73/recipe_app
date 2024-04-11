package com.example.recipe_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
public class BaseInsert {

    private SQLiteDatabase db;

    public BaseInsert(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertarReceta(String nombre, String ingredientes, String receta, String descripcion, String categoria, boolean favorito) {
        ContentValues values = new ContentValues();
        values.put("cat_re_nombre", nombre);
        values.put("cat_re_ingredientes", ingredientes);
        values.put("cat_re_receta", receta);
        values.put("cat_re_descripcion", descripcion);
        values.put("cat_re_categoria", categoria);
        values.put("cat_re_favorito", favorito ? 1 : 0);

        return db.insert("cat_recetas", null, values);
    }

    public long insertarIngrediente(String ingrediente) {
        ContentValues values = new ContentValues();
        values.put("cat_in_ingrediente", ingrediente);

        return db.insert("cat_ingredientes", null, values);
    }
}

