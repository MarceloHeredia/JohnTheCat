package AAventuradeJohnoGato.Objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;
import AAventuradeJohnoGato.Ferramentas.Ossos;

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
