package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe para recuperar os campos de uma classe atrav�s de reflection.
 * @author Guilherme Dio.
 *
 */
public final class CampoGetter 
{
	/**
	 * Recupera um campo espec�fico.
	 * @param classe    Refer�ncia a classe que dever� ser utilizada.
	 * @param nomeCampo Nome do campo necess�rio.
	 * @return {@link Field}: Campo espec�fico da classe
	 */
	public static final Field getField(Class<?> classe,String nomeCampo) 
	{
		List<Field> campos = getFields(classe);
		for(Field campo : campos)
		{
			if(campo.getName().equals(nomeCampo))
				return campo;
		}
		return null;
	}

	/**
	 * Recupera todos os campos da classe
	 * @param classe Refer�ncia a classe que dever� ser utilizada.
	 * @return {@link List} de {@link Field}: Lista de todos os campos da classe
	 */
	public static final List<Field> getFields(Class<?> classe) 
	{
		List<Field> campos = new ArrayList<Field>();
		while (classe != null) 
		{
			campos.addAll(Arrays.asList(classe.getDeclaredFields()));
			classe = classe.getSuperclass();
		}
		return campos;
	}
	
	public static final Object getValue(Field campo,Object objeto)
	{
		Object valor = null;
		try
		{
			valor = campo.get(objeto).toString();
		}
		catch(IllegalAccessException e)
		{
			campo.setAccessible(true);
			try
			{
				valor = campo.get(objeto).toString();
			}
			catch(Exception ex)
			{
				valor = "###";
			}
			campo.setAccessible(false);
		}
		return valor;
	}
}