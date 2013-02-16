package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * <p>Estrategia de EqualsUtil ao qual permite a parametrizacao de campos
 * <p>ignoraveis durante a validacao de igualdade
 * @author Guilherme Dio
 *
 */
public final class IgnoraveisEstrategiaEquals implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	/**
	 * Monta a estrategia com os campos a serem ignorados
	 * @param campos
	 */
	public IgnoraveisEstrategiaEquals(String... campos) 
	{
		parametro = new Parametro<String>(campos);
	}
	
	@Override
	public Boolean verifica(Field campo) 
	{
		return !parametro.isParametrizado(campo.getName());
	}
}
