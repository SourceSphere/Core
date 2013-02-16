package br.com.sourcesphere.core.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

/**
 * Implementação de um gerenciador de contextos JPA
 * @author Guilherme Dio
 *
 * @param <T> - Tipo da classe
 */
public final class DatabaseManager<T> 
{
	private static Logger log = Logger.getLogger(DatabaseManager.class);
    private static EntityManagerFactory managerFactory;
    @PersistenceContext
    private static EntityManager manager;
    private static EntityTransaction transaction;
    private static PersistenceUnit unit;
    
    /**
     * Retorna um entity manager a partir da persistence unit
     * <p>Deve conter o id 'main' ou 'test' no persistence.xml
     * @param unit
     */
    public static void init(PersistenceUnit unit) 
    {
    	setUnit(unit);
        managerFactory = getEntityManagerFactory();
        manager = getEntityManager();
    }
    
    private static void setUnit(PersistenceUnit unit) 
    {
    	if(unit == null)
    		throw new NullPointerException("A PersistenceUnit esta nula.");
    	DatabaseManager.unit = unit;
	}
    
    private static PersistenceUnit getUnit()
    {
    	if(unit == null)
    		throw new IllegalStateException("O DatabaseManager nao foi devidamente inicializada pelo metodo 'init()'");
    	return unit;
    }
    
    private static EntityManagerFactory getEntityManagerFactory()
    {
    	if(managerFactory == null || !managerFactory.isOpen())
    		managerFactory = Persistence.createEntityManagerFactory(getUnit().modulo());
    	return managerFactory;
    }

    private static EntityManager getEntityManager() 
    {
        if(manager == null || !manager.isOpen())
        	manager = getEntityManagerFactory().createEntityManager();
        return manager;
    }

    private static EntityTransaction getTransaction() 
    {
        if(transaction == null || !transaction.isActive())
        	transaction = getEntityManager().getTransaction();
        return transaction;
    }

    public static void beginTransaction() 
    {
        getTransaction().begin();
    }

    public static void endTransaction(boolean commit)
    {
        if (commit)
        {
            getTransaction().commit();
        } 
        else 
        {
            getTransaction().rollback();
        }
    }
    
    public static void flush() 
    {
        getEntityManager().flush();
    }

    public static void clear() 
    {
        getEntityManager().clear();
    }
    
    public static void close()
    {
        try 
        {
            getEntityManager().close();
        }
        catch (Exception e) 
        {
        	log.warn("Ocorreu uma exception ao tentar fechar o DatabaseManager: "+e.getMessage(),e);
        }
    }
    
    public static Query createNamedQuery(String query) 
    {
        return getEntityManager().createNamedQuery(query);
    }
    
    public static Query createQuery(String query) 
    {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object entity)
    {
        getEntityManager().persist(entity);
    }
    
    public static void refresh(Object entity)
    {
        getEntityManager().refresh(entity);
    }

    public static void remove(Object entity) 
    {
        getEntityManager().remove(entity);
    }

    public T merge(T entity) 
    {
        entity = getEntityManager().merge(entity);
        return entity;
    }

    public T find(Class<T> clazz, Object key)
    {
        return getEntityManager().find(clazz, key);
    }
    
	@SuppressWarnings("unchecked")
	public List<T> list(Class<T> clazz) 
	{
        Query query = getEntityManager().createQuery("FROM " + clazz.getName() + " AS o");
        return query.getResultList();
    }    
}