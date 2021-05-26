package com.testedesenvolvimento.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testedesenvolvimento.demo.model.ContaModel;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, Integer> {

}
