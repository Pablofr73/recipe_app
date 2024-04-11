package com.example.recipe_app.db;

import android.database.sqlite.SQLiteDatabase;
public class BaseCreate {
    public static final String TABLA_CAT_RECETAS_CREAR =
            "CREATE TABLE cat_recetas (" +
                    "cat_re_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cat_re_nombre TEXT NOT NULL, " +
                    "cat_re_ingredientes TEXT, " +
                    "cat_re_receta TEXT, " +
                    "cat_re_descripcion TEXT, " +
                    "cat_re_categoria TEXT, " +
                    "cat_re_favorito INTEGER NOT NULL)";

    public static final String TABLA_CAT_INGREDIENTES_CREAR =
            "CREATE TABLE cat_ingredientes (" +
                    "cat_in_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "cat_in_ingrediente TEXT NOT NULL)";

    public static void crearTablas(SQLiteDatabase db) {
        db.execSQL(TABLA_CAT_RECETAS_CREAR);
        db.execSQL(TABLA_CAT_INGREDIENTES_CREAR);
    }

}
