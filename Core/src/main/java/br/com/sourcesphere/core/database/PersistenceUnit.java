package br.com.sourcesphere.core.database;

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
