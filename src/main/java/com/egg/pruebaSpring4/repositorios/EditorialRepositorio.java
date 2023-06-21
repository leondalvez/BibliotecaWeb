
package com.egg.pruebaSpring4.repositorios;

import com.egg.pruebaSpring4.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    
}