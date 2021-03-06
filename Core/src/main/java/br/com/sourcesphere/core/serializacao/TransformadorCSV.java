/*
 * @Copyright 2013 - ALL RIGHTS RESERVED TO SOURCEPHERE
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.sourcesphere.core.serializacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.sourcesphere.core.util.ReflectionUtil;

/**
 * Classe que implementa a transformacao de um arquivo CSV para um objeto Java.<br>
 * O Arquivo nao deve ser nulo e deve conter header com identificacao igual aos atributos<br>
 * da classe ao qual sera serializado.
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
		//Leitura do CSV
		LeitorCSV leitor = new LeitorCSV(arquivo, ";");
		List<String> header = leitor.getHeader();
		List<Object> valores = leitor.getValores();
		
		//Lista de Objetos
		List<Object> objetos = new ArrayList<Object>();
		
		//Recupera os campos da classe
		List<Field> campos = ReflectionUtil.getInstance(classe, null).getFields();
		
		//Montagem dos objetos
		for(Object linha : valores)
		{
			//Valores da linha
			List<String> valoresLinha = Arrays.asList(linha.toString().split(";"));
			
			//Nova instancia da classe
			Object objeto = classe.newInstance();
			
			for(int i = 0;i < header.size();i++) 
			{
				//Varre os campos
				for(Field campoClasse : campos)
				{
					//Carrega os campos
					if(header.get(i).toLowerCase().equals(campoClasse.getName().toLowerCase()))
					{
						ReflectionUtil.getInstance(classe, objeto).setValue(campoClasse, valoresLinha.get(i));
					}
				}
			}
			//Adiciona na Lista de objetos
			objetos.add(objeto);
		}
		
		//Retorna os objetos montados
		return objetos;
	}
	
	/**
	 * Ainda não implememtado
	 */
	@Override
	@Deprecated
	public String retransforma(Object objeto) throws InstantiationException 
	{
		return "notYetImplemented";
	}
}
