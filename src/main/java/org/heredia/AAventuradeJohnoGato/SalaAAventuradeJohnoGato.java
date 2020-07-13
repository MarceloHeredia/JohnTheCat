package org.heredia.AAventuradeJohnoGato;
//sala do jogo demo reutilizada para o trabalho
import org.heredia.ClassesBasicas.Sala;

public abstract class SalaAAventuradeJohnoGato extends Sala {

	public SalaAAventuradeJohnoGato(String nome,String repVis) {
		super(nome,repVis);
	}

	@Override
	public boolean pega(String nomeFerramenta) {
		boolean resp = super.pega(nomeFerramenta);
		this.getFerramentas().remove(nomeFerramenta);
		return resp;
	}
}
