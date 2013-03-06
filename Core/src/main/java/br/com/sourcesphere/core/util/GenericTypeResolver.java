/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.sourcesphere.core.util;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import br.com.sourcesphere.core.util.Assert;

/**
 * Class for resolving generic types
 * @author Guilherme Dio
 */
@SuppressWarnings("rawtypes")
public abstract class GenericTypeResolver 
{
	private static Assert assertion = new Assert();

	/** Cache from Class to TypeVariable Map */
	private static final Map<Class, Reference<Map<TypeVariable, Type>>> typeVariableCache =
			Collections.synchronizedMap(new WeakHashMap<Class, Reference<Map<TypeVariable, Type>>>());


	/**
	 * Determine the target type for the generic return type of the given method.
	 * @param method the method to introspect
	 * @param clazz the class to resolve type variables against
	 * @return the corresponding generic parameter or return type
	 */
	public static Class<?> resolveReturnType(Method method, Class clazz) {
		assertion.notNull(method, "Method must not be null");
		Type genericType = method.getGenericReturnType();
		assertion.notNull(clazz, "Class must not be null");
		Map<TypeVariable, Type> typeVariableMap = getTypeVariableMap(clazz);
		Type rawType = getRawType(genericType, typeVariableMap);
		return (rawType instanceof Class ? (Class) rawType : method.getReturnType());
	}

	/**
	 * Method usable for discovering generic types
	 */
	public static Class<?> resolveTypeArgument(Class clazz, Class genericIfc) {
		Class[] typeArgs = resolveTypeArguments(clazz, genericIfc);
		if (typeArgs == null) {
			return null;
		}
		if (typeArgs.length != 1) {
			throw new IllegalArgumentException("Expected 1 type argument on generic interface [" +
					genericIfc.getName() + "] but found " + typeArgs.length);
		}
		return typeArgs[0];
	}

	public static Class[] resolveTypeArguments(Class clazz, Class genericIfc) {
		return doResolveTypeArguments(clazz, clazz, genericIfc);
	}

	private static Class[] doResolveTypeArguments(Class ownerClass, Class classToIntrospect, Class genericIfc) {
		while (classToIntrospect != null) {
			Type[] ifcs = classToIntrospect.getGenericInterfaces();
			for (Type ifc : ifcs) {
				if (ifc instanceof ParameterizedType) {
					ParameterizedType paramIfc = (ParameterizedType) ifc;
					Type rawType = paramIfc.getRawType();
					if (genericIfc.equals(rawType)) {
						Type[] typeArgs = paramIfc.getActualTypeArguments();
						Class[] result = new Class[typeArgs.length];
						for (int i = 0; i < typeArgs.length; i++) {
							Type arg = typeArgs[i];
							if (arg instanceof TypeVariable) {
								TypeVariable tv = (TypeVariable) arg;
								arg = getTypeVariableMap(ownerClass).get(tv);
								if (arg == null) {
									arg = extractBoundForTypeVariable(tv);
								}
							}
							result[i] = (arg instanceof Class ? (Class) arg : Object.class);
						}
						return result;
					}
					else if (genericIfc.isAssignableFrom((Class) rawType)) {
						return doResolveTypeArguments(ownerClass, (Class) rawType, genericIfc);
					}
				}
				else if (genericIfc.isAssignableFrom((Class) ifc)) {
					return doResolveTypeArguments(ownerClass, (Class) ifc, genericIfc);
				}
			}
			classToIntrospect = classToIntrospect.getSuperclass();
		}
		return null;
	}

	static Class resolveType(Type genericType, Map<TypeVariable, Type> typeVariableMap) {
		Type rawType = getRawType(genericType, typeVariableMap);
		return (rawType instanceof Class ? (Class) rawType : Object.class);
	}


	static Type getRawType(Type genericType, Map<TypeVariable, Type> typeVariableMap) {
		Type resolvedType = genericType;
		if (genericType instanceof TypeVariable) {
			TypeVariable tv = (TypeVariable) genericType;
			resolvedType = typeVariableMap.get(tv);
			if (resolvedType == null) {
				resolvedType = extractBoundForTypeVariable(tv);
			}
		}
		if (resolvedType instanceof ParameterizedType) {
			return ((ParameterizedType) resolvedType).getRawType();
		}
		else {
			return resolvedType;
		}
	}

