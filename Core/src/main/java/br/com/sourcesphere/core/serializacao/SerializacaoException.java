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

package br.com.sourcesphere.core.serializacao;

/**
 * RuntimeException para encapsular Exceptions causadas <br>
 * durante a serializacao de algum arquivo.
 * @author Guilherme Dio
 */
@SuppressWarnings("serial")
public final class SerializacaoException extends RuntimeException
{
	public SerializacaoException(String msg)
	{
		super(msg);
	}
}
