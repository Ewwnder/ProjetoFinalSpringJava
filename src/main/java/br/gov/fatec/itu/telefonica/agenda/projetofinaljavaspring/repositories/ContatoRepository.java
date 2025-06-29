package br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.fatec.itu.telefonica.agenda.projetofinaljavaspring.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
    
}
