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
public class ToStringUtil implements Ignoravel<String>
{
	private static ToStringUtil instance;
	
	private Set<String> ignorados = new HashSet<String>();
	
	private ToStringUtil()
	{}
	
	private ToStringUtil(List<String> ignorados)
	{
		this.addAllCamposIgnorados(ignorados);
	}
	
	public static ToStringUtil getInstance()
	{
		return getInstance(null);
	}
	
	public static ToStringUtil getInstance(List<String> ignorados)
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
	
	public void addAllCamposIgnorados(List<String> ignorados)
	{
		for(String ignorado : ignorados)
			this.addCampoIgnorado(ignorado);
	}
	
	public void addCampoIgnorado(String ignorado)
	{
		this.ignorados.add(ignorado);
	}

	public void clear()
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
			if(!isIgnorado(campo.getName()))
			{
				String valor = String.valueOf(reflection.getValue(campo));
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
	
	public Boolean isIgnorado(String campo)
	{
		if(ignorados.contains(campo))
			return true;
		return false;
	}
}
