package com.resuadam2.solexampmul.ejer1.model;

public class Album {
    private String titulo, artista;
    private int id, numCanciones, valoracion;

    public Album(int id, String titulo, String artista, int numCanciones, int valoracion) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.numCanciones = numCanciones;
        this.valoracion = valoracion;
    }

    public Album(String titulo, String artista, int numCanciones, int valoracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.numCanciones = numCanciones;
        this.valoracion = valoracion;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getNumCanciones() {
        return numCanciones;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setNumCanciones(int numCanciones) {
        this.numCanciones = numCanciones;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "Album{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", numCanciones=" + numCanciones +
                ", valoracion=" + valoracion +
                '}';
    }
}
