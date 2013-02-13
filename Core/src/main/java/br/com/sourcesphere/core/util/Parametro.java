package br.com.sourcesphere.core.util;

import java.util.HashSet;
import java.util.Set;

public class Parametro<T>
{
	/**
	 * Set com os parametros
	 */
	private Set<T> parametros = new HashSet<T>();
	
	public Parametro(T... parametros) 
	{
		addParametros(parametros);
	}
	
	public void addParametros(T... parametros)
	{
		for(T parametro : parametros)
			this.parametros.add(parametro);
	}
	
	public Boolean isParametrizado(T parametro)
	{
		if(parametros.contains(parametro))
			return true;
		return false;
	}
	
	public void clear()
	{
		this.parametros.clear();
	}
}
