package br.com.sourcesphere.core.tempo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TransformaData
{
	private final String modelo;
	
	public TransformaData(String modelo)
	{
		this.modelo = modelo;
	}
	
	public Calendar getCalendar(String data)
	{
		Calendar calendar = Calendar.getInstance();
		try
		{
			calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(data));
			return calendar;
		}
		catch(ParseException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	public Calendar getCalendar(Date data)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}
	
	public Calendar getCalendar(java.sql.Date data)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}
	
	public Date getJavaDate(String data)
	{
		SimpleDateFormat formatador = new SimpleDateFormat(modelo);
		try
		{
			return formatador.parse(data);
		}
		catch(ParseException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	public Date getJavaDate(Calendar data)
	{
		Date date = new Date(data.getTimeInMillis());
		return date;
	}
	
	public java.sql.Date getSqlDate(String data)
	{
		SimpleDateFormat formatador = new SimpleDateFormat(modelo);
		try
		{
			return new java.sql.Date(formatador.parse(data).getTime());
		}
		catch(ParseException ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	public java.sql.Date getSqlDate(Calendar data)
	{
		java.sql.Date date = new java.sql.Date(data.getTimeInMillis());
		return date;
	}

}
