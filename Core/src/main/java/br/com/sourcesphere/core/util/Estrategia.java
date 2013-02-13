package br.com.sourcesphere.core.util;


public interface Estrategia<T> 
{
	/**
	 * Este m�todo informa o parametro deve ser validado ou n�o
	 * @param t - parametro
	 * @return True - Se o parametro deve ser validado
	 * <p>False - Se o parametro n�o deve ser validado
	 */
	Boolean verifica(T t);
}
