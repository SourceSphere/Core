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

package br.com.sourcesphere.core.seguranca;

/**
 * ENUM com tipos de altoritmos para uso com a classe {@link Criptografia}
 * @author Guilherme Dio
 *
 */
public enum TipoAlgoritmo 
{	
	MD2("MD2"),
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");
	
	private String algoritmo;
	
	public String algoritmo()
	{
		return this.algoritmo;
	}
	
	private TipoAlgoritmo(String algoritmo) 
	{
		this.algoritmo = algoritmo;
	}
}
