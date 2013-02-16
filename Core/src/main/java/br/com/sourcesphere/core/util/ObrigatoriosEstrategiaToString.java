package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * <p>Estrategia para uso com {@link ToStringUtil} onde campos sao
 * <p>definidos como obrigatorios para a montagem da String
 * @author Guilherme Dio
 *
 */
public final class ObrigatoriosEstrategiaToString implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	/**
	 * Monta a estrategia com campos a serem obrigatorios
	 * @param campos
	 */
	public ObrigatoriosEstrategiaToString(String... campos) 
	{
		parametro = new Parametro<String>(campos);
	}
	
	@Override
	public Boolean verifica(Field campo) 
	{
		return parametro.isParametrizado(campo.getName());
	}
}
