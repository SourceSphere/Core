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

import java.math.BigDecimal;
import java.text.NumberFormat;

import javax.persistence.Embeddable;

/**
 * Classe no padrão singleton que implementa cálculos de porcentagem sobre um valor
 * <p>Para obter um instância, utilize o método {@link #getInstance(Long)}
 * @author Guilherme Dio
 * @since 1.0
 */
@Embeddable
public final class Porcentagem
{	
	//Instância Singleton
	private static Porcentagem instance;
	
	//Valor porcento
	private Long porcento;
	
	private Porcentagem() {}
	
	/**
	 * Método que retorna uma instancia singleton da classe
	 * @param valorPorCento Valor por cento a ser utilizado. Exemplos:
	 *                      <p>Ex: Para 10%, informe 10.
	 *                      <p>Ex: Para 50%, informe 50.
	 *                      <p>Ex: Para 200%, informe 200.
	 * @since 1.0
	 */
	public static Porcentagem getInstance(Long valorPorCento)
	{
		if(instance == null)
			instance = new Porcentagem();
		instance.setPorcento(valorPorCento);
		return instance;
	}
	
	/**
	 * Método para atribuir o valor percentual
	 * @param valorPorCento Valor por cento a ser utilizado. Exemplos:
	 *                      <p>Ex: Para 10%, informe 10
	 *                      <p>Ex: Para 50%, informe 50
	 *                      <p>Ex: Para 60,5%, informe 60.5
	 */
	private void setPorcento(Long valorPorCento) 
	{
		if(porcento != null)
			this.porcento = (porcento/100);
		else
			this.porcento = 0L;
	}
	
	/**
	 * Get do percentual numérico
	 * @return Valor percentual
	 *         <p> Ex: 0.5
	 */
	public Long getPorcento() 
	{
		return porcento;
	}
	
	/**
	 * Método para retornar um percentual formatado em String
	 * @return Uma String contendo o valor percentual formatado
	 *         <p> Ex: 50%
	 */
	public String getPorcentoFormatado()
	{
		NumberFormat percentFormat = NumberFormat.getPercentInstance();
		percentFormat.setMinimumFractionDigits(1);
		return percentFormat.format(this.porcento);
	}
	
	/**
	 * Acrescenta devida porcentagem do valor parametrizado
	 * @param valor - Parametro contendo o valor ao qual a porcentagem deve ser aplicada.
	 * @return Um Long contendo o valor acrescido da porcentagem parametrizada.
	 */
	public BigDecimal aplicaAcrescimo(BigDecimal valor)
	{
		if(valor == null) throw new PorcentagemException("Impossivel aplicar acrescimo em um valor nulo");
		return valor.add(calculaPorcentagem(valor));
	}
	
	/**
	 * Decrementa devida porcentagem do valor parametrizado
	 * @param valor - Parametro contendo o valor ao qual a porcentagem deve ser aplicada.
	 * @return Um BigDecimal contendo o valor decrescido da porcentagem parametrizada.
	 */
	public BigDecimal aplicaDecrescimo(BigDecimal valor)
	{
		if(valor == null) throw new PorcentagemException("Impossivel aplicar decrescimo em um valor nulo");
		return valor.subtract(calculaPorcentagem(valor));
	}
	
	/**
	 * Calcula o valor percentual do parametro a partir da porcentagem atribuída no método {@link #getInstance(Long)}
	 * @param valor - Parametro contendo o valor para o calculo.
	 * @return Um BigDecimal contendo o percentual do valor.
	 */
	public BigDecimal calculaPorcentagem(BigDecimal valor)
	{
		return new BigDecimal(porcento).multiply(valor);
	}
}
