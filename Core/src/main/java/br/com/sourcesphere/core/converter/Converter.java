package br.com.sourcesphere.core.converter;

/**
 * Classe para conversão de objetos
 * @author Guilherme Dio
 * @param <F> From(tipo original)
 * @param <T> To(tipo de destino)
 */
public interface Converter<F,T>
{
	public T convertTo(F objeto);
	public F convertFrom(T objeto);
}
