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

package br.com.sourcesphere.core.exception;

/**
 * {@link RuntimeException} for handling errors while initializating classes, or objects
 * @author Guilherme Dio
 *
 */
public class InitializationException extends RuntimeException
{

	/**
	 * Generated SerialUID
	 */
	private static final long serialVersionUID = 4180247098051967002L;
	
	public InitializationException(String msg)
	{
		super(msg);
	}

	public InitializationException(Throwable cause)
	{
		super(cause);
	}
	
	public InitializationException(String msg,Throwable cause)
	{
		super(msg, cause);
	}
	
}
