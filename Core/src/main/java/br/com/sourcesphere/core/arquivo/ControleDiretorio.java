package br.com.sourcesphere.core.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Responsável por gerenciar arquivos de um diretório,
 * com funções como executar o upload de um arquivo,
 * deletar um arquivo ou listar os arquivos de um diretório.
 * @author Guilherme Dio
 * @since 1.0
 *
 */
public final class ControleDiretorio 
{
	/**
	 * Diretorio sendo utilizado
	 */
	private File diretorio;
	
	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(ControleDiretorio.class.getName());
	
	/**
	 * Constroi uma instancia a partir do diretorio informado
	 * @param diretorio - Caminho para o diretorio a ser usado
 	 * @throws FileNotFoundException - Se o diretorio nao existir
	 * @throws IllegalArgumentException - Se o diretorio for nulo
	 * @throws IllegalArgumentException - Se nao for um diretorio
	 */
	public ControleDiretorio(String diretorio) throws FileNotFoundException
	{
		alteraDiretorio(diretorio);
	}
	
	/**
	 * Realiza o upload de um arquivo para o servidor
	 * @param arquivoCliente - Arquivo no client
	 * @throws IllegalArgumentException - Se o arquivo for nulo
	 * @throws RuntimeException - Se ocorrer algum erro adverso durante o upload
	 */
	@SuppressWarnings("resource")
	public void uploadArquivo(File arquivoCliente)
    {
		if(arquivoCliente == null)
		{
			String msg = "O arquivo informado e nulo";
			IllegalArgumentException e = new IllegalArgumentException(msg);
			log.error(msg,e);
			throw e;
		}
		File arquivoServidor = new File(diretorio+"\\"+arquivoCliente.getName());
		
        if(arquivoServidor.exists())
        {
        	log.info("O arquivo '"+arquivoCliente+"' ja existe e sera substituido pelo novo");
            arquivoServidor.delete();
        }
        try 
        { 
            //Cria um canal de comunicação na fonte 
            FileChannel fonte = new FileInputStream(arquivoCliente).getChannel();  

            //Cria um canal de comunicação no destino 
            FileChannel destino = new FileOutputStream(arquivoServidor).getChannel();  

            //Copia o arquivo da fonte para o destino
            destino.transferFrom(fonte,0,fonte.size());  

            //Fecha os canais 
            fonte.close();  
            destino.close();
            
            log.info("O arquivo foi gerado com sucesso no servidor");
        } 
        catch (IOException e) 
        {
        	String msg = "Ocorreu um erro ao tentar realizar o upload do arquivo '"+arquivoCliente+"'";
        	log.error(msg, e);
            throw new RuntimeException(msg,e);
        }  
    }
	
	/**
	 * Deleta um arquivo no servidor
	 * @param arquivo - Arquivo a ser deletado
	 * @throws ArquivoException - Se o arquivo for protegido
	 * @throws IllegalArgumentException - Se o arquivo for nulo
	 * @throws FileNotFoundException - Se o arquivo nao existir
	 */
	public void deletarArquivo(File arquivo) throws FileNotFoundException
	{
		if(arquivo == null)
		{
			String msg = "O arquivo informado e nulo";
			IllegalArgumentException e = new IllegalArgumentException(msg);
			log.error(msg,e);
			throw e;
		}
		
		if(!arquivo.getParent().equals(this.diretorio.getPath()))
		{
			String msg = "O arquivo nao se encontra no diretorio !";
			FileNotFoundException e = new FileNotFoundException(msg);
			log.error(msg,e);
			throw e;
		}
		
		try
		{
			arquivo.delete();
		}
		catch(SecurityException e)
		{
			String msg = "O arquivo '"+arquivo+"' e protegido pelo sistema, e nao pode ser deletado";
			log.error(msg,e);
			throw new ArquivoException(msg,e);
		}
	}
	
	/**
	 * Lista os arquivos contidos no diretorio
	 * @return Lista de Arquivos
	 */
	public List<File> listarArquivos()
	{
		List<File> arquivos = new ArrayList<File>();
		for(File arquivo : diretorio.listFiles())
		{
			arquivos.add(arquivo);
		}
		return arquivos;
	}
	
	/**
	 * Altera o diretorio sendo utilizado
	 * @param diretorio - Pasta/Dietorio a ser carregado
	 * @throws FileNotFoundException - Se o diretorio nao existir
	 * @throws IllegalArgumentException - Se o diretorio for nulo
	 * @throws IllegalArgumentException - Se nao for um diretorio
	 */
	public void alteraDiretorio(String diretorio) throws FileNotFoundException 
	{
		if(diretorio == null)
		{
			String msg = "O diretorio informado e nulo";
			IllegalArgumentException e = new IllegalArgumentException(msg);
			log.error(msg,e);
			throw e;
		}
		
		File diretorioTeste = null;
		if(diretorio.endsWith("\\"))
			diretorioTeste = new File(diretorio);
		else
			diretorioTeste = new File(diretorio + "\\");
		
		if(!diretorioTeste.exists())
		{
			String msg = "O diretorio '"+diretorio+"' nao existe";
			FileNotFoundException e = new FileNotFoundException(msg);
			log.error(msg,e);
			throw e;
		}
		if(!diretorioTeste.isDirectory())
		{
			String msg = diretorio+" nao e um diretorio";
			IllegalArgumentException e = new IllegalArgumentException(msg);
			log.error(msg,e);
			throw e;
		}
		
		this.diretorio = diretorioTeste;
	}
	
