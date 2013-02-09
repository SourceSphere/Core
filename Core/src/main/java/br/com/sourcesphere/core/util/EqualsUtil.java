package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;
import java.util.List;


/**
 * Classe para verificar dinamicamente se objetos s�o iguais, por reflection
 * @author Guilherme Dio
 * @since 1.0
 */
public class EqualsUtil 
{
	private static EqualsUtil instance;
	
	private Object objeto;
	
	private EqualsUtil() {}
	
	public static EqualsUtil getInstance(Object objetoPrincipal)
	{
		if(instance == null)
			instance = new EqualsUtil();
		instance.setObjeto(objetoPrincipal);
		return instance;
	}
	
	private void setObjeto(Object objeto)
	{
		this.objeto = objeto;
	}
	
	//Sendo implementado
	public Boolean isEquals(Object outroObjeto)
	{
		Class<?> clazzObjetoPrincipal = this.objeto.getClass();
		Class<?> clazzOutroObjeto = outroObjeto.getClass();
		if(clazzObjetoPrincipal.equals(clazzOutroObjeto))
		{
			List<Field> camposObjetoPrincipal = ReflectionUtil.getInstance(clazzObjetoPrincipal, objeto).getFields();
			List<Field> camposOutroObjeto = ReflectionUtil.getInstance(clazzOutroObjeto, outroObjeto).getFields();
			for(Field campoObjetoPrincipal : camposObjetoPrincipal)
			{
				for(Field campoOutroObjeto : camposOutroObjeto)
				{
					if(campoObjetoPrincipal.equals(campoOutroObjeto))
					{
						if(campoObjetoPrincipal.getType().isPrimitive())
						{
							//Validar Primitivo
						}
						else
						{
							Object valorObjetoPrincipal = ReflectionUtil.getInstance(clazzObjetoPrincipal, objeto).getValue(campoObjetoPrincipal);
							Object valorOutroObjeto = ReflectionUtil.getInstance(clazzOutroObjeto, objeto).getValue(campoOutroObjeto);
							if(!valorObjetoPrincipal.equals(valorOutroObjeto))
								return false;
						}
						break;
					}
				}
			}
			
		}
		return true;
	}
}
