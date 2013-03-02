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
 * Estrategia de EqualsUtil default
 * @author Guilherme Dio
 *
 */
public final class DefaultEstrategiaEquals implements EstrategiaEquals
{
	@Override
	public Boolean verifica(Field campo) 
	{
		return true;
	}
}
