package br.com.sourcesphere.core.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class ControleDiretorio 
{
	private File diretorio;
	public ControleDiretorio(String diretorio)
	{
		if(diretorio.endsWith("\\"))
			this.diretorio = new File(diretorio);
		else
			this.diretorio = new File(diretorio + "\\");
	}
		
	public void uploadArquivo(File arquivoCliente)
    {
		File arquivoServidor = new File(diretorio+"\\"+arquivoCliente.getName());
        if(arquivoServidor.exists())
            arquivoServidor.delete();
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
        } 
        catch (IOException ioe) 
        {
            throw new RuntimeException(ioe);
        }  
    }
	
	public void deletarArquivo(File arquivo) throws FileNotFoundException
	{
		if(!arquivo.getParent().equals(this.diretorio.getPath()))
		{
			throw new FileNotFoundException("O arquivo não se encontra no diretório !");
		}
		arquivo.delete();
	}
	
	public List<File> listarArquivos()
	{
		List<File> arquivos = new ArrayList<File>();
		for(File arquivo : diretorio.listFiles())
		{
			arquivos.add(arquivo);
		}
		return arquivos;
	}
	
	public void setDiretorio(String diretorio) 
	{
		this.diretorio = new File(diretorio);
	}
	public String getDiretorio() 
	{
		return this.diretorio.getAbsolutePath();
	}
}
