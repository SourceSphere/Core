package br.com.sourcesphere.core.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

@SuppressWarnings("unused")
public class EqualsUtilTest 
{	
	private EqualsUtil init(Object objetoPrincipal) 
	{
		return new EqualsUtil(objetoPrincipal);
	}

	@Test
	public void testIsEqualsComClassesSimplesIguais() 
	{
		CamposPrimitivosA A = new CamposPrimitivosA();
		CamposPrimitivosA B = new CamposPrimitivosA();
		
		EqualsUtil util = init(A);
		
		assertEquals(true, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesSimplesDiferentes() 
	{
		CamposPrimitivosA A = new CamposPrimitivosA();
		CamposPrimitivosB B = new CamposPrimitivosB();
		
		EqualsUtil util = init(A);
		
		assertEquals(false, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesCompostasBasicasIguais() 
	{
		CamposCompostosBasicoA A = new CamposCompostosBasicoA();
		CamposCompostosBasicoA B = new CamposCompostosBasicoA();
		
		EqualsUtil util = init(A);
		
		assertEquals(true, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesCompostaBasicassDiferentes() 
	{
		CamposCompostosBasicoA A = new CamposCompostosBasicoA();
		CamposCompostosBasicoB B = new CamposCompostosBasicoB();
		
		EqualsUtil util = init(A);
		
		assertEquals(false, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesCompostasAvancadasIguais() 
	{
		CamposCompostosAvancadoA A = new CamposCompostosAvancadoA();
		CamposCompostosAvancadoA B = new CamposCompostosAvancadoA();
		
		EqualsUtil util = init(A);
		
		assertEquals(true, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesCompostasAvancadasDiferentes() 
	{
		CamposCompostosAvancadoA A = new CamposCompostosAvancadoA();
		CamposCompostosAvancadoB B = new CamposCompostosAvancadoB();
		
		EqualsUtil util = init(A);
		
		assertEquals(false, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesCompostasSuperAvancadasIguais() 
	{
		CamposCompostosSuperAvancadoA A = new CamposCompostosSuperAvancadoA();
		CamposCompostosSuperAvancadoA B = new CamposCompostosSuperAvancadoA();
		
		EqualsUtil util = init(A);
		
		assertEquals(true, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClassesCompostasSuperAvancadasDiferentes() 
	{
		CamposCompostosSuperAvancadoA A = new CamposCompostosSuperAvancadoA();
		CamposCompostosSuperAvancadoB B = new CamposCompostosSuperAvancadoB();
		
		EqualsUtil util = init(A);
		
		assertEquals(false, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClasseComCampoIgnoravelDeValorDiferente() 
	{
		ClasseComCampoIgnoravelParaEquals A = new ClasseComCampoIgnoravelParaEquals();
		ClasseComCampoIgnoravelParaEquals B = new ClasseComCampoIgnoravelParaEquals();
		A.id = 5;
		B.id = 10;
		
		EqualsUtil util = init(A);
		util.addCampoIgnorado("id");
		
		assertEquals(true, util.isEquals(B));
	}
	
	@Test
	public void testIsEqualsComClasseComCampoObrigatorioDeValorDiferente() 
	{
		ClasseComCampoIgnoravelParaEquals A = new ClasseComCampoIgnoravelParaEquals();
		ClasseComCampoIgnoravelParaEquals B = new ClasseComCampoIgnoravelParaEquals();
		A.id = 5;
		B.id = 10;
		
		EqualsUtil util = init(A);
		
		assertEquals(false, util.isEquals(B));
	}
	
	private class CamposPrimitivosA
	{
		private int campoInteiro = 5;
		private char campoChar = 'f';
	}
	
	private class CamposPrimitivosB
	{
		private int campoInteiro = 10;
		private char campoChar = 'g';
	}
	
	private class CamposCompostosBasicoA
	{
		CamposPrimitivosA campoA = new CamposPrimitivosA();
	}
	
	private class CamposCompostosBasicoB
	{
		CamposPrimitivosB campoB = new CamposPrimitivosB();
	}
	
	private class CamposCompostosAvancadoA
	{
		CamposCompostosBasicoA campoA = new CamposCompostosBasicoA();
	}
	
	private class CamposCompostosAvancadoB
	{
		CamposCompostosBasicoB campoB = new CamposCompostosBasicoB();
	}
	
	private class CamposCompostosSuperAvancadoA
	{
		BigDecimal teste = new BigDecimal("0.0");
		String campoString = "string";
		Integer campoInteger = 10;
		CamposCompostosBasicoA campoBasicoA = new CamposCompostosBasicoA();
		CamposCompostosAvancadoA campoA = new CamposCompostosAvancadoA();
	}
	
	private class CamposCompostosSuperAvancadoB
	{
		BigDecimal teste = new BigDecimal("0.0");
		String campoString = "string";
		Integer campoInteger = 10;
		CamposCompostosBasicoB campoBasicoB = new CamposCompostosBasicoB();
		CamposCompostosAvancadoB campoB = new CamposCompostosAvancadoB();
	}
	
	private class ClasseComCampoIgnoravelParaEquals
	{
		public Integer id;
		public String nome = "classe com id";
	}
}
