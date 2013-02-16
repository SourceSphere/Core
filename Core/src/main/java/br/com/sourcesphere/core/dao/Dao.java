package br.com.sourcesphere.core.dao;

import java.util.List;

/**
 * Class abstrata para a implementação de uma Pattern DAO
 * @author Guilherme Gracia Dio
 * @param <T> - Tipo de entidade a ser usada
 */
public abstract class Dao<T> 
{
	/**
	 * Constante para o comparador de igualdade(=)
	 */
	public static final String EQUALS = "=";
	/**
	 * Constante para o comparador de semelhança(LIKE)
	 */
	public static final String LIKE = "LIKE";
	
	/**
	 * Adiciona a entidade no banco de dados
	 * @param modelo
	 */
	public abstract void adiciona(T modelo);
	
	/**
	 * Altera a entidade no banco de dados
	 * @param modelo
	 */
	public abstract void altera(T modelo);
	
	/**
	 * Remove a entidade no banco de dados
	 * @param modelo
	 */
	public abstract void remove(T modelo);
	
	/**
	 * Recupera a entidade no banco de dados
	 * @param chave - Chave de Identifica��o Unica
	 * @return <T> - Tipo de entidade usada
	 */
	public abstract T get(int chave);
	
	/**
	 * Recupera uma lista da entidade no banco de dados
	 * @return List<T> - Lista com as entidades
	 */
	public abstract List<T> getLista();
	
	/**
	 * Recupera a entidade no banco de dados a partir do campo e de um comparador
	 * @param campo - Campo a ser comparado
	 * @param comparador - Comparador a ser utilizado(Utilizar as constantes Dao.EQUALS ou Dao.LIKE)
	 * @param valor - Valor a ser usado como limitador.
	 * @return List<T> - Lista com as entidades
	 */
	public abstract List<T> getPorCampo(String campo,String comparador,Object valor);
	
}
