package br.com.javapet.bean;

import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

@ManagedBean
public class EstadoBean 
{
	public void salvar()
	{
		Messages.addGlobalInfo("Teste sistema Petshop");
	}
}
