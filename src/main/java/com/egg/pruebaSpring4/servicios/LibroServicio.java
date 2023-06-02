package com.egg.pruebaSpring4.servicios;

import com.egg.pruebaSpring4.entidades.Autor;
import com.egg.pruebaSpring4.entidades.Editorial;
import com.egg.pruebaSpring4.entidades.Libro;
import com.egg.pruebaSpring4.excepciones.MiExcepcion;
import com.egg.pruebaSpring4.repositorios.AutorRepositorio;
import com.egg.pruebaSpring4.repositorios.EditorialRepositorio;
import com.egg.pruebaSpring4.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crarLibro(Long isbn, String titulo, Integer ejemplares, String IdAutor, String IdEditorial) throws MiExcepcion{
        excepciones(isbn, titulo, ejemplares, IdAutor, IdEditorial);
        Autor autor = autorRepositorio.findById(IdAutor).get();
        Editorial editorial = editorialRepositorio.findById(IdEditorial).get();
        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String IdAutor, String IdEditorial) throws MiExcepcion {
        excepciones(isbn, titulo, ejemplares, IdAutor, IdEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(IdAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(IdEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

            Libro libro = new Libro();

            libro.setTitulo(titulo);
            libro.setEditorial(editorial);
            libro.setAutor(autor);
            libro.setEjemplares(ejemplares);
            
            libroRepositorio.save(libro);
        }
 
    
    private void excepciones(Long isbn, String titulo, Integer ejemplares, String IdAutor, String IdEditorial) throws MiExcepcion{
        
        if(isbn==null){
            throw new MiExcepcion("El Isbn no puede ser nulo");
        }
        
        if(isbn==null || titulo.isEmpty()){
            throw new MiExcepcion("El título no puede ser nulo o estar vacio");
        }
        
        if(ejemplares==null){
           throw new MiExcepcion("El numero de ejemplares no puede ser nulo"); 
        }
        
        if(IdAutor==null||IdAutor.isEmpty()){
           throw new MiExcepcion("El Id del autor no puede ser nulo o estar vacio"); 
        }
        
        if(IdEditorial==null||IdEditorial.isEmpty()){
           throw new MiExcepcion("El Id de la  no puede ser nulo o estar vacio"); 
        }
        
    }

}
