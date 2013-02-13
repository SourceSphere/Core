package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;

public interface EstrategiaToString extends Estrategia<Field>
{
	/**
	 * Este m�todo informa para a classe {@link ToStringUtil} se o parametro 'campo' deve ser carregado na String ou n�o
	 * @param campo - Campo sendo validado
	 * @return True - Se o campo deve ser impresso
	 * <p>False - Se o campo n�o deve ser impresso
	 */
	Boolean verifica(Field campo);
}
