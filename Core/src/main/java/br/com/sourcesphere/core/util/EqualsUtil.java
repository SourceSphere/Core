package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Classe para verificar dinamicamente se objetos são iguais, por reflection
 * @author Guilherme Dio
 * @since 1.0
 */
public class EqualsUtil implements Ignoravel<String>
{	
	/**
	 * Objeto principal utilizado na comparação
	 */
	private final Object objeto;
	
	/**
	 * Set com os campos ignorados
	 */
	private Set<String> ignorados = new HashSet<String>();
	
	/**
	 * Inicializa o EqualsUtil
	 * @param objetoPrincipal - Objeto utilizado para comparar com outros
	 */
	public EqualsUtil(Object objetoPrincipal)
	{
		this.objeto = objetoPrincipal;
	}
	
	private Object getObjeto() 
	{
		return objeto;
	}
	
	/**
	 * Realiza a comparação do objeto inicializado em {@link #EqualsUtil(Object)} com outro
	 * @param outroObjeto - Objeto a ser comparado com o principal
	 * @return True se for igual, False se for diferente
	 */
	public Boolean isEquals(Object outroObjeto)
	{
		//Classes dos objetos
		Class<?> clazzObjetoPrincipal = this.getObjeto().getClass();
		Class<?> clazzOutroObjeto = outroObjeto.getClass();
		
		//Verifica se ambos s�o da mesma classe
		if(clazzObjetoPrincipal.equals(clazzOutroObjeto))
		{
			//Campos dos objetos
			List<Field> camposObjetoPrincipal = ReflectionUtil.getInstance(clazzObjetoPrincipal, getObjeto()).getFields();
			List<Field> camposOutroObjeto = ReflectionUtil.getInstance(clazzOutroObjeto, outroObjeto).getFields();
			
			//Percorre todos os campos do objeto principal
			for(Field campoObjetoPrincipal : camposObjetoPrincipal)
			{
				//Verifica se é um campo ignorado na verificação
				if(!isIgnorado(campoObjetoPrincipal.getName()))
				{
					//Percorre todos os campos do objeto secundario
					for(Field campoOutroObjeto : camposOutroObjeto)
					{
						//Verifica se o tipos dos campos s�o iguais
						if(campoObjetoPrincipal.equals(campoOutroObjeto))
						{
							//Valores dos campos
							Object valorObjetoPrincipal = ReflectionUtil.getInstance(clazzObjetoPrincipal, getObjeto()).getValue(campoObjetoPrincipal);
							Object valorOutroObjeto = ReflectionUtil.getInstance(clazzOutroObjeto, outroObjeto).getValue(campoOutroObjeto);
							
							//Se ambos estiverem com valor nulo, retorna verdadeiro
							if(valorObjetoPrincipal == null && valorOutroObjeto == null)
								return true;
	
							//Verifica se o campo é primitivo ou uma instância
							if(campoObjetoPrincipal.getType().isPrimitive())
							{
								//Verifica se os valores são equivalentes
								return valorObjetoPrincipal.equals(valorOutroObjeto);
							}
							else
							{
								//Verifica se é java.lang
								if(isJavaLang(valorObjetoPrincipal.getClass()))
								{
									//Utiliza o equals do java.lang
									return valorObjetoPrincipal.equals(valorOutroObjeto);
								}
								else
								{
									//Filtra recursivamente
									return new EqualsUtil(valorObjetoPrincipal).isEquals(valorOutroObjeto);
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica se a classe é do pacote java.lang
	 * @param clazz - Classe para verificar
	 * @return True se for java.lang, False se não for java.lang
	 */
	private Boolean isJavaLang(Class<?> clazz)
	{
		return clazz.getPackage().equals(Object.class.getPackage());
	}
	
	
	@Override
	public void addAllCamposIgnorados(List<String> ignorados)
	{
		for(String ignorado : ignorados)
			this.addCampoIgnorado(ignorado);
	}
	
	@Override
	public void addCampoIgnorado(String ignorado)
	{
		this.ignorados.add(ignorado);
	}
	
	@Override
	public Boolean isIgnorado(String campo)
	{
		if(ignorados.contains(campo))
			return true;
		return false;
	}
	
	@Override
	public void clear()
	{
		this.ignorados.clear();
	}
}
