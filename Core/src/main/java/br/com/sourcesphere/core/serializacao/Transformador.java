package br.com.sourcesphere.core.serializacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface para a implementacao de um Transformador<br>
 * @see TransformadorCSV
 * @author Guilherme Dio
 *
 */
public abstract class Transformador
{
	/**
	 * @param classe - Classe para ser carregada a partir do arquivo.
	 * @param arquivo  Arquivo a ser serializado.
	 * @return {@link List}:  lista de objetos.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 */
	public abstract Object transforma(Class<?> classe,File arquivo) throws InstantiationException, IllegalAccessException, FileNotFoundException;
	
	/**
	 * @param classe - Classe para ser serializada para um arquivo.
	 * @return String contendo o objeto serializado
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 */
	public abstract String retransforma(Object objeto) throws InstantiationException;
	
}
