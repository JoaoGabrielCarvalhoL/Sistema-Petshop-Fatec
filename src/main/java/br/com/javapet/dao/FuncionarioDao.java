package br.com.javapet.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.javapet.domain.Funcionario;
import br.com.javapet.util.HibernateUtil;

public class FuncionarioDao extends GenericDao<Funcionario>
{
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarOrdenado()
	{
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try
		{
			Criteria consulta = sessao.createCriteria(Funcionario.class); 
			consulta.createAlias("pessoa", "p");
			consulta.addOrder(Order.asc("p.nome"));
			List<Funcionario> resultado = consulta.list();
			return resultado;  
			
			
		}catch (RuntimeException erro)
		{
			throw erro;
		}
		finally
		{
			sessao.close(); 
		}
	}
}
