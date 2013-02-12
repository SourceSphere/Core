package br.com.sourcesphere.core.util;

import java.util.List;

public interface Ignoravel<P>
{
	void addAllCamposIgnorados(List<P> ignorados);
	void addCampoIgnorado(P ignorado);
	Boolean isIgnorado(P verificar);
	void clear();
}
