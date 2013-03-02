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

package br.com.sourcesphere.core.converter;

/**
 * Classe para conversão de objetos
 * @author Guilherme Dio
 * @param <F> From(tipo original)
 * @param <T> To(tipo de destino)
 */
public interface Converter<F,T>
{
	public T convertTo(F objeto);
	public F convertFrom(T objeto);
}
