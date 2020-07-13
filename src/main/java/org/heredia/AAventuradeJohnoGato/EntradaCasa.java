package org.heredia.AAventuradeJohnoGato;

import org.heredia.ClassesBasicas.Ferramenta;
import org.heredia.ClassesBasicas.Sala;
import org.heredia.AAventuradeJohnoGato.Ferramentas.Ossos;
import org.heredia.AAventuradeJohnoGato.Objetos.CaoBravo;

public class EntradaCasa extends SalaAAventuradeJohnoGato {
	private boolean liberaporta;
	public EntradaCasa() {
		super("EntradaCasa", "2");
		CaoBravo cao = new CaoBravo();
		this.getObjetos().put("CaoBravo", cao);
		liberaporta = false;
	}
	
	public String getLiberaPorta () {
		String lib = ""+liberaporta;
		return lib;
	}
	
	public void setLiberaPorta(String bol) {
		if (bol.equals("true")) {
			liberaporta=true;
		}
		else liberaporta=false;
		
	}

	@Override
	public String textoDescricao () {
		StringBuilder descricao = new StringBuilder ();
		descricao.append("Voce esta na entrada da casa").append("\n");
		descricao.append("Obstaculos: ").append(this.objetosDisponiveis().toString()).append("\n");
		descricao.append("Portas: ").append(this.portasDisponiveis().toString()).append("\n"); 
		return descricao.toString();
	}

	@Override
	public boolean usa(String ferramenta){
		Ferramenta f = this.getMochila().usar(ferramenta);
		if (f == null || !(f instanceof Ossos)) {
			return false;
		}
		CaoBravo c = (CaoBravo)this.getObjetos().get("CaoBravo");
		c.usar(f);
		liberaporta = true;
		this.setRepVisual("3");
		return true;
	}
	
	@Override
	public Sala sai (String sala) {
		Sala aux = super.sai(sala);
		if (sala.equals("SalaDono")) {
			if (liberaporta == false) {
				aux = null;
			}
		}
		return aux;
	}

	
}