package br.com.javapet.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.javapet.dao.AgendamentoDao;
import br.com.javapet.dao.ClienteDao;
import br.com.javapet.dao.ProdutoDao;
import br.com.javapet.domain.Agendamento;
import br.com.javapet.domain.Cliente;
import br.com.javapet.domain.Produto;

@ManagedBean
@ViewScoped 
public class AgendamentoBean 
{
	private Agendamento agendamento; 
	
	private ScheduleModel agendamentos; 
	
	private List<Cliente> clientes; 
	
	private List<Produto> produtos; 
	
	public Agendamento getAgendamento() 
	{
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) 
	{
		this.agendamento = agendamento;
	}

	public List<Cliente> getClientes() 
	{
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) 
	{
		this.clientes = clientes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public ScheduleModel getAgendamentos() 
	{
		return agendamentos;
	}
	
	public void setAgendamentos(ScheduleModel agendamentos) 
	{
		this.agendamentos = agendamentos;
	}
	
	
	
	
	@PostConstruct
	public void listar()
	{
		agendamentos = new DefaultScheduleModel();
	}
	
	
	public void novo(SelectEvent evento)
	{
		agendamento = new Agendamento(); 
		
		agendamento.setDataAgendamento((Date) evento.getObject());
		
		ClienteDao clienteDao = new ClienteDao(); 
		clientes = clienteDao.listar();
		
		ProdutoDao produtoDao = new ProdutoDao(); 
		produtos = produtoDao.listar();
		 
	}
	
	public void salvar()
	{
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(agendamento.getDataAgendamento());
		calendar.add(Calendar.DATE, 1);
		agendamento.setDataAgendamento(calendar.getTime());
		
		try
		{
			AgendamentoDao agendamentoDao = new AgendamentoDao(); 
			agendamentoDao.salvar(agendamento);
			Messages.addGlobalInfo("Agendamento salvo com sucesso!");
		}
		catch(RuntimeException erro)
		{
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o agendamento");
			erro.printStackTrace();
		}
	}
}

