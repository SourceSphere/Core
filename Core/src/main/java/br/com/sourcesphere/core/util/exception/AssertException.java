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

/**
 * RuntimeException for assertion handling
 * @author Guilherme Dio
 *
 */
public class AssertException extends RuntimeException 
{
	/**
	 * Generated SerialUID
	 */
	private static final long serialVersionUID = 8551600486557723651L;

	public AssertException(String msg)
	{
		super(msg);
	}

	public AssertException(Throwable cause)
	{
		super(cause);
	}
	
	public AssertException(String msg,Throwable cause)
	{
		super(msg, cause);
	}
}
