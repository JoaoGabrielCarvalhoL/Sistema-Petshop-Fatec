package br.com.javapet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.ClienteDao;
import br.com.javapet.domain.Cliente;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable
{
	private Cliente cliente;
	
	private List<Cliente> clientes; 
	
	
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
			Messages.addGlobalError("Ocorreu um erro ao tentar listar clientes");
			erro.printStackTrace();
		}
	}
	
	
}