	/**
	 * Cria uma pasta dentro do diretorio sendo utilizado
	 * @param nome - Nome da pasta a ser criada
	 * @return {@link File} - Diretorio criado
	 * @throws DiretorioException - Se nao possuir permissao para uso do diretorio
	 */
	public File criarPasta(String nome)
	{
		
		if(diretorio == null)
		{
			String msg = "O nome da pasta a ser criada nao pode ser nulo";
			IllegalArgumentException e = new IllegalArgumentException(msg);
			log.error(msg,e);
			throw e;
		}
		
		File newDir = new File(diretorio, nome);
		try
		{
			newDir.mkdir();
		}
		catch(SecurityException e)
		{
			String msg = "Nao existe permissao para se criar pastas no diretorio '"+diretorio+"'";
			log.error(msg,e);
			throw new DiretorioException(msg,e);
		}
		
		return newDir;
	}
	
	/**
	 * Obtem o espaco livre do diretorio
	 * @return Espaco livre do diretorio em {@link Long}
	 * @throws DiretorioException - Se nao possuir as devidas permissoes
	 */
	public Long getEspacoLivre()
	{
		Long livre = 0L;
		try
		{
			livre = diretorio.getFreeSpace();
		}
		catch(SecurityException e)
		{
			String msg = "Nao existe permissao para consultar propriedades do diretorio '";
			log.error(msg,e);
			throw new DiretorioException(msg,e);
		}
		return livre;
	}
	
	/**
	 * Obtem o espaco total do diretorio
	 * @return Espaco total do diretorio em {@link Long}
	 * @throws DiretorioException - Se nao possuir as devidas permissoes
	 */
	public Long getEspacoTotal()
	{
		Long total = 0L;
		try
		{
			total = diretorio.getTotalSpace();
		}
		catch(SecurityException e)
		{
			String msg = "Nao existe permissao para consultar propriedades do diretorio '"+diretorio+"'";
			log.error(msg,e);
			throw new DiretorioException(msg,e);
		}
		return total;
	}
	
	/**
	 * Obtem o espaco sendo usado pelo diretorio
	 * @return Espaco usado no diretorio em {@link Long}
	 * @throws DiretorioException - Se nao possuir as devidas permissoes
	 */
	public Long getEspacoUsado()
	{
		return getEspacoTotal() - getEspacoLivre();
	}
	
	/**
	 * Renomeia o diretorio
	 * @param novoNome - String contendo o novo nome para o diretorio
	 * @throws IllegalArgumentException - Se o nome estiver nulo
	 * @throws DiretorioException - Se o nome possuir codicoes de um subdiretorio
	 * @throws DiretorioException - Se nao houver permissao
	 * @throws RuntimeException - Se ocorrer algum erro adverso
	 */
	public void renomear(String novoNome)
	{
		if(novoNome == null) throw new IllegalArgumentException("O novo nome do diretorio nao pode ser nulo");
		if(novoNome.contains("\\") || novoNome.contains("/")) throw new DiretorioException("Deve informar um nome concreto e nao um subdiretorio. Ex: 'pasta1'");
		novoNome = novoNome.trim();
		File novoDir = new File(diretorio,novoNome);
		try
		{
			diretorio.renameTo(novoDir);
		}
		catch(SecurityException e)
		{
			String msg = "Nao ha devida permissao para alterar o nome do diretorio '"+diretorio+"'";
			log.error(msg,e);
			throw new DiretorioException(msg,e);
		}
		
		try 
		{
			alteraDiretorio(novoDir.getAbsolutePath());
		} 
		catch (FileNotFoundException e) 
		{
			String msg = "Ocorreu um erro inesperado e o diretorio nao pode ser encontrado e/ou nao foi renomeado corretamente";
			log.error(msg,e);
			throw new RuntimeException(msg,e);
		}
	}
	
	/**
	 * Recupera o diretorio corrente
	 * @return Diretorio atual
	 */
	public String getDiretorioAtual() 
	{
		try
		{
			return this.diretorio.getAbsolutePath();
		}
		catch(SecurityException e)
		{
			String msg = "Nao ha devidas permissoes para visualizar o caminho do diretorio '"+diretorio+"'";
			log.error(msg,e);
			throw new DiretorioException(msg,e);
		}
	}
}
