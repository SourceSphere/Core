package br.com.sourcesphere.core.serializacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa a transformação de um arquivo CSV para um objeto Java.<br>
 * O Arquivo não deve ser nulo e deve conter header com identificação igual aos atributos<br>
 * da classe ao qual será serializado.
 * <br>
 * <hr>
 * Exemplo:<br>
 * Arquivo CSV:<br>
 * Nome;Idade;
 * <br><br>
 * Classe Java:<br>
 * private String nome;<br>
 * private int idade;
 * <br><br>
 * 
 * @author Guilherme Dio
 *
 */
public final class TransformadorCSV extends Transformador 
{

	/**
	 * @param classe   Classe para ser carregada a partir do CSV.
	 * @param arquivo  Arquivo CSV a ser serializado.
	 * @return {@link List}:  lista de objetos.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 */
	@Override
	public List<Object> transforma(Class<?> classe, File arquivo) throws InstantiationException, IllegalAccessException, FileNotFoundException
	{	
		//Classe para setar dados
		CampoSetter setter = new CampoSetter();
		
		//Leitura do CSV
		LeitorCSV leitor = new LeitorCSV(arquivo, ";");
		List<String> header = leitor.getHeader();
		List<Object> valores = leitor.getValores();
		
		//Lista de Objetos
		List<Object> objetos = new ArrayList<Object>();
		
		//Recupera os campos da classe
		List<Field> campos = new CampoGetter().getFields(classe);
		
		//Montagem dos objetos
		for(Object linha : valores)
		{
			//Nova instância da classe
			Object objeto = classe.newInstance();
			for(int i = 0;i < header.size();i++) 
			{
				//Varre os campos
				for(Field campoClasse : campos)
				{
					//Carrega os campos
					if(header.get(i).toLowerCase().equals(campoClasse.getName().toLowerCase()))
					{
						setter.setFieldValue(objeto, campoClasse, linha.toString().split(";")[i]);
					}
				}
			}
			//Adiciona na List
			objetos.add(objeto);
		}
		
		//Retorna os objetos montados
		return objetos;
	}
}
