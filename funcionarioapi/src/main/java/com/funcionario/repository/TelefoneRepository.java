package com.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.funcionario.domain.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
