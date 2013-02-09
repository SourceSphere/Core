package br.com.sourcesphere.core.serializacao;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Classe para setar valores em um campo primitivo
 * @author Guilherme Dio.
 *
 */
public class CampoPrimitivoSetter 
{
	/**
	 * 
	 * @param objeto Referência ao objeto a ser modificado.
	 * @param campo  Campo do objeto a ser setado.
	 * @param valor  Valor que deve ser inserido no campo.
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public void setFieldValue(Object objeto,Field campo,Object valor) throws IllegalArgumentException, IllegalAccessException
	{
		campo.setAccessible(true);
		if(campo.getType().equals(Byte.TYPE))
		{
			campo.setByte(objeto, Byte.parseByte(valor.toString()));
			return;
		}
		if(campo.getType().equals(Short.TYPE))
		{
			campo.setShort(objeto, Short.parseShort(valor.toString()));
			return;
		}
		if(campo.getType().equals(Integer.TYPE))
		{
			campo.setInt(objeto, Integer.parseInt(valor.toString()));
			return;
		}
		if(campo.getType().equals(Long.TYPE))
		{
			campo.setLong(objeto, Long.parseLong(valor.toString()));
			return;
		}
		if(campo.getType().equals(Float.TYPE))
		{
			campo.setFloat(objeto, Float.parseFloat(valor.toString()));
			return;
		}
		if(campo.getType().equals(Double.TYPE))
		{
			campo.setDouble(objeto, Double.parseDouble(valor.toString()));
			return;
		}
		if(campo.getType().equals(Boolean.TYPE))
		{
			campo.setBoolean(objeto, Boolean.parseBoolean(valor.toString()));
			return;
		}
		if(campo.getType().equals(Float.TYPE))
		{
			campo.setFloat(objeto, Float.parseFloat(valor.toString()));
			return;
		}
		if(campo.getType().equals(Boolean.TYPE))
		{
			campo.setBoolean(objeto, Boolean.parseBoolean(valor.toString()));
			return;
		}
	}
	
	public void setFieldsValue(Object objeto,List<Field> campos,List<Object> valores) throws IllegalArgumentException, IllegalAccessException
	{
		for(int i = 0;i < campos.size();i++)
		{
			this.setFieldValue(objeto, campos.get(i), valores.get(i));
		}
	}
}
