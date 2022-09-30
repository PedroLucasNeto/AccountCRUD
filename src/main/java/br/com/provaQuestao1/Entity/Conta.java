package br.com.provaQuestao1.Entity;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length= 100)
	private String nome;
	
	@Column(length= 15)
	private int agencia;
	
	@Column(length= 15)
	private int conta;
	
	@Column(length=255)
	private int saldo;

}
