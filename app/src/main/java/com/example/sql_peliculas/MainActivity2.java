package com.example.sql_peliculas;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private TextView tv_NombreUser;
    private Button salir;
    DaoContacto dao;
    Adaptador adaptador;
    ArrayList<Resenia> lista;
    Resenia resenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        //Nombre usuario
        tv_NombreUser = findViewById(R.id.tv_user);
        String dato =getIntent().getStringExtra("dato");
        tv_NombreUser.setText("Bienvenido " + dato);

        salir = findViewById(R.id.btn_exit);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });


        dao = new DaoContacto(MainActivity2.this);
        lista = dao.verTodo();
        adaptador= new Adaptador(lista, dao, this);
        ListView list = findViewById(R.id.lista);
        Button insertar = findViewById(R.id.btn_add);
        list.setAdapter(adaptador);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity2.this);
                dialog.setTitle("Nuevo Registro");
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
                guardar.setText("Agregar");
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            MainActivity2.this.resenia = new Resenia(
                                    titulo.getText().toString(),
                                    genero.getText().toString(),
                                    director.getText().toString(),
                                    Integer.parseInt(anio.getText().toString()),
                                    puntuacion.getRating(),
                                    resenia.getText().toString());
                            dao.insertar(MainActivity2.this.resenia);
                            lista = dao.verTodo();
                            adaptador.notifyDataSetChanged();
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
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
    }
}