
package com.Entradas.Entradas.service;

import com.Entradas.Entradas.exceptions.EntradasException;
import com.Entradas.Entradas.exceptions.ExceptionDetails;
import com.Entradas.Entradas.model.Evento;
import com.Entradas.Entradas.repository.IEventoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService implements IEventoService{

    @Autowired
    private IEventoRepository eventoRepo;
    
    @Override
    public List<Evento> getEventos() {
        List <Evento> listaEventos = new ArrayList<>();
        
        listaEventos = eventoRepo.findAll();
        
        if(listaEventos == null){
            throw new EntradasException("La lista de eventos esta vacia" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error"));
        }
        return listaEventos;
    }

    @Override
    public void saveEvento(Evento evento) {
        try{
            eventoRepo.save(evento);
        }
        catch(Exception e){
             throw new EntradasException("Error al guardar el evento" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }

    @Override
    public Evento findById(Long id) {
        Evento evento = eventoRepo.findById(id).orElse(null);
        if(evento == null){
            throw new EntradasException("evento no encontrado" , 
                     new ExceptionDetails("La ID ingresada no existe","Error") );
        }
        return evento;
    }

    @Override
    public void deleteEvento(Long id) {
        this.findById(id);
        eventoRepo.deleteById(id);
    }

    @Override
    public void editEvento(Evento evento , Long id) {
        Evento even = this.findById(id);
        
        even.setIdEvento(id);
        even.setCapacidadDisponible(evento.getCapacidadDisponible());
        even.setCapacidadTotal(evento.getCapacidadTotal());
        even.setFechaFin(evento.getFechaFin());
        even.setFechaInicio(evento.getFechaInicio());
        even.setNombre(evento.getNombre());
        
        try{
            eventoRepo.save(even);
        }  
        catch(Exception e){
            throw new EntradasException("Error al guardar el evento" , 
                     new ExceptionDetails("Ha ocurrido un error inesparado","Error") , e);
        }
    }
    
}
