
package com.Entradas.Entradas.controller;

import com.Entradas.Entradas.model.Evento;
import com.Entradas.Entradas.service.IEventoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoController {
    @Autowired
    private IEventoService eventoServ;
    
    @PostMapping("/evento/guardar")
    public String saveEvento(@Valid @RequestBody Evento evento){
        eventoServ.saveEvento(evento);
        return "Evento guardado correctamente";
    }
    
    @GetMapping("/evento/traer")
    public List <Evento> getEventos(){
        return eventoServ.getEventos();
    }
    
    @GetMapping("/evento/traer/{idEvento}")
    public Evento traerEventoPorId(@PathVariable Long idEvento){
        return eventoServ.findById(idEvento);
    }
    
    @DeleteMapping("/evento/borrar/{idEvento}")
    public String deleteEvento(@PathVariable Long idEvento){
        eventoServ.deleteEvento(idEvento);
        return "evento eliminado correctamente";
    }
    
    @PutMapping("/evento/editar/{idEvento}")
    public String editEvento(@Valid @RequestBody Evento evento , @PathVariable Long idEvento){
        eventoServ.editEvento(evento,idEvento);   
        return "Evento editado correctamente";
    }
}
