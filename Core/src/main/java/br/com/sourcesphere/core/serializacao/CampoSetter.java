package br.com.sourcesphere.core.serializacao;

import java.lang.reflect.Field;

/**
 * Classe para realizar o set de valores através de reflection
 * nos atributos de um objeto.
 * @author Guilherme
 */
public class CampoSetter 
{
	private final CampoPrimitivoSetter primitiveSetter;
	
	public CampoSetter() 
	{
		this.primitiveSetter = new CampoPrimitivoSetter();
	}
	
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
		if(campo.getType().isPrimitive())
			primitiveSetter.setFieldValue(objeto, campo, valor);
		else
			campo.set(objeto, campo.getType().cast(valor));
	}
}
