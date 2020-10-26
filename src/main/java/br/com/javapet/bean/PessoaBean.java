package br.com.javapet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.PessoaDao;
import br.com.javapet.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable
{
	private Pessoa pessoa; 
	
	private List<Pessoa> pessoas; 
	
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
		pessoa = new Pessoa(); 
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
