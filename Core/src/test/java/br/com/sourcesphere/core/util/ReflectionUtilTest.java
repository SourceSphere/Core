package br.com.sourcesphere.core.util;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Test;

public class ReflectionUtilTest
{

	@Test
	public void testGetValorPrimitivo() 
	{
		ReflectionUtil util = ReflectionUtil.getInstance(CamposPrimitivosA.class, new CamposPrimitivosA());
		assertEquals('f', util.getValue(util.getField("campoChar")));
	}
	
	@Test
	public void testGetCampoPrimitivo() 
	{
		ReflectionUtil util = ReflectionUtil.getInstance(CamposPrimitivosA.class, new CamposPrimitivosA());
		Field campoChar = util.getField("campoChar");
		assertEquals(true, campoChar.getType().isPrimitive());
	}
	
	@Test
	public void testComparaValorCampoPrimitivoIgual() 
	{
		ReflectionUtil util = ReflectionUtil.getInstance(CamposPrimitivosA.class, new CamposPrimitivosA());
		Field campoCharA = util.getField("campoChar");
		Object valorCharA = util.getValue(campoCharA);
		
		util = ReflectionUtil.getInstance(CamposPrimitivosB.class, new CamposPrimitivosB());
		Field campoCharB = util.getField("campoChar");
		Object valorCharB = util.getValue(campoCharB);
		
		assertEquals(true, valorCharA.equals(valorCharB));
	}
	
	@Test
	public void testComparaValorCampoPrimitivoDiferente() 
	{
		ReflectionUtil util = ReflectionUtil.getInstance(CamposPrimitivosA.class, new CamposPrimitivosA());
		Field campoCharA = util.getField("campoChar");
		Object valorCharA = util.getValue(campoCharA);
		
		util = ReflectionUtil.getInstance(CamposPrimitivosC.class, new CamposPrimitivosC());
		Field campoCharC = util.getField("campoChar");
		Object valorCharC = util.getValue(campoCharC);
		
		assertEquals(false, valorCharA.equals(valorCharC));
	}
	
	private class CamposPrimitivosA
	{
		private int campoInteiro = 5;
		private char campoChar = 'f';
	}
	
	private class CamposPrimitivosB
	{
		private int campoInteiro = 5;
		private char campoChar = 'f';
	}
	
	private class CamposPrimitivosC
	{
		private int campoInteiro = 10;
		private char campoChar = 'g';
	}

}
