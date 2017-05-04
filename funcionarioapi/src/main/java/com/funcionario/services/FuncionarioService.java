package com.funcionario.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.funcionario.domain.Funcionario;
import com.funcionario.domain.Telefone;
import com.funcionario.repository.FuncionarioRepository;
import com.funcionario.repository.TelefoneRepository;
import com.funcionario.services.exceptions.FuncionarioException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	public List<Funcionario> listar(){		
		return funcionarioRepository.findAll();
	}
	
	public Funcionario buscar(Long id){
		Funcionario funcionario = funcionarioRepository.findOne(id);
		
		if(funcionario == null){
			throw new FuncionarioException("Funcionario não existe.");
		}
		
		return funcionario;
	}
	
	public Funcionario salvar(Funcionario funcionario){
		funcionario.setId(null);
		return funcionarioRepository.save(funcionario);		
	}
	
	public void deletar(Long id){
		try{
			funcionarioRepository.delete(id);
		}catch (EmptyResultDataAccessException e) {
			throw new FuncionarioException("Funcionario não existe.");
		}
	}
	
	public void atualizar(Funcionario funcionario){
		verificaExistencia(funcionario);
		funcionarioRepository.save(funcionario);
	}
	
	private void verificaExistencia(Funcionario funcionario){
		buscar(funcionario.getId());
	}
	
	public Telefone salvarTelefone(Long funcionarioId, Telefone telefone) {		
		Funcionario funcionario = buscar(funcionarioId);
		
		telefone.setFuncionario(funcionario);		
		return telefoneRepository.save(telefone);
	}
	
	public List<Telefone> listarTelefones(Long funcionarioId) {
		Funcionario funcionario = buscar(funcionarioId);
		
		return funcionario.getTelefones();
	}
}
