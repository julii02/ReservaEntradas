
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.exceptions.ExceptionDetails;
import com.Entradas.Entradas.model.Entrada;
import com.Entradas.Entradas.model.Evento;
import com.Entradas.Entradas.model.Pago;
import com.Entradas.Entradas.repository.IEntradaRepository;
import com.Entradas.Entradas.repository.IEventoRepository;
import com.Entradas.Entradas.repository.IPagoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntradaService implements IEntradaService{

    @Autowired
    private IEntradaRepository entradaRepo;
    
    @Autowired
    private IEventoRepository eventoRepo;
    
    @Autowired
    private IPagoRepository pagoRepo;
    
    @Override
    public void saveEntrada(Entrada entrada) {
        
        Long idEvento = entrada.getEvento().getIdEvento();
        Evento evento = eventoRepo.findById(idEvento).orElse(null);
        
        
        if(evento == null){
            throw new EntradasException("El evento no existe" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") );
        }
        
        if(evento.getCapacidadDisponible() < 1){
            throw new EntradasException("El evento no tiene capacidad" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") );
        }
        else{
            try{
                evento.setCapacidadDisponible(evento.getCapacidadDisponible() - 1);
                eventoRepo.save(evento);
                entradaRepo.save(entrada);
        }
        catch(Exception e){
             throw new EntradasException("Error al guardar la entrada" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
        }
        
    }

    @Override
    public List<Entrada> getEntradas() {
        List <Entrada> listaEntradas = entradaRepo.findAll();
        
        if(listaEntradas == null){
            throw new EntradasException("La lista de entradas esta vacia" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error"));
        }
        return listaEntradas;
    }

    @Override
    public void deleteEntrada(Long id) {
        Entrada entrada = this.findById(id);
        Evento evento = entrada.getEvento();
        evento.setCapacidadDisponible(evento.getCapacidadDisponible() + 1);
        eventoRepo.save(evento);
        List <Pago> listaPagos = pagoRepo.findAll();
        
        for(Pago pago : listaPagos){
            pago.getEntrada().getIdEntrada().equals(id);
            pagoRepo.deleteById(pago.getId());
        }
        entradaRepo.deleteById(id);
    }

    @Override
    public Entrada findById(Long id) {
        Entrada entrada = entradaRepo.findById(id).orElse(null);
        if(entrada == null){
            throw new EntradasException("Entrada no encontrada" , 
                     new ExceptionDetails("La ID ingresada no existe","Error") );
        }
        return entrada;
    }
    //VER QUE PASO!
    @Override
    public void editEntrada(Entrada entrada , Long id) {
        Entrada entra = this.findById(id);
        
        entra.setCategoriaEntrada(entrada.getCategoriaEntrada());
        entra.setEvento(entrada.getEvento());
        entra.setPrecio(entrada.getPrecio());
        entra.setUsuario(entrada.getUsuario());
        
        try{
            entradaRepo.save(entra);
        }  
        catch(Exception e){
            throw new EntradasException("Error al guardar el usuario" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }
    
}
