package br.com.javapet.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class EstadoBean 
{
	public void salvar()
	{
		String texto = "Teste";
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto);
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, mensagem);
	}
}
