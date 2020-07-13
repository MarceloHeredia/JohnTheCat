package org.heredia.AAventuradeJohnoGato.Objetos;

import org.heredia.ClassesBasicas.Ferramenta;
import org.heredia.ClassesBasicas.Objeto;
import org.heredia.AAventuradeJohnoGato.Ferramentas.Ossos;

public class CaoBravo extends Objeto {
	public CaoBravo() {
		super("Cao Bravo","Cao Manso");
	}

	@Override
	public boolean usar(Ferramenta f) {
		if (f instanceof Ossos) {
			this.setAcaoOk(true);
			return true;
		}
		return false;
	}
}
