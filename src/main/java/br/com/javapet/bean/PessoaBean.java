package br.com.javapet.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.EstadoDao;
import br.com.javapet.dao.PessoaDao;
import br.com.javapet.domain.Cidade;
import br.com.javapet.domain.Estado;
import br.com.javapet.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable
{
	private Pessoa pessoa; 
	
	private List<Pessoa> pessoas; 
	
	private List<Estado> estados; 
	
	private List<Cidade> cidades; 
	
	
	public List<Cidade> getCidades() 
	{
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) 
	{
		this.cidades = cidades;
	}
	
	public List<Estado> getEstados()
	{
		return estados;
	}
	
	public void setEstados(List<Estado> estados) 
	{
		this.estados = estados;
	}
	
	public Pessoa getPessoa() 
	{
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) 
	{
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() 
	{
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) 
	{
		this.pessoas = pessoas;
	}
	
	@PostConstruct
	public void listar()
	{
		try 
		{
			PessoaDao pessoaDao = new PessoaDao(); 
			pessoas = pessoaDao.listar();
		
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}
	
	public void novo()
	{
		try
		{
			pessoa = new Pessoa(); 
			EstadoDao estadoDao = new EstadoDao(); 
			estados = estadoDao.listar(); 
		
			cidades = new ArrayList<>();
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento)
	{
		
	}
	
	public void salvar()
	{
		
	}
	
	public void excluir(ActionEvent evento)
	{
		
	}
}
