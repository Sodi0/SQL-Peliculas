package com.example.sql_peliculas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DaoContacto {
    SQLiteDatabase _database;
    ArrayList<Resenia> _lista = new ArrayList<Resenia>();
    Resenia _resenia;
    Context _context;
    String _nombreBD = "BDContactos";
    String _table = "create table if not exists resenia(id integer primary key autoincrement,titulo text,genero text, director text, anio integer, puntuacion real, resenia text)";

    public DaoContacto(Context _contacto){
        this._context = _contacto;
        _database = _contacto.openOrCreateDatabase(_nombreBD, Context.MODE_PRIVATE,null);
        _database.execSQL(_table);
    }

    public boolean insertar(Resenia _resenia) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("titulo", _resenia.getTitulo());
        contenedor.put("genero", _resenia.getGenero());
        contenedor.put("director", _resenia.getDirector());
        contenedor.put("anio", _resenia.getAnio());
        contenedor.put("puntuacion", _resenia.getPuntuacion());
        contenedor.put("resenia", _resenia.getResenia());
        return (_database.insert("resenia", null, contenedor)) > 0;
    }

    public boolean eliminar(int id) {
        return (_database.delete("resenia", "id=" + id, null)) > 0;
    }

    public boolean editar(Resenia _resenia) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("titulo", _resenia.getTitulo());
        contenedor.put("genero", _resenia.getGenero());
        contenedor.put("director", _resenia.getDirector());
        contenedor.put("anio", _resenia.getAnio());
        contenedor.put("puntuacion", _resenia.getPuntuacion());
        contenedor.put("resenia", _resenia.getResenia());
        return (_database.update("resenia", contenedor, "id=" + _resenia.getId(), null)) > 0;
    }

    public ArrayList<Resenia> verTodo() {
        _lista.clear();
        Cursor cursor = _database.rawQuery("select * from resenia", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                _lista.add(new Resenia(
                        cursor.getInt(0),      // id
                        cursor.getString(1),   // titulo
                        cursor.getString(2),   // genero
                        cursor.getString(3),   // director
                        cursor.getFloat(5),    // puntuacion
                        cursor.getInt(4),      // anio
                        cursor.getString(6)    //resenia
                ));
            } while (cursor.moveToNext());
        }
        return _lista;
    }

    public Resenia verUno(int posicion) {
        Cursor cursor = _database.rawQuery("select * from resenia", null);
        cursor.moveToPosition(posicion);
        _resenia = new Resenia(
                cursor.getInt(0),      // id
                cursor.getString(1),   // titulo
                cursor.getString(2),   // genero
                cursor.getString(3),   // director
                cursor.getFloat(5),    // puntuacion
                cursor.getInt(4),      // anio
                cursor.getString(6)   // resenia
        );
        return _resenia;
    }
}
