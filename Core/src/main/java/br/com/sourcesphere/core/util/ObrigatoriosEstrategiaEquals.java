package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * <p>Estrategia para uso com {@link EqualsUtil} onde campos sao
 * <p>definidos como obrigatorios para a igualdade
 * @author Guilherme Dio
 *
 */
public final class ObrigatoriosEstrategiaEquals implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	/**
	 * Monta a estrategia com campos a serem obrigatorios
	 * @param campos
	 */
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
