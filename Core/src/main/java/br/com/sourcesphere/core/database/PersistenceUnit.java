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

package br.com.sourcesphere.core.database;

/**
 * Tipo de Persistencia
 * <p>Deve ter o persistence.xml com persistence-unit = main
 * <p>Ou persistence-unit = test
 * @author Guilherme Dio
 *
 */
public enum PersistenceUnit 
{
	TEST("test"),
	MAIN("main");
	
	private final String modulo;
	
	public String modulo()
	{
		return this.modulo;
	}
	
	private PersistenceUnit(String modulo) 
	{
		this.modulo = modulo;
	}
}
