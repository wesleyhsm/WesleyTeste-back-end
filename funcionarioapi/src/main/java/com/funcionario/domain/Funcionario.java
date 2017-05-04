package com.funcionario.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="funcionario")
public class Funcionario {
	
	private Long id;
	private String nome;
	private Date dataNascimento;
	private Double salario;
	private Date dtCriacao;
	private Date dtAlteracao;
	private List<Telefone> telefones;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message="O campo nome não pode ser vazio.")
	@Size(max=60, message="O nome não conter mais de 60 caracteres.")
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull(message="O campo data nascimento é obrigatorio.")
	@Temporal(TemporalType.DATE)	
	@Column(name="data_nascimento")
	@JsonFormat(pattern="dd/MM/yyyy")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@NotNull(message="O campo salario é obrigatorio.")
	@Column(name="salario")
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")	
	public Date getDtCriacao() {
		return dtCriacao;
	}	
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	
	@PrePersist
	@PreUpdate
	public void configuraDataCriacaoAlteracao(){
		this.dtAlteracao = new Date();
		
		if(this.dtCriacao == null){
			this.dtCriacao = new Date();
		}
	}
	
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY)
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}	
}
