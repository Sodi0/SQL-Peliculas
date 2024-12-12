package com.example.sql_peliculas;

//Aqui van los getter y los setter para poder
public class Resenia {
    int id;
    String titulo;
    String genero;
    String director;
    int anio;
    float puntuacion;
    String resenia;

    public Resenia() {

    }

    public Resenia(String titulo, String genero, String director, int anio, float puntuacion, String resenia) {
        this.titulo = titulo;
        this.genero = genero;
        this.director = director;
        this.anio = anio;
        this.puntuacion = puntuacion;
        this.resenia = resenia;
    }

    public Resenia(int id, String titulo, String genero, String director, float puntuacion, int anio, String resenia) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.director = director;
        this.puntuacion = puntuacion;
        this.anio = anio;
        this.resenia = resenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getResenia() {
        return resenia;
    }

    public void setResenia(String resenia) {
        this.resenia = resenia;
    }
}
