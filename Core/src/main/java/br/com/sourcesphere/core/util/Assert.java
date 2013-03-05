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

package br.com.sourcesphere.core.util;

import org.apache.log4j.Logger;

import br.com.sourcesphere.core.util.exception.AssertException;

/**
 * Assertion handler class that protectects the code in validating arguments.
 * Useful for identifying programmer errors at runtime.
 * @author Guilherme Dio
 */
public class Assert 
{
	/**
	 * Logger
	 */
	private static final Logger log = Logger.getLogger(Assert.class);
	
	/**
	 * The default base message
	 */
	private final String baseMsg = "[Assertion fail] - ";
	
	/**
	 * The type of exception to be thrown
	 */
	private Class<? extends RuntimeException> exceptionType;
	
	/**
	 * The logger to be used for registering the checks
	 */
	private Logger classLogger;
	
	/**
	 * Constructs an instance of the {@link Assert} class with an {@link RuntimeException} type to be thrown on assertions check
	 * <p> and a {@link Logger} to register messages of error
	 * @param exceptionType - The {@link RuntimeException} type to be thrown on assertions
	 * @param classLogger - The {@link Logger} to be used for registering messages
	 * @return An instance of {@link Assert}
	 */
	public Assert(Class<? extends RuntimeException> exceptionType,Logger classLogger)
	{
		setExceptionType(exceptionType);
		setClassLogger(classLogger);
	}
	
	/**
	 * Constructs an instance of the {@link Assert} class with a default {@link IllegalArgumentException} value for exception type to be thrown
	 * <p> and a {@link Logger} to register messages of error
	 * @param classLogger - The logger of the class, to register messages
	 * @return An instance of {@link Assert}
	 */
	public Assert(Logger classLogger)
	{
		this(IllegalArgumentException.class,classLogger);
	}
	
	/**
	 * Constructs an instance of the {@link Assert} class with an {@link RuntimeException} type to be thrown on assertions check
	 * <p> and a default {@link Logger} to register messages of error
	 * @param exceptionType - The {@link RuntimeException} type to be thrown on assertions
	 * @param classLogger - The {@link Logger} to be used for registering messages
	 * @return An instance of {@link Assert}
	 */
	public Assert(Class<? extends RuntimeException> exceptionType)
	{
		this(exceptionType,Assert.log);
	}
	
	/**
	 * Constructs an instance of the {@link Assert} class with a default {@link IllegalArgumentException} value for exception type to be thrown
	 * @return An instance of {@link Assert}
	 */
	public Assert() 
	{
		this(Assert.log);
	}
	
	/**
	 * Sets the exception type to be used
	 * @param exceptionType - The {@link RuntimeException} type to be used
	 */
	public void setExceptionType(Class<? extends RuntimeException> exceptionType) 
	{
		this.exceptionType = exceptionType;
	}
	
	/**
	 * Sets the {@link Logger} of the class, to register messages
	 * @param classLogger - The class logger
	 */
	public void setClassLogger(Logger classLogger) 
	{
		this.classLogger = classLogger;
	}
	
