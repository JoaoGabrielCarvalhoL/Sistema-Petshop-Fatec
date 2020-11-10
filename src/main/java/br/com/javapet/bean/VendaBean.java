package br.com.javapet.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.ProdutoDao;
import br.com.javapet.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable 
{
	private List<Produto> produtos;
	
	public List<Produto> getProdutos() 
	{
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
	}
	
	@PostConstruct
	public void listar()
	{
		try
		{
			ProdutoDao produtoDao = new ProdutoDao(); 
			produtos = produtoDao.listar("descricao");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}
}
