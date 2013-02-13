package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

/**
 * Interface para estrategia na classe {@link EqualsUtil}
 * @author gdio
 *
 */
public interface EstrategiaEquals extends Estrategia<Field>
{
	/**
	 * Este m�todo informa para a classe {@link EqualsUtil} se o parametro 'campo' deve ser validado ou n�o
	 * @param campo - Campo sendo validado
	 * @return True - Se o campo deve ser validado
	 * <p>False - Se o campo n�o deve ser validado
	 */
	Boolean verifica(Field campo);
}
