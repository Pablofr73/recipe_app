package com.example.recipe_app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recetas.db";
    private static final int DATABASE_VERSION = 1;

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

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_CAT_RECETAS_CREAR);
        db.execSQL(TABLA_CAT_INGREDIENTES_CREAR);
        BaseInsert basein = new BaseInsert(db);
        basein.insertarIngrediente("");
        basein.insertarReceta(
                "",
                "",
                "",
                "",
                "",
                false
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
