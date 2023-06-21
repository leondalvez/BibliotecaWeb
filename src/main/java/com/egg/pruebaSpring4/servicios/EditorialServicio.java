package com.egg.pruebaSpring4.servicios;

import com.egg.pruebaSpring4.entidades.Editorial;
import com.egg.pruebaSpring4.excepciones.MiExcepcion;
import com.egg.pruebaSpring4.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiExcepcion {
        if (nombre.isEmpty()|| nombre==null) {
            throw new MiExcepcion("El valor de Nombre no puede ser nulo");
        }
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList();

        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }
    
    @Transactional
    public void modificarEditorial(String nombre, String id) {

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }
    
    public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
    }
}
