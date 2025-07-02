package br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.entities.Contato;
import br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.repositories.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public List<Contato> getAll(){
        return repository.findAll();
    }

    public List<Contato> filtroContatos(String tipo, String busca,  Boolean favorito, Boolean ordenarAZ) {
    List<Contato> contatos = repository.findAll();

    if (tipo!=null && !tipo.isEmpty()){
        switch (busca!=null ? busca.toLowerCase() : "name") {
            case "number":
                contatos = contatos.stream()
                        .filter(c -> c.getNumber() != null && c.getNumber().toLowerCase().contains(tipo.toLowerCase()))
                        .collect(Collectors.toList());
                break;
             case "email":
                contatos = contatos.stream()
                    .filter(c -> c.getEmail() != null && c.getEmail().toLowerCase().contains(tipo.toLowerCase()))
                    .collect(Collectors.toList());
                break;
            default:
                contatos = contatos.stream()
                    .filter(c -> c.getName() != null && c.getName().toLowerCase().contains(tipo.toLowerCase()))
                    .collect(Collectors.toList());
                
        }
    }

    if (Boolean.TRUE.equals(favorito)) {
        contatos = contatos.stream()
            .filter(Contato::getFavorito)
            .collect(Collectors.toList());
    }

    if (Boolean.TRUE.equals(ordenarAZ)) {
        contatos = contatos.stream()
            .sorted(Comparator.comparing(Contato::getName, String.CASE_INSENSITIVE_ORDER))
            .collect(Collectors.toList());
    }

    return contatos;
    }
    

    public Contato save(Contato contato){
        return repository.save(contato);
    }

    public void update(Contato contato, long id){
        Contato aux = repository.getReferenceById(id);
        aux.setName(contato.getName());
        aux.setNumber(contato.getNumber());
        aux.setApelido(contato.getApelido());
        aux.setEmail(contato.getEmail());
        aux.setEndereco(contato.getEndereco());
        aux.setGrupo(contato.getGrupo());
        aux.setInformacao(contato.getInformacao());
        aux.setFavorito(contato.getFavorito());
        aux.setDataAniversario(contato.getDataAniversario());

        repository.save(aux);
    }

    public void delete(long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Contato não cadastrado no sistema!");
        }
    }
    
}
