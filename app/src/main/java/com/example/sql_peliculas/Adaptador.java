package com.example.sql_peliculas;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Resenia> lista;
    DaoContacto dao;
    Resenia _resenia;
    Activity _activity;
    int id=0;

    public Adaptador(ArrayList<Resenia> lista, DaoContacto dao, Activity _activity) {
        this.lista = lista;
        this.dao = dao;
        this._activity = _activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        _resenia = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        _resenia = lista.get(i);
        return _resenia.getId();
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        _resenia = lista.get(posicion);
        TextView titulo = v.findViewById(R.id.tv_titulo);
        TextView genero = v.findViewById(R.id.tv_genero);
        TextView director = v.findViewById(R.id.tv_director);
        TextView anio = v.findViewById(R.id.tv_anio);
        RatingBar puntuacion = v.findViewById(R.id.rating_puntuacion_view);
        TextView resenia = v.findViewById(R.id.tv_resenia);
        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);

        titulo.setText(_resenia.getTitulo());
        genero.setText(_resenia.getGenero());
        director.setText(_resenia.getDirector());
        anio.setText(String.valueOf(_resenia.getAnio()));
        puntuacion.setRating(_resenia.getPuntuacion());
        resenia.setText(_resenia.getResenia());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialog = new Dialog(_activity);
                dialog.setTitle("Editar Reseña");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();

                final EditText titulo = dialog.findViewById(R.id.txt_titulo);
                final EditText genero = dialog.findViewById(R.id.txt_genero);
                final EditText director = dialog.findViewById(R.id.txt_director);
                final EditText anio = dialog.findViewById(R.id.txt_anio);
                final RatingBar puntuacion = dialog.findViewById(R.id.rating_puntuacion);
                final EditText resenia = dialog.findViewById(R.id.txt_resenia);

                Button guardar = dialog.findViewById(R.id.btn_guardar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);

                _resenia = lista.get(pos);
                setId(_resenia.getId());
                titulo.setText(_resenia.getTitulo());
                genero.setText(_resenia.getGenero());
                director.setText(_resenia.getDirector());
                anio.setText(String.valueOf(_resenia.getAnio()));
                puntuacion.setRating(_resenia.getPuntuacion());
                resenia.setText(_resenia.getResenia());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            _resenia = new Resenia(
                                    getId(),
                                    titulo.getText().toString(),
                                    genero.getText().toString(),
                                    director.getText().toString(),
                                    puntuacion.getRating(),
                                    Integer.parseInt(anio.getText().toString()),
                                    resenia.getText().toString());
                            dao.editar(_resenia);
                            lista = dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(_activity, "Error al guardar los cambios", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(view.getTag().toString());
                _resenia = lista.get(posicion);
                setId(_resenia.getId());
                AlertDialog.Builder del = new AlertDialog.Builder(_activity);
                del.setMessage("Esta a punto de eliminar");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodo();
                        notifyDataSetChanged();
                    }
                });

                del.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return v;
    }
}
