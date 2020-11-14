package br.com.javapet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.AdministradorDao;
import br.com.javapet.dao.PessoaDao;
import br.com.javapet.domain.Administrador;
import br.com.javapet.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AdministradorBean implements Serializable 
{
	private Administrador administrador; 
	
	private List<Administrador> administradores;
	
	private List<Pessoa> pessoas;

	public Administrador getAdministrador() 
	{
		return administrador;
	}

	public void setAdministrador(Administrador administrador) 
	{
		this.administrador = administrador;
	}

	public List<Administrador> getAdministradores() 
	{
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores)
	{
		this.administradores = administradores;
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
			AdministradorDao administradorDao = new AdministradorDao(); 
			administradores = administradorDao.listar("nomeFantasia");
			
		}catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar administradores");
			erro.printStackTrace();
		}
	}
	
	public void novo()
	{
		try
		{
			administrador = new Administrador(); 
			PessoaDao pessoaDao = new PessoaDao(); 
		
			pessoas = pessoaDao.listar("nome");
			
		}catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo administrador");
			erro.printStackTrace();
		}
	}
	
	public void salvar()
	{
		try
		{
			AdministradorDao administradorDao = new AdministradorDao(); 
			administradorDao.merge(administrador);
			
			administrador = new Administrador(); 
			administradores = administradorDao.listar("nomeFantasia");
			
			PessoaDao pessoaDao = new PessoaDao();
			pessoas = pessoaDao.listar("nome"); 
			Messages.addGlobalInfo("Administrador salvo com sucesso!");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar um novo administrador");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento)
	{
		try
		{
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");
			AdministradorDao administradorDao = new AdministradorDao(); 
			administradorDao.excluir(administrador);
			
			administradores = administradorDao.listar("nomeFantasia"); 
			Messages.addGlobalInfo("Administrador salvo com sucesso!");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalInfo("Ocorreu um erro ao tentar excluir administrador");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento)
	{
		try
		{
			administrador = (Administrador) evento.getComponent().getAttributes().get("administradorSelecionado");
			
		}catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar alterar administrador");
			erro.printStackTrace();
		}
	}
	
}
