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

/**
 * Classe que encapsula toda a serializacao.
 * @author Guilherme Dio
 *
 */
public final class Serializador 
{
	private final Transformador transformador;
	private Class<?> classe;

	/**
	 * 
	 * @param transformador Transformador utilizado para {@link Transformador}
	 */
	public Serializador(Transformador transformador) 
	{
		this.transformador = transformador;
	}
	
	/**
	 * Serializa o arquivo
	 * @param arquivo Arquivo a ser serializado
	 * @return {@link Object}: Objeto serializado a partir do arquivo
	 */
	public Object serializa(File arquivo)
	{
		if(arquivo == null) throw new IllegalArgumentException("O arquivo informado esta nulo !");
		if(classe == null) throw new IllegalArgumentException("A classe para serializacao nao foi informada !");
		try
		{
			return this.transformador.transforma(this.classe, arquivo);
		}
		catch(IllegalAccessException e)
		{
			throw new SerializacaoException("O Arquivo esta com acesso negado !");
		}
		catch(InstantiationException e)
		{
			throw new SerializacaoException("A classe informada e invalida !");
		}
		catch(FileNotFoundException e)
		{
			throw new SerializacaoException("O arquivo nao existe !");
		}
		catch(IllegalStateException e)
		{
			throw new SerializacaoException("O arquivo esta em formato invalido !");
		}
	}
	
	/**
	 * Deserializa o objeto para uma String utilizando o transformador parametrizado
	 * @param objeto - Instancia a ser deserializada
	 * @return Objeto deserializado
	 */
	public String deserializa(Object objeto)
	{
		if(objeto == null) throw new IllegalArgumentException("O objeto não pode ser nulo !");
		try
		{
			return this.transformador.retransforma(objeto);
		} 
		catch(InstantiationException e)
		{
			throw new SerializacaoException("A classe informada é inválida !");
		}
	}
	
	/**
	 * Set da classe a qual deverá ser utilizada na serialização
	 * @param classe
	 */
	public void setClasse(Class<?> classe)
	{
		if(classe == null) throw new IllegalArgumentException("A classe para serializacao nao foi informada !");
		this.classe = classe;
	}
	
	public Class<?> getClasse() 
	{
		return this.classe;
	}
	
}
