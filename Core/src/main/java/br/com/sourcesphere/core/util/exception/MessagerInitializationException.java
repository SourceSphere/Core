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

package br.com.sourcesphere.core.util.exception;

import br.com.sourcesphere.core.exception.InitializationException;
import br.com.sourcesphere.core.util.Messager;


/**
 * {@link RuntimeException} for handling initialization errors on {@link Messager}
 * @author guilherme
 *
 */
public class MessagerInitializationException extends InitializationException
{
	/**
	 * Generated SerialUID
	 */
	private static final long serialVersionUID = -6409668841542493587L;
	
	public MessagerInitializationException(String msg)
	{
		super(msg);
	}

	public MessagerInitializationException(Throwable cause)
	{
		super(cause);
	}
	
	public MessagerInitializationException(String msg,Throwable cause)
	{
		super(msg, cause);
	}
}
