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
