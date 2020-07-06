package AAventuradeJohnoGato;


import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import AAventuradeJohnoGato.Ferramentas.Arma;
import AAventuradeJohnoGato.Objetos.GatosMalvados;


public class SalaGatos extends SalaAAventuradeJohnoGato {

	public SalaGatos (){
		super ("SalaGatos","6");
		GatosMalvados gatos = new GatosMalvados();
		this.getObjetos().put("GatosMalvados", gatos);
		


	}

	@Override
	public String textoDescricao() {
		StringBuilder descricao = new StringBuilder();
		descricao.append("Voce esta na ").append(this.getNome()).append("\n");
		descricao.append("Inimigos ").append(this.objetosDisponiveis().toString()).append(" estao tentando roubar sua comida").append("\n");
		descricao.append("Portas: ").append(this.portasDisponiveis().toString()).append("\n");
		return descricao.toString();
	}

	@Override
	public boolean usa (String ferramenta){
		Ferramenta f = this.getMochila().usar(ferramenta);
		if (f==null || !(f instanceof Arma)){
			return false;
		}
		((GatosMalvados)(this.getObjetos().get("GatosMalvados"))).setAcaoOk(true);
		throw new FimDeJogoException();
	}


}