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

import java.lang.reflect.Field;

/**
 * Interface para estrategias de uso na classe {@link ToStringUtil}
 * @author Guilherme Dio
 *
 */
public interface EstrategiaToString extends Estrategia<Field>
{
	/**
	 * Este metodo informa para a classe {@link ToStringUtil} se o parametro 'campo' deve ser carregado na String ou nao
	 * @param campo - Campo sendo validado
	 * @return True - Se o campo deve ser impresso
	 * <p>False - Se o campo nao deve ser impresso
	 */
	Boolean verifica(Field campo);
}
