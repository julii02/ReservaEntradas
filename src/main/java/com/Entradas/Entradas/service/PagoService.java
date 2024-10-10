
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.exceptions.ExceptionDetails;
import com.Entradas.Entradas.model.Entrada;
import com.Entradas.Entradas.model.Pago;
import com.Entradas.Entradas.repository.IEntradaRepository;
import com.Entradas.Entradas.repository.IPagoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService implements IPagoService{

    @Autowired
    private IPagoRepository pagoRepo;
    
    @Autowired 
    private IEntradaRepository entradaRepo;
    
    @Override
    public void savePago(Pago pago) {
        Long idEntrada = pago.getEntrada().getIdEntrada();
        Entrada entrada = entradaRepo.findById(idEntrada).orElse(null);
        
        pago.setMonto(entrada.getPrecio());
        
        try{
           pagoRepo.save(pago);
        }
        catch(Exception e){
             throw new EntradasException("Error al guardar el pago" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }

    @Override
    public List<Pago> getPagos() {
        List <Pago> listaPagos = pagoRepo.findAll();
        
        if(listaPagos == null){
            throw new EntradasException("La lista de usuarios esta vacia" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error"));
        }
        return listaPagos;
    }

    @Override
    public void deletePago(Long id) {
        this.findById(id);
        pagoRepo.deleteById(id);
    }

    @Override
    public Pago findById(Long id) {
        Pago pago = pagoRepo.findById(id).orElse(null);
        if(pago == null){
            throw new EntradasException("Pago no encontrado" , 
                     new ExceptionDetails("La ID ingresada no existe","Error") );
        }
        return pago;
    }

    @Override
    public void editPago(Pago pago, Long id) {
        Pago pag = this.findById(id);
        
        pag.setEntrada(pago.getEntrada());
        pag.setFechaPago(pago.getFechaPago());
        pag.setMetodoPago(pago.getMetodoPago());
        pag.setMonto(pago.getMonto());
        
        try{
            pagoRepo.save(pag);
        }  
        catch(Exception e){
            throw new EntradasException("Error al guardar el pago" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }
    
}
