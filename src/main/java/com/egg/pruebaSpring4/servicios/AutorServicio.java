package com.egg.pruebaSpring4.servicios;

import com.egg.pruebaSpring4.entidades.Autor;
import com.egg.pruebaSpring4.excepciones.MiExcepcion;
import com.egg.pruebaSpring4.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearAutor(String nombre) throws MiExcepcion {
        if (nombre.isEmpty() || nombre==null) {
            throw new MiExcepcion("El valor de Nombre no puede ser nulo");
        }
        
        Autor autor = new Autor();
        autor.setNombre(nombre);
        
        autorRepositorio.save(autor);

    }
    
    public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        
        autores = autorRepositorio.findAll();
        
        return autores;
    }
    
    public void modificarAutor(String nombre, String id) {

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }
    
}
