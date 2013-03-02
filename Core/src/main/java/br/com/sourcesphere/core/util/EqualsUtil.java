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
 * Classe para verificar dinamicamente se objetos são iguais, por reflection
 * @author Guilherme Dio
 * @since 1.0
 */
public final class EqualsUtil
{	
	/**
	 * Objeto principal utilizado na comparação
	 */
	private final Object objeto;
	
	/**
	 * Estratagia de validacao
	 */
	private final EstrategiaEquals estrategia;
	
	/**
	 * Inicializa o EqualsUtil com estrategia default(validacao total)
	 * @param objetoPrincipal - Objeto utilizado para comparar com outros
	 */
	public EqualsUtil(Object objetoPrincipal)
	{
		this(new DefaultEstrategiaEquals(),objetoPrincipal);
	}
	
	/**
	 * Inicializa o EqualsUtil com uma estrategia de validacao
	 * @param estrategia - Estrategia de validacao
	 * @param objetoPrincipal - Objeto utilizado para comparar com outros
	 */
	public EqualsUtil(EstrategiaEquals estrategia,Object objetoPrincipal)
	{
		this.objeto = objetoPrincipal;
		this.estrategia = estrategia;
	}
	
	private Object getObjeto() 
	{
		return objeto;
	}
	
	/**
	 * Realiza a comparação do objeto inicializado em {@link #EqualsUtil(Object)} com outro
	 * @param outroObjeto - Objeto a ser comparado com o principal
	 * @return True se for igual, False se for diferente
	 */
	public Boolean isEquals(Object outroObjeto)
	{
		//Classes dos objetos
		Class<?> clazzObjetoPrincipal = this.getObjeto().getClass();
		Class<?> clazzOutroObjeto = outroObjeto.getClass();
		
		//Verifica se ambos sao da mesma classe
		if(clazzObjetoPrincipal.equals(clazzOutroObjeto))
		{
			//Campos dos objetos
			List<Field> camposObjetoPrincipal = ReflectionUtil.getInstance(clazzObjetoPrincipal, getObjeto()).getFields();
			List<Field> camposOutroObjeto = ReflectionUtil.getInstance(clazzOutroObjeto, outroObjeto).getFields();
			
			//Percorre todos os campos do objeto principal
			for(Field campoObjetoPrincipal : camposObjetoPrincipal)
			{
				//Verifica se a estrategia permite a validação do campo
				if(this.estrategia.verifica(campoObjetoPrincipal))
				{
					//Percorre todos os campos do objeto secundario
					for(Field campoOutroObjeto : camposOutroObjeto)
					{
						//Verifica se o tipos dos campos sao iguais
						if(campoObjetoPrincipal.equals(campoOutroObjeto))
						{
							//Valores dos campos
							Object valorObjetoPrincipal = ReflectionUtil.getInstance(clazzObjetoPrincipal, getObjeto()).getValue(campoObjetoPrincipal);
							Object valorOutroObjeto = ReflectionUtil.getInstance(clazzOutroObjeto, outroObjeto).getValue(campoOutroObjeto);
							
							//Se ambos estiverem com valor nulo, retorna verdadeiro
							if(valorObjetoPrincipal == null && valorOutroObjeto == null)
								return true;
	
							//Verifica se o campo é primitivo ou uma instância
							if(campoObjetoPrincipal.getType().isPrimitive())
							{
								//Verifica se os valores são equivalentes
								return valorObjetoPrincipal.equals(valorOutroObjeto);
							}
							else
							{
								//Verifica se é java.lang
								if(isJavaLang(valorObjetoPrincipal.getClass()))
								{
									//Utiliza o equals do java.lang
									return valorObjetoPrincipal.equals(valorOutroObjeto);
								}
								else
								{
									//Filtra recursivamente
									return new EqualsUtil(valorObjetoPrincipal).isEquals(valorOutroObjeto);
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica se a classe é do pacote java.lang
	 * @param clazz - Classe para verificar
	 * @return True se for java.lang, False se não for java.lang
	 */
	private Boolean isJavaLang(Class<?> clazz)
	{
		return clazz.getPackage().equals(Object.class.getPackage());
	}
	
	
}
