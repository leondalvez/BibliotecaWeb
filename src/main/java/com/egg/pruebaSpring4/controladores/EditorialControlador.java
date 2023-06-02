package com.egg.pruebaSpring4.controladores;


import com.egg.pruebaSpring4.excepciones.MiExcepcion;
import com.egg.pruebaSpring4.servicios.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {
    
    @Autowired
    AutorServicio editorialServicio;

    @GetMapping("/registrar")//localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
         try {
             editorialServicio.crearAutor(nombre);
         } catch (MiExcepcion ex) {
             Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
             return"editorial_form.html";
         }
        return"index.html";
    }

}
