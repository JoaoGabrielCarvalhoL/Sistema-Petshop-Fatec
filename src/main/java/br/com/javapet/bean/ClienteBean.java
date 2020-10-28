package br.com.javapet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.ClienteDao;
import br.com.javapet.dao.PessoaDao;
import br.com.javapet.domain.Cliente;
import br.com.javapet.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable
{
	private Cliente cliente;
	
	private List<Cliente> clientes; 
	
	private List<Pessoa> pessoas; 
	
	
	public List<Cliente> getClientes() 
	{
		return clientes;
	}
	
	public void setClientes(List<Cliente> clientes) 
	{
		this.clientes = clientes;
	}
	
	public Cliente getCliente() 
	{
		return cliente;
	}
	
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
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
			ClienteDao clienteDao = new ClienteDao(); 
			clientes = clienteDao.listar("dataCadastro");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar listar clientes.");
			erro.printStackTrace();
		}
	}
	
	public void novo()
	{
		try
		{
			cliente = new Cliente(); 
			PessoaDao pessoaDao = new PessoaDao();
		
			pessoas = pessoaDao.listar("nome");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente.");
			erro.printStackTrace();
		}
	}
	
	public void salvar()
	{
		try
		{
			ClienteDao clienteDao = new ClienteDao(); 
			clienteDao.merge(cliente);
			
			cliente = new Cliente(); 
			clientes = clienteDao.listar("dataCadastro");
			
			PessoaDao pessoaDao = new PessoaDao(); 
			pessoas = pessoaDao.listar("nome");
			Messages.addGlobalInfo("Cliente salvo com sucesso!");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar um novo cliente");
			erro.printStackTrace();
		}
	}
	
	public void excluir()
	{
		
	}
	
	public void editar()
	{
		
	}
	
	
}
