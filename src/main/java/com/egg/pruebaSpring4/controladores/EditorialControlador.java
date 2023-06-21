package com.egg.pruebaSpring4.controladores;

import com.egg.pruebaSpring4.entidades.Editorial;
import com.egg.pruebaSpring4.excepciones.MiExcepcion;
import com.egg.pruebaSpring4.servicios.EditorialServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/registrar")//localhost:8080/editorial/registrar
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
         try {
             editorialServicio.crearEditorial(nombre);
         } catch (MiExcepcion ex) {
             Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
             return"editorial_form.html";
         }
        return"index.html";
   
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        
        modelo.addAttribute("editoriales", editoriales);
        
        return "editorial_list.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("editorial", editorialServicio.getOne(id));
        return "editorial_modificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo) {
        editorialServicio.modificarEditorial(nombre, id);
        return "redirect:../lista";
    }
    
    
}
