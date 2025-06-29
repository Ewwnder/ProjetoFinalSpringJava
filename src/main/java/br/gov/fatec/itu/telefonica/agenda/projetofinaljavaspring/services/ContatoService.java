package br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.services;

import java.util.List;

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
