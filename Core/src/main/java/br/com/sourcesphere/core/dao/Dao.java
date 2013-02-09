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
	 * Método que adiciona uma entidade no sistema de armazenamento de dados utilizado
	 * @param modelo
	 */
	public abstract void adiciona(T modelo);
	
	/**
	 * Método que altera os dados de uma entidade no sistema de armazenamento de dados utilizado
	 * @param modelo
	 */
	public abstract void altera(T modelo);
	
	/**
	 * Método que remove uma entidade do sistema de armazenamento de dados utilizado
	 * @param modelo
	 */
	public abstract void remove(T modelo);
	
	/**
	 * Método que retorna uma entidade no sistema de armazenamento de dados a partir de sua Chave de identificação
	 * @param chave - Chave de Identificação Unica
	 * @return <T> - Tipo de entidade usada
	 */
	public abstract T get(int chave);
	
	/**
	 * Método que retorna as entidades contidas no sistema de armazenamento de dados utilizado
	 * @return List<T> - Lista com as entidades
	 */
	public abstract List<T> getLista();
	
	/**
	 * Método que retorna as entidades contidas no sistema de armazenamento de dados, limitando pelo valor de um campo
	 * @param campo - Campo a ser comparado
	 * @param comparador - Comparador a ser utilizado(Utilizar as constantes Dao.EQUALS ou Dao.LIKE)
	 * @param valor - Valor a ser usado como limitador.
	 * @return List<T> - Lista com as entidades
	 */
	public abstract List<T> getPorCampo(String campo,String comparador,Object valor);
	
}
