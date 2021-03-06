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

package br.com.sourcesphere.core.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe para uso de diversos parametros de um tipo
 * @author Guilherme Dio
 *
 * @param <T>
 */
public final class Parametro<T>
{
	/**
	 * Set com os parametros
	 */
	private Set<T> parametros = new HashSet<T>();
	
	public Parametro(T... parametros) 
	{
		addParametros(parametros);
	}
	
	/**
	 * Adiciona um parametro
	 * @param parametros
	 */
	public void addParametros(T... parametros)
	{
		if(parametros != null)
		{
			for(T parametro : parametros)
				this.parametros.add(parametro);
		}
	}
	
	/**
	 * Verifica se o valor foi parametrizado
	 * @param parametro
	 * @return True ou False
	 */
	public Boolean isParametrizado(T parametro)
	{
		if(parametros.contains(parametro))
			return true;
		return false;
	}
	
	/**
	 * Descompacta os parametros em um array de objetos
	 * @return Array de objetos com os parametros
	 */
	public Object[] getParametros()
	{
		return parametros.toArray();
	}
	
	/**
	 * Limpa os parametros
	 */
	public void clear()
	{
		this.parametros.clear();
	}
}
