package com.example.sql_peliculas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText txt_nombre;
    private Button btn_Ingresar;
    ProgressBar progressBar;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txt_nombre = findViewById(R.id.txt_Nombre);
        progressBar = findViewById(R.id.progressBar);
        btn_Ingresar = findViewById(R.id.btn_Ingresar);
        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                validarNombre();
            }
        });
    }

    private void validarNombre(){
        String nombre = txt_nombre.getText().toString();
        if (nombre.isEmpty()) {
            Toast.makeText(this, "Ingresa tu nombre antes de comenzar", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        Toast.makeText(this,"Gracias", Toast.LENGTH_SHORT).show();
        iniciarProgressBar();
    }

    private void iniciarActividad(){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("dato", txt_nombre.getText().toString());
        startActivity(i);
    }

    private void iniciarProgressBar(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                counter++;
                                progressBar.setProgress(counter);
                                if (counter==30){
                                    timer.cancel();
                                    iniciarActividad();
                                }
                            }
                        });
                    }
                };
                timer.schedule(timerTask,30,30);
            }
        });
    }
}