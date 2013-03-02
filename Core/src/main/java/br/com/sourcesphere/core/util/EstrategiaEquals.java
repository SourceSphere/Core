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
 * Interface para estrategia na classe {@link EqualsUtil}
 * @author Guilherme Dio
 *
 */
public interface EstrategiaEquals extends Estrategia<Field>
{
	/**
	 * Este metodo informa para a classe {@link EqualsUtil} se o parametro 'campo' deve ser validado ou nao
	 * @param campo - Campo sendo validado
	 * @return True - Se o campo deve ser validado
	 * <p>False - Se o campo n�o deve ser validado
	 */
	Boolean verifica(Field campo);
}
