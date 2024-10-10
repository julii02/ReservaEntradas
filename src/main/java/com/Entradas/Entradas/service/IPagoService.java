
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.model.Pago;
import java.util.List;

public interface IPagoService {
    
    public void savePago(Pago pago);
    
    public List <Pago> getPagos();
    
    public void deletePago(Long id);
    
    public Pago findById(Long id);
    
    public void editPago(Pago pago , Long id);
}
