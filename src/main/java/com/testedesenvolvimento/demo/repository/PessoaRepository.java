package com.testedesenvolvimento.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testedesenvolvimento.demo.model.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Integer>{

}
