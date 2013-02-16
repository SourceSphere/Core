package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * <p>Estrategia para ignorar campos durante a montagem
 * <p>de String descrevendo a classe na {@link ToStringUtil}
 * @author Guilherme Dio
 *
 */
public final class IgnoraveisEstrategiaToString implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	/**
	 * Monta a estrategia com campos a serem ignorados
	 * @param campos
	 */
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
