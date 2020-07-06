package AAventuradeJohnoGato;


import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import AAventuradeJohnoGato.Ferramentas.PoteRacao;
import AAventuradeJohnoGato.Ferramentas.Arma;
import AAventuradeJohnoGato.Objetos.DonoDestraido;
import AAventuradeJohnoGato.Objetos.GatosMalvados;

public class SalaDono extends SalaAAventuradeJohnoGato {
	private boolean entregoupote;
	public SalaDono() {
		super("SalaDono", "4");
		DonoDestraido dono = new DonoDestraido();
		this.getObjetos().put("DonoDestraido", dono);
		entregoupote = false;
	}
	
	public String getEntregouPote() {
		String entregou = ""+entregoupote;
		return entregou;
	}
	
	public void setEntregouPote(String bol) {
		if (bol.equals("true")) {
			entregoupote=true;
		}
		else entregoupote=false;
		
	}

	@Override
	public String textoDescricao () {
		StringBuilder descricao = new StringBuilder ();
		descricao.append("Voce esta na sala do seu dono").append("\n");
		descricao.append("Objetos presentes: ").append(this.objetosDisponiveis().toString()).append("\n");
		descricao.append("Porque seu dono claramente e um objeto para voce").append("\n");
		descricao.append("Portas: ").append(this.portasDisponiveis().toString()).append("\n");
		return descricao.toString();
	}

	@Override
	public boolean usa (String ferramenta){
		Ferramenta f = this.getMochila().usar(ferramenta);
		if (f instanceof PoteRacao) {
		DonoDestraido dd = (DonoDestraido)this.getObjetos().get("DonoDestraido");
		dd.usar(f);
		entregoupote = true;
		return true;}
		if (f instanceof Arma) {
			((DonoDestraido)(this.getObjetos().get("DonoDestraido"))).setAcaoOk(true);
			throw new FimDeJogoException();
		}
		 return false;
	}
	
	@Override
	public Sala sai (String sala) {
		Sala aux = super.sai(sala);
		if (sala.equals("SalaGatos")) {
			if (entregoupote == false) {
				aux = null;
			}
		}
		return aux;
	}

}