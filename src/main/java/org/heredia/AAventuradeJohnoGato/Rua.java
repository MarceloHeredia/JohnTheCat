package org.heredia.AAventuradeJohnoGato;

import org.heredia.AAventuradeJohnoGato.Ferramentas.Ossos;

public class Rua extends SalaAAventuradeJohnoGato {

	public Rua() {
		super("Rua","1");
	}

	@Override
	public String textoDescricao() {
		StringBuilder descricao = new StringBuilder();
		descricao.append("Voce esta na ").append(this.getNome()).append("\n");
		descricao.append("Ferramentas: ").append(this.ferramentasDisponiveis().toString()).append("\n");
		descricao.append("Portas: ").append(this.portasDisponiveis().toString()).append("\n");
		return descricao.toString();
	}
	
	public void jaPegou(boolean ja) {
		if (ja == false) {
			Ossos ossos = new Ossos();
			this.getFerramentas().put(ossos.getDescricao(),ossos);
		}
	}

	@Override
	public boolean usa(String ferramenta) {
		return false;
	}
}
