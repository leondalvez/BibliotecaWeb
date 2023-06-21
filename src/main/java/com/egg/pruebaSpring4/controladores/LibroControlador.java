package com.egg.pruebaSpring4.controladores;

import com.egg.pruebaSpring4.entidades.Autor;
import com.egg.pruebaSpring4.entidades.Editorial;
import com.egg.pruebaSpring4.entidades.Libro;
import com.egg.pruebaSpring4.excepciones.MiExcepcion;
import com.egg.pruebaSpring4.servicios.AutorServicio;
import com.egg.pruebaSpring4.servicios.EditorialServicio;
import com.egg.pruebaSpring4.servicios.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(
            @RequestParam(required = false) Long isbn,
            @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares,
            @RequestParam String IdAutor, @RequestParam String IdEditorial,
            ModelMap modelo) {

        try {
            libroServicio.crarLibro(isbn, titulo, ejemplares,
                    IdAutor, IdEditorial);

            modelo.put("exito", "El libro fue cargado correctamente.");

        } catch (MiExcepcion ex) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE,
                    null, ex);
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            modelo.put("error", ex.getMessage());
            return "libro_form.html";
        }
        return "libro_form.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List <Libro> libros = libroServicio.listarLibros();
        
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
    }
}
