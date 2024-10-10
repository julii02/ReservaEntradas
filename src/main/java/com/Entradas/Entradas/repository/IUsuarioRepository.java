package com.Entradas.Entradas.repository;

import com.Entradas.Entradas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IUsuarioRepository extends JpaRepository <Usuario , Long>{
    
}
