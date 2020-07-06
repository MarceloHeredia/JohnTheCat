package AAventuradeJohnoGato;


public class GameStart extends SalaAAventuradeJohnoGato {

	public GameStart () {
		super ("GameStart", "0");

	}

	@Override
	public String textoDescricao() {
		StringBuilder descricao = new StringBuilder();
		descricao.append("A Aventura de John o Gato").append("\n");
		descricao.append("DIGITE:\n 'iniciar' para comecar o jogo \n 'carregar' para continuar de onde parou");
		return descricao.toString();
	}
	
	@Override
	public boolean usa(String ferramenta) {
		return false;
	}
}