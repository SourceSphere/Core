package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * Estrategia de EqualsUtil default
 * @author Guilherme Dio
 *
 */
public final class DefaultEstrategiaEquals implements EstrategiaEquals
{
	@Override
	public Boolean verifica(Field campo) 
	{
		return true;
	}
}
