package br.com.sourcesphere.core.serializacao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe para recuperar os campos de uma classe através de reflection.
 * @author Guilherme Dio.
 *
 */
public final class CampoGetter 
{
	/**
	 * Recupera um campo específico.
	 * @param classe    Referência a classe que deverá ser utilizada.
	 * @param nomeCampo Nome do campo necessário.
	 * @return {@link Field}: Campo específico da classe
	 */
	public Field getField(Class<?> classe,String nomeCampo) 
	{
		List<Field> campos = this.getFields(classe);
		for(Field campo : campos)
		{
			if(campo.getName().equals(nomeCampo))
				return campo;
		}
		return null;
	}
	
	/**
	 * Recupera todos os campos da classe
	 * @param classe Referência a classe que deverá ser utilizada.
	 * @return {@link List} de {@link Field}: Lista de todos os campos da classe
	 */
	public List<Field> getFields(Class<?> classe) 
	{
		List<Field> campos = new ArrayList<Field>();
		while (classe != null) 
		{
			campos.addAll(Arrays.asList(classe.getDeclaredFields()));
			classe = classe.getSuperclass();
		}
		return campos;
	}
}
