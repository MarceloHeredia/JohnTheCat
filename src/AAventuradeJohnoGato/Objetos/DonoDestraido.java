package AAventuradeJohnoGato.Objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;
import AAventuradeJohnoGato.Ferramentas.PoteRacao;
import AAventuradeJohnoGato.Ferramentas.Arma;

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
