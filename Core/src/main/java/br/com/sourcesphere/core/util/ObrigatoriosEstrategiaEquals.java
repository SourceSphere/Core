package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

public class ObrigatoriosEstrategiaEquals implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	public ObrigatoriosEstrategiaEquals(String... campos) 
	{
		parametro = new Parametro<String>(campos);
	}
	
	@Override
	public Boolean verifica(Field campo) 
	{
		return parametro.isParametrizado(campo.getName());
	}
}
