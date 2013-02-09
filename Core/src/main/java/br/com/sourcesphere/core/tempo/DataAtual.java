package br.com.sourcesphere.core.tempo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataAtual 
{	
	public String getHoraAtual()
	{
		//Identifica horario no servidor
		Date tempo_servidor = new Date(Calendar.getInstance().getTimeInMillis());
		
		//Transforma em 'Horas:Minutos'
		String horario = new SimpleDateFormat("hh:mm").format(tempo_servidor);
		
		return horario;
	}
	
	public String getDataAtual()
	{
		//Identifica horario no servidor
		Date tempo_servidor = new Date(Calendar.getInstance().getTimeInMillis());
		
		//Transforma em 'Horas:Minutos'
		String data = new SimpleDateFormat("dd/MM/yyy").format(tempo_servidor);
		
		return data;
	}
}
