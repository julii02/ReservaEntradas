
package com.Entradas.Entradas.repository;

import com.Entradas.Entradas.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventoRepository extends JpaRepository <Evento , Long>{
    
}
