package com.testedesenvolvimento.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testedesenvolvimento.demo.error.NotFoundError;
import com.testedesenvolvimento.demo.error.ValidationError;
import com.testedesenvolvimento.demo.model.ContaModel;
import com.testedesenvolvimento.demo.model.PessoaModel;
import com.testedesenvolvimento.demo.repository.ContaRepository;
import com.testedesenvolvimento.demo.repository.PessoaRepository;

@Service
public class ContaService {

	@Autowired
	ContaRepository _repository;

	@Autowired
	PessoaRepository _pessoaRepository;

	public ContaModel criarConta(ContaModel conta ) { 
		conta.setDataHoraCriacao(new Date());
		conta.setSaldo(0);
		verificarSePessoaExiste(conta.getPessoaId());
		return _repository.save(conta);

	}

	public ContaModel obterConta(int id) {
		verificarSeContaExiste(id);
		return _repository.getById(id);
	}

	private void verificarSeContaExiste(int id) {
		var conta = _repository.findById(id);

		if(conta.isEmpty()) {
			throw new NotFoundError("A conta não existe. id:"+ id);
		}

	}

	public ContaModel inserirCredito(int id, float valor) {

		if(valor <= 0) {
			throw new ValidationError("O valor menores ou iguais a 0 não são validos. Valor:" + valor);
		}

		var conta = obterConta(id);

		verificarSePessoaExiste(conta.getPessoaId());

		var pessoa = _pessoaRepository.getById(conta.getPessoaId());


		if (pessoa.getTipoPessoa() == "PF" && valor > 500) {
			throw new ValidationError("Valor ultrapassa limite para o tipo pessoa. Valor :" + valor +
					" Tipo Pessoa: PF");
		}
		else if (pessoa.getTipoPessoa() == "PJ" && valor > 2000) {
			throw new ValidationError("Valor ultrapassa limite para o tipo pessoa. Valor :" + valor +
					" Tipo Pessoa: PJ");	

		}
		var saldo = conta.getSaldo();
		saldo += valor;
		conta.setSaldo(saldo);
		_repository.save(conta);

		return obterConta(id);

	}

	private void verificarSePessoaExiste(int pessoaId) {
		var pessoa = _pessoaRepository.findById(pessoaId);

		if(pessoa.isEmpty()) {
			throw new NotFoundError("Pessoa não existe. ID:"+ pessoaId);
		}

	}

	public List<PessoaModel> inicializarTeste() {
		var listaPessoas = new ArrayList<PessoaModel>();
		listaPessoas.add(new PessoaModel("05307126360","","", "Ana Karolliny","PF", new Date()));

		listaPessoas.add(new PessoaModel("000000000000000","Jurandir LTDA","PetShop Jurandir",null,"PJ",null));      
		return _pessoaRepository.saveAll(listaPessoas);
	}

	public ContaModel transferir(int origemId, int destinoId,float valor) {

		verificarSeContaExiste(destinoId);
		verificarSeContaExiste(origemId);
		
		inserirCredito(destinoId, valor);
		retirarCredito(origemId, valor);

		return obterConta(origemId);
	}

	private void retirarCredito(int id, float valor) {
		var conta = obterConta(id);
		var saldo = conta.getSaldo();
		saldo -= valor;
		conta.setSaldo(saldo);
		_repository.save(conta);





	}



}
