package br.com.javapet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.CidadeDao;
import br.com.javapet.domain.Cidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable
{
	private List<Cidade> cidades; 
	
	public List<Cidade> getCidades() 
	{
		return cidades;
	}
	
	public void setCidades(List<Cidade> cidades) 
	{
		this.cidades = cidades;
	}
	
	@PostConstruct
	public void listar()
	{
		try 
		{
			CidadeDao cidadeDao = new CidadeDao(); 
			cidades = cidadeDao.listar();
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}
}
