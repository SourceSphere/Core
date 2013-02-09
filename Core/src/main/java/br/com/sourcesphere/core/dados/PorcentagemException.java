package br.com.sourcesphere.core.dados;

public class PorcentagemException extends RuntimeException 
{
	private static final long serialVersionUID = 8183154189347439943L;
	private String msg;
	
	public PorcentagemException(String msg)
	{
		super(msg);
		this.msg = msg;
	}
	
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	
	@Override
	public String getMessage()
	{
		final String texto = "Erro ao operar porcentagem";
		
		if(!"".equals(msg) && msg != null)
			return texto+":"+this.msg;
		else
			return texto;
	}
}
