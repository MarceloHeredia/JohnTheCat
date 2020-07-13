package org.heredia.AAventuradeJohnoGato.Objetos;

import org.heredia.ClassesBasicas.Ferramenta;
import org.heredia.ClassesBasicas.Objeto;
import org.heredia.AAventuradeJohnoGato.Ferramentas.PoteRacao;
import org.heredia.AAventuradeJohnoGato.Ferramentas.Arma;

public class DonoDestraido extends Objeto {
	public DonoDestraido() {
		super("Dono Destraido","Dono Atencioso");
	}

	@Override
	public boolean usar(Ferramenta f) {
		if (f instanceof PoteRacao || f instanceof Arma) {
			this.setAcaoOk(true);
			return true;
		}
		return false;
	}
}
