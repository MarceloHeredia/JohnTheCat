package AAventuradeJohnoGato.Objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;
import AAventuradeJohnoGato.Ferramentas.Arma;

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
