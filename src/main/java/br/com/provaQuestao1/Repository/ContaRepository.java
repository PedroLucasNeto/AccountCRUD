package br.com.provaQuestao1.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.provaQuestao1.Entity.Conta;
@Repository
public interface ContaRepository extends CrudRepository <Conta, Integer>{

	List<Conta>findByNome(String name);

	Conta findById(int id);
	
}
