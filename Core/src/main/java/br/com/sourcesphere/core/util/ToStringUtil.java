package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe para imprimir(toString) objetos din�micamente por reflection
 * @author Guilherme Dio
 * @autor Marco Noronha
 * @since 1.0
 */
public class ToStringUtil 
{
	private static ToStringUtil instance;
	
	private Set<String> ignorados = new HashSet<String>();
	
	private ToStringUtil()
	{}
	
	private ToStringUtil(Set<String> ignorados)
	{
		this.addAllCamposIgnorados(ignorados);
	}
	
	public static ToStringUtil getInstance()
	{
		return getInstance(new HashSet<String>());
	}
	
	public static ToStringUtil getInstance(Set<String> ignorados)
	{
		if(instance == null)
			instance = new ToStringUtil(ignorados);
		else
		{
			instance.clear();
			instance.addAllCamposIgnorados(ignorados);
		}
		return instance;
	}
	
	public void addAllCamposIgnorados(Set<String> ignorados)
	{
		for(String ignorado : ignorados)
			this.addCampoIgnorado(ignorado);
	}
	
	public void addCampoIgnorado(String ignorado)
	{
		this.ignorados.add(ignorado);
	}

	private void clear()
	{
		this.ignorados.clear();
	}
	
	public String toString(Object objeto)
	{
		ReflectionUtil reflection = ReflectionUtil.getInstance(objeto.getClass(), objeto);
		List<Field> campos = reflection.getFields();
		StringBuilder sb = new StringBuilder(reflection.getClassName()+":\r\n{");
		for(Field campo : campos)
		{
			if(!isIgnorado(campo))
			{
				String valor = reflection.getValue(campo).toString();
				sb.append("\r\n"+campo.getName()+": "+valor);
			}
		}
		sb.append("\r\n}");
		return sb.toString();
	}
	
	public String toString(Object... objetos)
	{
		StringBuilder sb = new StringBuilder();
		for(Object objeto : objetos)
			sb.append(this.toString(objeto));
		return sb.toString();
	}
	
	private boolean isIgnorado(Field campo)
	{
		if(ignorados.contains(campo.getName()))
			return true;
		return false;
	}
}
