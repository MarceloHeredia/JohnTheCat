package org.heredia.AAventuradeJohnoGato.Objetos;

import org.heredia.ClassesBasicas.Ferramenta;
import org.heredia.ClassesBasicas.Objeto;
import org.heredia.AAventuradeJohnoGato.Ferramentas.Arma;

public class GatosMalvados extends Objeto {
	public GatosMalvados() {
		super("Gatos Malvados","Ameaça Eliminada");
	}

	@Override
	public boolean usar(Ferramenta f) {
		if (f instanceof Arma) {
			this.setAcaoOk(true);
			return true;
		}
		return false;
	}
}
