package com.testedesenvolvimento.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testedesenvolvimento.demo.model.ContaModel;
import com.testedesenvolvimento.demo.model.InserirCreditoModel;
import com.testedesenvolvimento.demo.model.PessoaModel;
import com.testedesenvolvimento.demo.service.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	@Autowired
	ContaService _service;
	
	@PostMapping
	public ContaModel criarconta(@RequestBody ContaModel conta) {
		return _service.criarConta(conta);
	}
	
	@GetMapping (value = "/{id}")
	public ContaModel obterConta(@PathVariable int id) {
		return _service.obterConta(id);
	}
		
	@PatchMapping(value = "/{id}/credito")
	public ContaModel inserirCredito(@PathVariable int id, @RequestBody InserirCreditoModel model) {
		return _service.inserirCredito(id, model.getValor());
	}
	
	@PatchMapping(value = "/{origemId}/transferencia/{destinoId}")
	public ContaModel transferir(@PathVariable int origemId,
			@PathVariable int destinoId,
			@RequestBody InserirCreditoModel model) {
		return _service.transferir(origemId, destinoId, model.getValor());
	}
	
	@GetMapping(value = "inicializar-teste")
	public List<PessoaModel> inicializarTeste(){
		return _service.inicializarTeste();
	}
}
