package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

public class DefaultEstrategiaToString implements EstrategiaToString
{	
	@Override
	public Boolean verifica(Field campo) 
	{
		return true;
	}
}
