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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Classe para a leitura de CSV's<br>
 * Recupera o header e os valores.
 * @author Guilherme Dio
 */
public final class LeitorCSV
{
	private final File arquivo;
	private final String separador;
	
	/**
	 * @param arquivo   Arquivo a ser lido.
	 * @param separador Separador dos dados no arquivo.
	 */
	public LeitorCSV(File arquivo,String separador)
	{
		this.arquivo = arquivo;
		this.separador = separador;
	}
	
	/**
	 * @return {@link List}: lista com o header do csv.
	 * @throws FileNotFoundException
	 */
	public List<String> getHeader() throws FileNotFoundException
	{
		Scanner scan = new Scanner(arquivo);
		if(scan.hasNextLine())
		{
			String[] header = scan.nextLine().split(separador);
			scan.close();
			return Arrays.asList(header);
		}
		scan.close();
		throw new IllegalStateException("O arquivo nao possui um cabecalho");
	}
	
	/**
	 * @return {@link List}: lista com os valores
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	public List<Object> getValores() throws FileNotFoundException
	{
		List<Object> valores = new ArrayList<Object>();
		Scanner scan = new Scanner(arquivo);
		if(!scan.hasNextLine()) throw new IllegalStateException("O arquivo nao possui nenhuma linha de valres");
		scan.nextLine();
		while(scan.hasNextLine())
		{
			valores.add(scan.nextLine());
		}
		scan.close();
		return valores;
	}
}