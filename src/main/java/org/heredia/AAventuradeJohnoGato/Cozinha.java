	package org.heredia.AAventuradeJohnoGato;


import org.heredia.ClassesBasicas.Ferramenta;
import org.heredia.ClassesBasicas.Sala;
import org.heredia.AAventuradeJohnoGato.Ferramentas.Arma;
import org.heredia.AAventuradeJohnoGato.Ferramentas.PoteRacao;

public class Cozinha extends SalaAAventuradeJohnoGato {

	public Cozinha (){
		super ("Cozinha", "5");

	}
	
	public void jaPegouArma (boolean jp) {
		if (jp == false) {
			Arma arma = new Arma();
			this.getFerramentas().put(arma.getDescricao(), arma);
		}
	}
	
	public void jaPegouPote (boolean jp) {
		if (jp == false) {
			PoteRacao pote = new PoteRacao();
			this.getFerramentas().put(pote.getDescricao(),pote);
		}
	}

	@Override
	public String textoDescricao() {
		StringBuilder descricao = new StringBuilder();
		descricao.append("Voce esta na "	).append(this.getNome()).append("\n");
		descricao.append("Ferramentas: ").append(this.ferramentasDisponiveis().toString()).append("\n");
		descricao.append("Portas: ").append(this.portasDisponiveis().toString()).append("\n");
		return descricao.toString();
	}

	@Override
	public boolean usa(String ferramenta) {
		return false;
	}

}