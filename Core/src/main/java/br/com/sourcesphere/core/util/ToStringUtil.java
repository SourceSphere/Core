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
import java.util.List;

/**
 * Classe para imprimir(toString) objetos dinamicamente por reflection
 * @author Guilherme Dio
 * @author Marco Noronha
 * @since 1.0
 */
public final class ToStringUtil
{
	private static ToStringUtil instance;
	
	private EstrategiaToString estrategia;
	
	private ToStringUtil() {}
	
	/**
	 * Monta uma instancia para tostring com a estrategia default
	 * @return ToStringUtil com Estrategia Default
	 */
	public static ToStringUtil getInstance()
	{
		return getInstance(new DefaultEstrategiaToString());
	}
	
	/**
	 * Monta uma instancia para tostring com a estrategia informada
	 * @param estrategia - Tipo de estrategia a ser usada({@link EstrategiaToString})
	 * @return ToStringUtil com a Estrategia informada
	 */
	public static ToStringUtil getInstance(EstrategiaToString estrategia)
	{
		if(instance == null)
			instance = new ToStringUtil();
		instance.setEstrategia(estrategia);
		return instance;
	}
	
	private void setEstrategia(EstrategiaToString estrategia)
	{
		if(estrategia == null) throw new IllegalArgumentException("A estrategia '"+estrategia+"' e invalida");
		this.estrategia = estrategia;
	}
	
	/**
	 * Monta uma String com os nomes do campos do objeto e seus valores
	 * @param objeto - Objeto a ser impresso
	 * @return String contendo os valores dos campos do ojeto
	 * @throws IllegalArgumentException - Se o objeto for nulo
	 */
	public String toString(Object objeto)
	{
		if(objeto == null) throw new IllegalArgumentException("Os objeto '"+objeto+"' e invalido");
		
		ReflectionUtil	reflection = ReflectionUtil.getInstance(objeto.getClass(), objeto);
		List<Field> campos = reflection.getFields();
		StringBuilder sb = new StringBuilder(reflection.getClassName()+":\r\n{");
		for(Field campo : campos)
		{
			if(estrategia.verifica(campo))
			{
				String valor = String.valueOf(reflection.getValue(campo));
				sb.append("\r\n"+campo.getName()+": "+valor);
			}
		}
		sb.append("\r\n}");
		return sb.toString();
	}
	
	/**
	 * Monta uma String com os nomes do campos dos objetos e seus valores
	 * @param objetos - Objetos a serem impressos
	 * @return String contendo os valores dos campos dos ojetos
	 *  @throws IllegalArgumentException - Se os objetos forem nulos
	 */
	public String toString(Object... objetos)
	{
		if(objetos == null) throw new IllegalArgumentException("Os objetos '"+objetos+"' sao invalidos");
		StringBuilder sb = new StringBuilder();
		for(Object objeto : objetos)
			sb.append(this.toString(objeto));
		return sb.toString();
	}
}
