package br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.entities.Contato;
import br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.services.ContatoService;

@CrossOrigin
@RestController
@RequestMapping("contatos")
public class ContatoController {


    @Autowired
    private ContatoService service;

    @GetMapping
    public ResponseEntity<List<Contato>> getContatos(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String busca,
    @RequestParam(required = false) Boolean favorito,
    @RequestParam(required = false) Boolean ordenarAZ) {

    boolean semFiltro = (name == null && busca == null && favorito == null && ordenarAZ == null);

    if (semFiltro) {
        return ResponseEntity.ok(service.getAll());
    }

    return ResponseEntity.ok(service.filtroContatos(name, busca, favorito, ordenarAZ));
    }


    @PostMapping
    public ResponseEntity<Contato> save(@RequestBody Contato contato){
        return ResponseEntity.created(null).body(service.save(contato));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void>delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void>update(@PathVariable long id, @RequestBody Contato contato){
        service.update(contato, id);
        return ResponseEntity.noContent().build();
    }


}