	/**
	 * Asserts that an {@link Object} is not null
	 * @param object - {@link Object} to be verified
	 * @param msg - Message to be encapsulated on the exception, and shown in the log
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void notNull(Object object,String msg)
	{
		if(object == null)
			handle(msg);
	}
	
	/**
	 * Asserts that an {@link Object} is not null
	 * @param object - {@link Object} to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void notNull(Object object)
	{
		notNull(object,"this object must not be null");
	}
	
	/**
	 * Asserts that an {@link Object} is null
	 * @param object - {@link Object} to be verified
	 * @param msg - Message to be encapsulated on the exception, and shown in the log
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isNull(Object object,String msg)
	{
		if(object != null)
			handle(msg);
	}
	
	/**
	 * Asserts that an {@link Object} is not null
	 * @param object - {@link Object} to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isNull(Object object)
	{
		isNull(object, getMessage("this object must be null"));
	}
	
	/**
	 * Asserts that an {@link String} is not empty/null
	 * @param value - {@link String} to be verified
	 * @param msg - Message to be encapsulated on the exception, and shown in the log
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void notEmpty(String value,String msg)
	{
		notNull(value,msg);
		if(value.isEmpty())
			handle(msg);
	}
	
	/**
	 * Asserts that an {@link String} is not empty/null
	 * @param value - {@link String} to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void notEmpty(String value)
	{
		notNull(value,getMessage("this string must not be empty/null"));
	}
	
	/**
	 * Asserts that an boolean expression is true
	 * @param value - boolean expression to be verified
	 * @param msg - Message to be encapsulated on the exception, and shown in the log
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isTrue(boolean expression,String msg)
	{
		if(!expression)
			handle(msg);
	}
	
	/**
	 * Asserts that an boolean expression is true
	 * @param value - boolean expression to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isTrue(boolean expression)
	{
		isTrue(expression, getMessage("this expression must be true"));
	}
	
	/**
	 * Asserts that an boolean expression is false
	 * @param value - boolean expression to be verified
	 * @param msg - Message to be encapsulated on the exception, and shown in the log
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isFalse(boolean expression,String msg)
	{
		if(expression)
			handle(msg);
	}
	
	/**
	 * Asserts that an boolean expression is true
	 * @param value - boolean expression to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isFalse(boolean expression)
	{
		isFalse(expression, getMessage("this expression must be false"));
	}
	
	/**
	 * Asserts that an {@link Object} is instance of the specified {@link Class}
	 * @param type - {@link Class} the expected class type
	 * @param object - {@link Object} to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isInstanceOf(Class<?> type,Object object,String msg)
	{
		if(!type.isInstance(object))
			handle(msg);
	}
	
	/**
	 * Asserts that an {@link Object} is instance of the specified {@link Class}
	 * @param type - {@link Class} the expected class type
	 * @param object - {@link Object} to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void isInstanceOf(Class<?> type,Object object)
	{
		isInstanceOf(type, object,getMessage("this object must be of type "+type.getClass()));
	}
	
	/**
	 * Asserts that an {@link Object}[] array does not contain null elements
	 * @param objects - {@link Object}[] array to be verified
	 * @param msg - Message to be encapsulated on the exception, and shown in the log
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void noNullElements(Object[] objects,String msg)
	{
		for(Object object: objects)
			if(object == null)
				handle(msg);
	}
	
	/**
	 * Asserts that an {@link Object}[] array does not contain null elements
	 * @param objects - {@link Object}[] array to be verified
	 * @throws The specified {@link RuntimeException} or the default {@link IllegalArgumentException}(default), if the param do not accept the conditions
	 */
	public void noNullElements(Object[] objects)
	{
		noNullElements(objects,getMessage("the array must not contain null elements"));
	}
	
	/**
	 * Gets an default message to be shown
	 * @param msg
	 * @return
	 */
	protected String getMessage(String msg)
	{
		return baseMsg+msg;
	}
	
	/**
	 * Logs a warn message using the {@link Logger}
	 * @param msg
	 */
	protected void log(String msg)
	{
		classLogger.warn(msg);
	}
	
	/**
	 * Instantiate the {@link RuntimeException} of the specified {@link Class}
	 * @param msg - The message to be shown on the exception when thrown
	 * @return An instance of the specified {@link RuntimeException}
	 */
	protected RuntimeException instantiateException(String msg)
	{
		RuntimeException exception;
		try 
		{
			exception = new RuntimeException(msg,exceptionType.newInstance());
		}
		catch (InstantiationException e) 
		{
			String errorMsg = "Unnable to instantiate the exception '"+exceptionType.getSimpleName()+"'";
			log.error(errorMsg);
			throw new AssertException(errorMsg,e);
		}
		catch (IllegalAccessException e) 
		{
			String errorMsg = "The exception '"+exceptionType.getSimpleName()+"' has illegal access, so it cannot be instatiated";
			log.error(errorMsg);
			throw new AssertException(errorMsg,e);
		}
		return exception;
	}
	
	/**
	 * Handle the event by logging the message and throwing the specified {@link RuntimeException}
	 * @param msg
	 */
	protected void handle(String msg)
	{
		log(msg);
		throw instantiateException(msg);
	}
	
}
