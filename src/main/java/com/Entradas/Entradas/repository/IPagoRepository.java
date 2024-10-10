
package com.Entradas.Entradas.repository;

import com.Entradas.Entradas.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoRepository extends JpaRepository <Pago , Long>{
    
}
