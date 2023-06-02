package com.egg.pruebaSpring4.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {

    @Id
    ///Esta anotación hace que la id se genere de forma automatica 
    ///al momento que el repositorio persista la entidad
    @GeneratedValue(generator = "uuid")
    ///Con ésta anotación tenemos una estrategia alternativa
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;

    public Autor() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
