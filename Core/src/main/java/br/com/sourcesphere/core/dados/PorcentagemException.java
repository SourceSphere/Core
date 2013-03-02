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

package br.com.sourcesphere.core.dados;

/**
 * Excessões envolvendo a classe {@link Porcentagem}
 * @author Guilherme Dio
 *
 */
public final class PorcentagemException extends RuntimeException 
{
	private static final long serialVersionUID = 8183154189347439943L;
	private String msg;
	
	public PorcentagemException(String msg)
	{
		super(msg);
		this.msg = msg;
	}
	
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}
	
	@Override
	public String getMessage()
	{
		final String texto = "Erro ao operar porcentagem";
		
		if(!"".equals(msg) && msg != null)
			return texto+":"+this.msg;
		else
			return texto;
	}
}
