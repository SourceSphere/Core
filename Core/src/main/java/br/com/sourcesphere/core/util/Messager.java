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

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import br.com.sourcesphere.core.util.exception.MessagerInitializationException;

/**
 * Util class for retrieving messages from an multi language resource bundle archive
 * @author Guilherme Dio
 *
 */
public final class Messager
{
	/**
	 * Logger
	 */
	private final Logger log = Logger.getLogger(Messager.class);
	
	/**
	 * The resource bundle of messages
	 */
	private final ResourceBundle bundle;
	
	/**
	 * Assert for code validation
	 */
	private final Assert assertion = new Assert(log);
	
	/**
	 * Constructs a {@link Messager} with a base name of {@link ResourceBundle}
	 * for retrieving messages
	 * @param baseName - The base name of the I18n messages
	 */
	public Messager(String baseName) 
	{
		assertion.setExceptionType(MessagerInitializationException.class);
		assertion.notEmpty(baseName);
		bundle = ResourceBundle.getBundle(baseName);
	}
	
	/**
	 * Retrieves a message from the resource bundle
	 * @param key - The key to the message
	 * @return Message
	 */
	public String getMessage(String key)
	{
		assertion.setExceptionType(MessagerInitializationException.class);
		assertion.notEmpty(key);
		return bundle.getString(key);
	}
}
