package com.funcionario.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.funcionario.domain.Funcionario;
import com.funcionario.domain.Telefone;
import com.funcionario.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioResources {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> listar(){	
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(funcionarioService.listar());
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Funcionario funcionario){
		funcionario = funcionarioService.salvar(funcionario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		Funcionario funcionario = funcionarioService.buscar(id);
				
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(funcionario);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){		
		funcionarioService.deletar(id);		
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Funcionario funcionario, @PathVariable("id") Long id){
		funcionario.setId(id);		
		funcionarioService.atualizar(funcionario);		
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}/telefones", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarTelefone(@Valid @PathVariable("id") Long funcionarioId,	@RequestBody Telefone telefone) {
		
		funcionarioService.salvarTelefone(funcionarioId, telefone);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}/telefones", method = RequestMethod.GET)
	public ResponseEntity<List<Telefone>> listarComentarios(@PathVariable("id")Long funcionarioId) {
		List<Telefone> telefones = funcionarioService.listarTelefones(funcionarioId);	
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(telefones);
	}
}
