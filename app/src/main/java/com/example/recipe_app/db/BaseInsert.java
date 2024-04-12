package com.example.recipe_app.db;

import android.content.ContentValues;
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
        values.put("cat_re_favorito", favorito ? 1 : 0); // Asegúrate de convertir booleano a entero aquí

        return db.insert("cat_recetas", null, values);
    }

    public long insertarIngrediente(String ingrediente) {
        ContentValues values = new ContentValues();
        values.put("cat_in_ingrediente", ingrediente);

        return db.insert("cat_ingredientes", null, values);
    }

    // Método para inicializar datos
    public void inicializarDatos() {
        db.beginTransaction();
        try {
            inicializarDatos2();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }




    // Método para inicializar datos
    public void inicializarDatos2() {
        // Insertar ingredientes
        insertarIngrediente("Carne de cerdo");
        insertarIngrediente("Piña");
        insertarIngrediente("Cebolla");
        insertarIngrediente("Cilantro");
        insertarIngrediente("Salsa");
        insertarIngrediente("Aguacate");
        insertarIngrediente("Tomate");
        insertarIngrediente("Limón");
        insertarIngrediente("Sal");
        insertarIngrediente("Masa");
        insertarIngrediente("Pescado");
        insertarIngrediente("Pepino");
        insertarIngrediente("Fideos");
        insertarIngrediente("Carne de res");
        insertarIngrediente("Carne de pollo");
        insertarIngrediente("Vinagre");
        insertarIngrediente("Harina");
        insertarIngrediente("Aceite de oliva");
        insertarIngrediente("Mantequilla");
        insertarIngrediente("Lechuga");
        insertarIngrediente("Patata");
        insertarIngrediente("Leche");
        insertarIngrediente("Azúcar");
        insertarIngrediente("Huevo");
        insertarIngrediente("Queso");
        insertarIngrediente("Albahaca");
        insertarIngrediente("Miel");
        insertarIngrediente("Chocolate");
        insertarIngrediente("Café");
        insertarIngrediente("Fresa");
        insertarIngrediente("Plátano");
        insertarIngrediente("Nuez");
        insertarIngrediente("Pan");
        insertarIngrediente("Arroz");
        insertarIngrediente("Pimienta");
        insertarIngrediente("Ajo");
        insertarIngrediente("Chile");
        insertarIngrediente("Carne de cordero");
        insertarIngrediente("Carne de conejo");
        insertarIngrediente("Garbanzos");
        insertarIngrediente("Tahini");
        insertarIngrediente("Eneldo");
        insertarIngrediente("Romero");
        insertarIngrediente("Cebolla verde");
        insertarIngrediente("Caldo de carne");
        insertarIngrediente("Yogur");
        insertarIngrediente("Bistec");
        insertarIngrediente("Costillas");
        insertarIngrediente("Salmón");


        // Insertar una receta
        // Mexicana
        insertarReceta("Tacos al Pastor", "Carne de cerdo, piña, cebolla, cilantro, salsa", "Cocinar la carne con piña y servir en tortillas de maíz con cebolla y cilantro.", "Tacos con carne marinada y piña.", "Mexicana", false);
        insertarReceta("Guacamole", "Aguacates, cebolla, tomate, limón, sal, cilantro", "Triturar los aguacates y mezclar con el resto de ingredientes.", "Salsa de aguacate fresco.", "Mexicana", false);
        // Italiana
        insertarReceta("Pizza Margherita", "Masa de pizza, salsa de tomate, mozzarella, albahaca", "Hornear la masa con salsa y queso, luego agregar albahaca.", "Clásica pizza italiana.", "Italiana", false);
        insertarReceta("Spaghetti Carbonara", "Spaghetti, guanciale, queso Pecorino Romano, huevo, pimienta negra", "Cocinar la pasta y mezclar con el resto de ingredientes.", "Pasta con salsa de huevo y guanciale.", "Italiana", false);
        // Japonesa
        insertarReceta("Sushi Maki", "Arroz de sushi, nori, pescado fresco, aguacate, pepino", "Enrollar los ingredientes en nori con arroz y cortar.", "Rollos de sushi con pescado y vegetales.", "Japonesa", false);
        insertarReceta("Ramen", "Fideos de ramen, caldo de carne o pescado, huevo, cebolla verde, nori, carne o pescado", "Cocinar los fideos y servir en el caldo con los toppings.", "Sopa de fideos japonesa.", "Japonesa", false);
        // Postres
        insertarReceta("Tarta de Queso", "Queso crema, galletas, mantequilla, azúcar, huevo", "Hornear la mezcla de queso sobre una base de galletas.", "Postre cremoso con base crujiente.", "Postres", false);
        insertarReceta("Brownies", "Chocolate, mantequilla, azúcar, harina, huevo", "Hornear la mezcla hasta que esté firme pero jugosa.", "Cuadraditos de chocolate húmedo.", "Postres", false);
        insertarReceta("Flan de Caramelo", "Leche, huevos, azúcar, esencia de vainilla", "Hornear la mezcla en baño María.", "Postre suave y dulce con caramelo.", "Postres", false);
        insertarReceta("Gelatina de Frutas", "Gelatina, frutas variadas, agua, azúcar", "Disolver la gelatina, añadir frutas y refrigerar.", "Refrescante y colorido postre de gelatina.", "Postres", false);
        // Res
        insertarReceta("Steak a la Pimienta", "Bistec de res, pimienta negra, sal, aceite de oliva, mantequilla", "Freír el bistec con pimienta y servir con salsa de mantequilla.", "Carne de res con pimienta.", "Res", false);
        insertarReceta("Hamburguesa Clásica", "Carne de res molida, pan de hamburguesa, lechuga, tomate, queso, salsa", "Grillar la carne y armar la hamburguesa con los ingredientes.", "Hamburguesa con todos los acompañamientos clásicos.", "Res", false);
        // Cerdo
        insertarReceta("Costillas BBQ", "Costillas de cerdo, salsa BBQ, miel, ajo, sal", "Hornear las costillas con la salsa BBQ.", "Costillas asadas con salsa BBQ.", "Cerdo", false);
        insertarReceta("Lomo de Cerdo Asado", "Lomo de cerdo, romero, ajo, sal, pimienta", "Asar el lomo de cerdo con hierbas y especias.", "Lomo asado jugoso y sabroso.", "Cerdo", false);
        // Pollo
        insertarReceta("Pollo al Limón", "Pechuga de pollo, limón, ajo, pimienta, sal", "Hornear el pollo con limón y especias.", "Pollo jugoso con un toque cítrico.", "Pollo", false);
        insertarReceta("Alitas Picantes", "Alitas de pollo, salsa picante, miel, ajo, vinagre", "Hornear las alitas con la salsa picante.", "Alitas de pollo con salsa picante y dulce.", "Pollo", false);
        // Pescado
        insertarReceta("Salmón al Horno", "Salmón, limón, eneldo, sal, pimienta", "Hornear el salmón con limón y eneldo.", "Salmón jugoso y aromatizado.", "Pescado", false);
        insertarReceta("Ceviche", "Pescado blanco, limón, cebolla morada, cilantro, chile", "Marinar el pescado en jugo de limón con vegetales y chile.", "Plato fresco de pescado marinado.", "Pescado", false);
        // Mediterránea
        insertarReceta("Hummus", "Garbanzos, tahini, limón, ajo, aceite de oliva", "Procesar todos los ingredientes hasta obtener una pasta.", "Crema de garbanzos oriental.", "Mediterranea", false);
        insertarReceta("Gyros de Cordero", "Carne de cordero, pan de pita, tomate, cebolla, salsa tzatziki", "Servir la carne en pan de pita con vegetales y salsa.", "Sándwich griego con carne de cordero.", "Mediterranea", false);
        // Patatas
        insertarReceta("Patatas Bravas", "Patatas, salsa brava, aceite de oliva, ajo", "Freír las patatas y servir con salsa brava.", "Patatas picantes típicas españolas.", "Patatas", false);
        insertarReceta("Puré de Patatas", "Patatas, leche, mantequilla, sal, nuez moscada", "Cocer las patatas y triturar con leche y mantequilla.", "Cremoso puré de patatas.", "Patatas", false);
        // Conejo
        insertarReceta("Conejo al Ajillo", "Conejo, ajos, vino blanco, laurel, pimienta", "Cocinar el conejo con ajos y vino blanco.", "Conejo tierno cocido con ajos.", "Conejo", false);
        insertarReceta("Estofado de Conejo", "Conejo, patatas, zanahorias, cebolla, tomillo", "Estofar el conejo con vegetales y hierbas.", "Estofado rico y aromático de conejo.", "Conejo", false);
        // Cordero
        insertarReceta("Cordero Asado", "Pierna de cordero, romero, ajo, vino tinto", "Asar la pierna de cordero con hierbas y vino.", "Pierna de cordero jugosa y aromatizada.", "Cordero", false);
        insertarReceta("Kebabs de Cordero", "Carne de cordero, cebolla, pimiento, especias, yogur", "Grillar la carne con vegetales y servir con salsa de yogur.", "Kebabs de cordero especiados.", "Cordero", false);
        // Vegana
        insertarReceta("Chili Vegano", "Frijoles, tomate, maíz, pimiento, especias", "Cocinar todos los ingredientes hasta que espese.", "Chili picante sin productos animales.", "Vegana", false);
        insertarReceta("Curry de Verduras", "Verduras surtidas, leche de coco, pasta de curry", "Cocinar las verduras en leche de coco y curry.", "Plato rico y cremoso de verduras.", "Vegana", false);
        // Comida Rápida
        insertarReceta("Pizza Pepperoni", "Masa de pizza, salsa de tomate, queso, pepperoni", "Hornear la masa con salsa, queso y pepperoni.", "Pizza rápida con pepperoni.", "Comida Rapida", false);
        insertarReceta("Papas Fritas", "Patatas, aceite para freír, sal", "Freír las patatas y salar al gusto.", "Clásico acompañamiento de comida rápida.", "Comida Rapida", false);
        // Desayuno
        insertarReceta("Huevos Revueltos", "Huevos, leche, sal, pimienta, mantequilla", "Revolver los huevos con leche y cocinar en mantequilla.", "Desayuno clásico y sencillo.", "Desayuno", false);
        insertarReceta("Panqueques", "Harina, leche, huevo, azúcar, levadura", "Cocinar la mezcla en una sartén hasta dorar ambos lados.", "Dulces y esponjosos panqueques.", "Desayuno", false);
        // Panadería
        insertarReceta("Pan de Ajo", "Pan francés, ajo, mantequilla, perejil", "Hornear el pan con ajo y mantequilla.", "Acompañamiento crujiente y aromático.", "Panadería", false);
        insertarReceta("Baguette", "Harina, agua, levadura, sal", "Hornear hasta que la corteza esté crujiente.", "Pan francés largo y delgado.", "Panadería", false);
        // Sopas
        insertarReceta("Sopa de Tomate", "Tomate, cebolla, caldo de verduras, albahaca", "Cocinar los tomates con cebolla y caldo, triturar.", "Sopa cremosa de tomate.", "Sopas", false);
        insertarReceta("Gazpacho", "Tomate, pepino, pimiento, ajo, vinagre", "Mezclar las verduras crudas y servir frío.", "Sopa fría española.", "Sopas", false);
        // Bebidas
        insertarReceta("Smoothie de Fresa", "Fresas, plátano, yogur, miel", "Licuar todos los ingredientes hasta suavizar.", "Bebida fría y nutritiva.", "Bebidas", false);
        insertarReceta("Café Frappé", "Café, leche, hielo, azúcar", "Mezclar el café con hielo y azúcar.", "Café helado espumoso.", "Bebidas", false);

    }
}
