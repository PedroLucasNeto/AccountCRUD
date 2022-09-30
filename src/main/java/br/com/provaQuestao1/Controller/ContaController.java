package br.com.provaQuestao1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.provaQuestao1.Entity.Conta;
import br.com.provaQuestao1.Exceptions.GenericException;
import br.com.provaQuestao1.Service.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	@Autowired
	private ContaService contaService;
	

	@GetMapping(value = "/{name}")
	public ResponseEntity<List<Conta>> buscaPeloNome(@PathVariable String name) {
		List<Conta> listaRetorno = contaService.listaPorNome(name);
		if (listaRetorno == null || listaRetorno.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Conta>>(listaRetorno, HttpStatus.OK);
	}

		
	@GetMapping
	public ResponseEntity<List<Conta>> buscaTodos() {
		
		List<Conta> listaRetorno = contaService.findAll();
		if (listaRetorno == null || listaRetorno.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Conta>>(listaRetorno, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Conta> buscaPeloId(@PathVariable int id) {
		
		Conta conta = contaService.encontraPeloId(id);
		if (conta == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}


	@PostMapping(value = "/create")
	public ResponseEntity<Conta> criaConta(@RequestBody Conta conta) {
		Conta contaNova = contaService.novaConta(conta);
		return new ResponseEntity<Conta>(contaNova, HttpStatus.CREATED);
	}

	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Conta> alteraConta(@PathVariable int id, @RequestBody Conta toDo) throws GenericException {
		contaService.atualizaConta(id,toDo);
		return new ResponseEntity<Conta>(toDo, HttpStatus.OK);
	}
	

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Conta> removeConta(@PathVariable int id) {
		contaService.removeConta(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
