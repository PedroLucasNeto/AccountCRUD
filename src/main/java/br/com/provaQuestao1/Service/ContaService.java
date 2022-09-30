package br.com.provaQuestao1.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.provaQuestao1.Entity.Conta;
import br.com.provaQuestao1.Entity.User;
import br.com.provaQuestao1.Exceptions.GenericException;
import br.com.provaQuestao1.Repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;

	public List<Conta> listaPorNome(String name) {
		List<Conta> list = contaRepository.findByNome(name);
		return list;
	}

	public Conta encontraPeloId(int id) {
		Conta conta = contaRepository.findById(id);
		if (conta == null) {
			throw new GenericException("Não foi possível encontrar tarefa.");
		}
		return conta;
	}

	public Conta novaConta(Conta conta) {

		if (this.verificaNome(conta.getNome()) != true) {
			contaRepository.save(conta);
		} else {
			conta = null;
		}
		return conta;
	}

	public Conta atualizaConta(int id,Conta contaAtt) throws GenericException {
		Conta conta = this.encontraPeloId(id);
		conta.setNome(contaAtt.getNome());
		contaRepository.save(conta);
		return conta;
	}

	public List<Conta> findAll() {
		List<Conta> list = (List<Conta>) contaRepository.findAll();
		if (list == null || list.isEmpty()) {
			throw new GenericException("Não existe nenhuma conta.");
		}
		return list;
	}

	public void removeConta(int id) {

		if (contaRepository.findById(id) == null) {
			throw new GenericException("Conta a ser excluída não existe");
		}
		contaRepository.deleteById(id);
	}

	public boolean verificaNome(String nome) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User[]> lista = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users",User[].class);

		List<User> lista2 = Arrays.stream(Objects.requireNonNull(lista.getBody())).toList();
		for (User user : lista2) {

			if (user.getName().equals(nome)) {
				return true;
			}
		}
		return false;
	}

}
