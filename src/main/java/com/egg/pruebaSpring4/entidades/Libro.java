package com.egg.pruebaSpring4.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/// Anotación que da cuenta de que éste libro va a ser una entidad.
@Entity
public class Libro {

    ///Anotación que declara que isbn va a ser la llave primaria del libro
    @Id
    private Long isbn;
    private String titulo;
    private Integer ejemplares;

    ///Anotación de que el dato es de tipo fecha.
    @javax.persistence.Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date alta;

    ///Muchos libros un autor
    @ManyToOne
    private Autor autor;
    ///Muchos libros una editorial
    @ManyToOne
    private Editorial editorial;

    public Libro() {
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

}
