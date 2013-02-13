package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Classe para imprimir(toString) objetos din�micamente por reflection
 * @author Guilherme Dio
 * @autor Marco Noronha
 * @since 1.0
 */
public class ToStringUtil
{
	private static ToStringUtil instance;
	
	private EstrategiaToString estrategia;
	
	private ToStringUtil() {}
	
	public static ToStringUtil getInstance()
	{
		return getInstance(new DefaultEstrategiaToString());
	}
	
	public static ToStringUtil getInstance(EstrategiaToString estrategia)
	{
		if(instance == null)
			instance = new ToStringUtil();
		instance.setEstrategia(estrategia);
		return instance;
	}
	
	private void setEstrategia(EstrategiaToString estrategia)
	{
		this.estrategia = estrategia;
	}
	
	public String toString(Object objeto)
	{
		ReflectionUtil reflection = ReflectionUtil.getInstance(objeto.getClass(), objeto);
		List<Field> campos = reflection.getFields();
		StringBuilder sb = new StringBuilder(reflection.getClassName()+":\r\n{");
		for(Field campo : campos)
		{
			if(estrategia.verifica(campo))
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
}
