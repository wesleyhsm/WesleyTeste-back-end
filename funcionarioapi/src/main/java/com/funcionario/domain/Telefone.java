package com.funcionario.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="telefone")
public class Telefone{
	
	private Long id;		
	private String numero;
	private Funcionario funcionario;
	private Date dtCriacao;
	private Date dtAlteracao;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message="O campo numero não pode ser vazio.")
	@Size(max=15, message="O numero não conter mais de 15 caracteres.")
	@Column(name="numero")
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_funcionario")
	@JsonIgnore	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_criacao")
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	public Date getDtCriacao() {
		return dtCriacao;
	}	
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_alteracao")
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
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
}
