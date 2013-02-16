package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * Estrategia de ToStringUtil default
 * @author Guilherme Dio
 *
 */
public final class DefaultEstrategiaToString implements EstrategiaToString
{	
	@Override
	public Boolean verifica(Field campo) 
	{
		return true;
	}
}
