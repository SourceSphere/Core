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

package br.com.sourcesphere.core.arquivo;

/**
 * Exception de Runtime para handle de diretorios
 * @author Guilherme Dio
 *
 */
public class DiretorioException extends RuntimeException
{
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 5541355387769945046L;

	public DiretorioException(String msg) 
	{
		super(msg);
	}
	
	public DiretorioException(String msg, Exception e)
	{
		super(msg,e);
	}
}
