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

package br.com.sourcesphere.core.arquivo;

import java.util.Calendar;

/**
 * Classe para armazenar dados exclusivos de um arquivo
 * @author Guilherme Dio
 * @since 1.0
 *
 */
public final class Arquivo
{
	private String nome;
	private String extensao;
	private long tamanho;
	private String caminhoReal;
	private String caminhoWeb;
	private Calendar ultimaModificacao;
	
	public String getNome() 
	{
		return nome;
	}
	public String getExtensao() 
	{
		return extensao;
	}
	public long getTamanho() 
	{
		return tamanho;
	}
	public String getCaminhoReal()
	{
		return caminhoReal;
	}
	public String getCaminhoWeb()
	{
		return caminhoWeb;
	}
	public Calendar getUltimaModificacao() 
	{
		return ultimaModificacao;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setExtensao(String extensao) 
	{
		this.extensao = extensao;
	}
	public void setTamanho(long tamanho)
	{
		this.tamanho = tamanho;
	}
	public void setCaminhoReal(String caminhoReal) 
	{
		this.caminhoReal = caminhoReal;
	}
	public void setCaminhoWeb(String caminhoWeb)
	{
		this.caminhoWeb = caminhoWeb;
	}
	public void setUltimaModificacao(Calendar ultimaModificacao)
	{
		this.ultimaModificacao = ultimaModificacao;
	}

}
