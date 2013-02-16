package br.com.sourcesphere.core.util;

import java.lang.reflect.Field;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

/**
 * Classe de utilidades para tratar os componentes de qualquer classe via reflection
 * @author Guilherme Dio
 * @autor Marco Noronha
 * @since 1.0
 */
public final class ReflectionUtil 
{
	private static ReflectionUtil instance;
	
	private Class<?> clazz;
	private Object objeto;
	
	private ReflectionUtil() {}
	
	/**
	 * Metodo que retornar uma instancia unica da classe
	 * @param clazz - Classe do objeto a ser utilizado
	 * @param objeto - Instancia a ser utilizada
	 * @return ReflectionUtil
	 * @throws IllegalArgumentException - Se a classe e/ou o objeto forem invalidos,nulos.
	 * @since 1.0
	 */
	public static ReflectionUtil getInstance(Class<?> clazz,Object objeto)
	{
		if(instance == null)
			instance = new ReflectionUtil();
		instance.setDependencias(clazz, objeto);
		return instance;
	}
	
	/**
	 * Seta as dependencias
	 * @param clazz
	 * @param objeto
	 */
	private void setDependencias(Class<?> clazz,Object objeto)
	{
		setClazz(clazz);
		setObjeto(objeto);
	}
	
	private Class<?> getClazz() 
	{
		return clazz;
	}
	
	private void setClazz(Class<?> clazz)
	{
		if(clazz == null) throw new IllegalArgumentException("A classe '"+String.valueOf(clazz)+"' e invalida");
		this.clazz = clazz;
	}
	
	private Object getObjeto()
	{
		return objeto;
	}
	
	private void setObjeto(Object objeto)
	{
		if(objeto == null) throw new IllegalArgumentException("O objeto '"+String.valueOf(objeto)+"' e invalido");
		this.objeto = objeto;
	}
	
	/**
	 * Recupera um campo especifico a partir da classe informada no {@link #getInstance(Class, Object)}
	 * @param nomeCampo Nome do campo necessario.
	 * @return {@link Field}: Campo especifico da classe
	 */
	public final Field getField(String nomeCampo) 
	{
		return new Mirror().on(instance.getClazz()).reflect().field(nomeCampo);
	}

	/**
	 * Recupera todos os campos da classe informada no {@link #getInstance(Class, Object)}
	 * @return {@link List} de {@link Field}: Lista de todos os campos da classe
	 */
	public final List<Field> getFields() 
	{
		return new Mirror().on(instance.getClazz()).reflectAll().fields();
	}
	
	/**
	 * Recupera valor de um campo do objeto informado no {@link #getInstance(Class, Object)}
	 * @param campo Referencia o campo que se deseja obter o valor.
	 * @return Valor do campo
	 */
	public final Object getValue(Field campo)
	{
		return new Mirror().on(instance.getObjeto()).get().field(campo);
	}
	
	/**
	 * Define um valor em um campo especifico da objeto informado no {@link #getInstance(Class, Object)}
	 * @param campo - Referencia o campo que se deseja obter o valor.
	 * @param valor - Um valor a ser atribuido
	 */
	public final void setValue(Field campo,Object valor)
	{
		try
		{
			new Mirror().on(instance.getObjeto()).set().field(campo).withValue(valor);
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("O campo '"+String.valueOf(campo)+"' e invalido e/ou nao existe",e);
		}
	}
	
	/**
	 * Invoca determinado metodo do objeto informado no {@link #getInstance(Class, Object)}
	 * @param metodo - Metodo a ser invocado
	 * @param args - {@link Parametro} contendo os args que o metodo necessita
	 * @return O retorno do metodo invocado, ou null
	 * @throws IllegalArgumentException - se o metodo for nulo
	 */
	public Object invocarMetodo(String metodo,Parametro<Object> args)
	{
		try
		{
			return new Mirror().on(instance.getObjeto()).invoke().method(metodo).withArgs(args.getParametros());
		}
		catch(IllegalArgumentException e)
		{
			throw new IllegalArgumentException("O metodo '"+String.valueOf(metodo)+"' e invalido e/ou nao existe",e);
		}
	}
	
	
	/**
	 * Recupera o nome simplificado da classe informada no {@link #getInstance(Class, Object)}
	 * <p> Ex: String
	 * <p>     Object
	 * <p>     Integer
	 * @return Nome da classe
	 */
	public final String getClassName()
	{
		return instance.getClazz().getSimpleName();
	}
	
	/**
	 * Recupera o nome do pacote da classe informada no {@link #getInstance(Class, Object)}
	 * <p> Ex: java.lang
	 * <p>     java.util
	 * @return Pacote da classe
	 */
	public final String getPackageName()
	{
		return instance.getClazz().getPackage().getName();
	}
	
	/**
	 * Recupera o pacote e o nome da classe informada no {@link #getInstance(Class, Object)}
	 * <p> Ex: java.lang.String
	 * <p>     java.util.List
	 * @return Pacote+Nome da classe
	 */
	public final String getFullClassName()
	{
		return instance.getClazz().getName();
	}
}