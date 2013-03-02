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
 * <p>Estrategia para uso com {@link ToStringUtil} onde campos sao
 * <p>definidos como obrigatorios para a montagem da String
 * @author Guilherme Dio
 *
 */
public final class ObrigatoriosEstrategiaToString implements EstrategiaEquals 
{
	private Parametro<String> parametro;
	
	/**
	 * Monta a estrategia com campos a serem obrigatorios
	 * @param campos
	 */
	public ObrigatoriosEstrategiaToString(String... campos) 
	{
		parametro = new Parametro<String>(campos);
	}
	
	@Override
	public Boolean verifica(Field campo) 
	{
		return parametro.isParametrizado(campo.getName());
	}
}
