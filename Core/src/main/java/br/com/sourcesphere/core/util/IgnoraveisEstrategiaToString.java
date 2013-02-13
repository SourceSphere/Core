package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

public final class IgnoraveisEstrategiaToString implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	public IgnoraveisEstrategiaToString(String... campos) 
	{
		parametro = new Parametro<String>(campos);
	}
	
	@Override
	public Boolean verifica(Field campo) 
	{
		return !parametro.isParametrizado(campo.getName());
	}
}
