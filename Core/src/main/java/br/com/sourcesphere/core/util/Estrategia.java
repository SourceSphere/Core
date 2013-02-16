package br.com.sourcesphere.core.util;

/**
 * Interface para implementacao de estrategias com verificacoes no meio do codigo
 * @author Guilherme Dio
 *
 * @param <T>
 */
public interface Estrategia<T> 
{
	/**
	 * Este metodo informa o parametro deve ser validado ou nao
	 * @param t - parametro
	 * @return True - Se o codigo deve continuar
	 * <p>False - Se o codigo nao deve continuar
	 */
	Boolean verifica(T t);
}
