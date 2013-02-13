package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

public class DefaultEstrategiaEquals implements EstrategiaEquals
{
	@Override
	public Boolean verifica(Field campo) 
	{
		return true;
	}
}
