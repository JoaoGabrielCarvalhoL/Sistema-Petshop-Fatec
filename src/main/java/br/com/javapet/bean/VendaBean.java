package br.com.javapet.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.javapet.dao.ProdutoDao;
import br.com.javapet.domain.ItemVenda;
import br.com.javapet.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable 
{
	private List<Produto> produtos;
	
	private List<ItemVenda> itensVenda; 
	
	public List<Produto> getProdutos() 
	{
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
	}
	
	public List<ItemVenda> getItensVenda() 
	{
		return itensVenda;
	}
	
	public void setItensVenda(List<ItemVenda> itensVenda)
	{
		this.itensVenda = itensVenda;
	}
	
	@PostConstruct
	public void listar()
	{
		try
		{
			ProdutoDao produtoDao = new ProdutoDao(); 
			produtos = produtoDao.listar("descricao");
			itensVenda = new ArrayList<ItemVenda>();
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void adicionar(ActionEvent evento)
	{
		try
		{
			Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ItemVenda itemVenda = new ItemVenda(); 
			itemVenda.setValorParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));
			
			itensVenda.add(itemVenda);
			
			
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar adicionar produtos ao carrinho");
			erro.printStackTrace();
		}
	}
}
