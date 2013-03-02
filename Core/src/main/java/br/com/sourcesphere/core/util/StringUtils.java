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
 * Utilities for String
 * @author Guilherme Dio
 *
 */
public final class StringUtils 
{
	/**
	 * Check if the {@link String} has some value
	 * @param str - {@link String} a ser verificada
	 * @return {@link Boolean} True se tiver texto / False se nao tiver texto
	 */
	public static Boolean hasText(String str)
	{
		if(str == null || "".equals(str))
			return false;
		return true;
	}
	
	/**
	 * Counts the occurrences of specified value on the {@link String}
	 * @param valor {@link String} a ser verificada
	 * @param ocorrencia {@link String} de ocorrencia a ser encontrada
	 * @return {@link Integer} com a quantidade de ocorrencias
	 */
	public static Integer occurrencesOf(String valor, String ocorrencia) 
	{
		if(!hasText(valor) || !hasText(ocorrencia)) return 0;
		
		Integer qtd = 0;
		Integer pos = 0;
		Integer idx;
		while ((idx = valor.indexOf(ocorrencia, pos)) != -1) 
		{
			++qtd;
			pos = idx + ocorrencia.length();
		}
		return qtd;
	}
}
