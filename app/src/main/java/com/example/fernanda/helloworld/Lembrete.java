package com.example.fernanda.helloworld;

/**
 * Created by Fernanda on 28/04/2015.
 */
public class Lembrete {
   private Long id;
   private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " - " + titulo;
    }
}
