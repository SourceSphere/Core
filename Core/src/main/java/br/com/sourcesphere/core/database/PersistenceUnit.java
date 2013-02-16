package br.com.sourcesphere.core.database;

/**
 * Tipo de Persistencia
 * <p>Deve ter o persistence.xml com persistence-unit = main
 * <p>Ou persistence-unit = test
 * @author Guilherme Dio
 *
 */
public enum PersistenceUnit 
{
	TEST("test"),
	MAIN("main");
	
	private final String modulo;
	
	public String modulo()
	{
		return this.modulo;
	}
	
	private PersistenceUnit(String modulo) 
	{
		this.modulo = modulo;
	}
}
