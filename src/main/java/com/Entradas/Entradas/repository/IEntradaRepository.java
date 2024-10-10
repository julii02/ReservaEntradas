
package com.Entradas.Entradas.repository;

import com.Entradas.Entradas.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntradaRepository extends JpaRepository <Entrada , Long>{
    
}