	static Map<TypeVariable, Type> getTypeVariableMap(Class clazz) {
		Reference<Map<TypeVariable, Type>> ref = typeVariableCache.get(clazz);
		Map<TypeVariable, Type> typeVariableMap = (ref != null ? ref.get() : null);

		if (typeVariableMap == null) {
			typeVariableMap = new HashMap<TypeVariable, Type>();

			// interfaces
			extractTypeVariablesFromGenericInterfaces(clazz.getGenericInterfaces(), typeVariableMap);

			// super class
			Type genericType = clazz.getGenericSuperclass();
			Class type = clazz.getSuperclass();
			while (type != null && !Object.class.equals(type)) {
				if (genericType instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) genericType;
					populateTypeMapFromParameterizedType(pt, typeVariableMap);
				}
				extractTypeVariablesFromGenericInterfaces(type.getGenericInterfaces(), typeVariableMap);
				genericType = type.getGenericSuperclass();
				type = type.getSuperclass();
			}

			// enclosing class
			type = clazz;
			while (type.isMemberClass()) {
				genericType = type.getGenericSuperclass();
				if (genericType instanceof ParameterizedType) {
					ParameterizedType pt = (ParameterizedType) genericType;
					populateTypeMapFromParameterizedType(pt, typeVariableMap);
				}
				type = type.getEnclosingClass();
			}

			typeVariableCache.put(clazz, new WeakReference<Map<TypeVariable, Type>>(typeVariableMap));
		}

		return typeVariableMap;
	}


	static Type extractBoundForTypeVariable(TypeVariable typeVariable) {
		Type[] bounds = typeVariable.getBounds();
		if (bounds.length == 0) {
			return Object.class;
		}
		Type bound = bounds[0];
		if (bound instanceof TypeVariable) {
			bound = extractBoundForTypeVariable((TypeVariable) bound);
		}
		return bound;
	}

	private static void extractTypeVariablesFromGenericInterfaces(Type[] genericInterfaces, Map<TypeVariable, Type> typeVariableMap) {
		for (Type genericInterface : genericInterfaces) {
			if (genericInterface instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType) genericInterface;
				populateTypeMapFromParameterizedType(pt, typeVariableMap);
				if (pt.getRawType() instanceof Class) {
					extractTypeVariablesFromGenericInterfaces(
							((Class) pt.getRawType()).getGenericInterfaces(), typeVariableMap);
				}
			}
			else if (genericInterface instanceof Class) {
				extractTypeVariablesFromGenericInterfaces(
						((Class) genericInterface).getGenericInterfaces(), typeVariableMap);
			}
		}
	}

	private static void populateTypeMapFromParameterizedType(ParameterizedType type, Map<TypeVariable, Type> typeVariableMap) {
		if (type.getRawType() instanceof Class) {
			Type[] actualTypeArguments = type.getActualTypeArguments();
			TypeVariable[] typeVariables = ((Class) type.getRawType()).getTypeParameters();
			for (int i = 0; i < actualTypeArguments.length; i++) {
				Type actualTypeArgument = actualTypeArguments[i];
				TypeVariable variable = typeVariables[i];
				if (actualTypeArgument instanceof Class) {
					typeVariableMap.put(variable, actualTypeArgument);
				}
				else if (actualTypeArgument instanceof GenericArrayType) {
					typeVariableMap.put(variable, actualTypeArgument);
				}
				else if (actualTypeArgument instanceof ParameterizedType) {
					typeVariableMap.put(variable, actualTypeArgument);
				}
				else if (actualTypeArgument instanceof TypeVariable) {
					// We have a type that is parameterized at instantiation time
					// the nearest match on the bridge method will be the bounded type.
					TypeVariable typeVariableArgument = (TypeVariable) actualTypeArgument;
					Type resolvedType = typeVariableMap.get(typeVariableArgument);
					if (resolvedType == null) {
						resolvedType = extractBoundForTypeVariable(typeVariableArgument);
					}
					typeVariableMap.put(variable, resolvedType);
				}
			}
		}
	}

}
