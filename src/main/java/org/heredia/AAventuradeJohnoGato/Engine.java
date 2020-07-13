package org.heredia.AAventuradeJohnoGato;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.heredia.AAventuradeJohnoGato.Ferramentas.Ossos;
import org.heredia.AAventuradeJohnoGato.Ferramentas.Arma;
import org.heredia.AAventuradeJohnoGato.Ferramentas.PoteRacao;
import org.heredia.AAventuradeJohnoGato.Objetos.GatosMalvados;
import org.heredia.ClassesBasicas.Mochila;
import org.heredia.ClassesBasicas.Sala;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Engine {
	private JanelaPrincipal janP;
	private Sala salaInicial;
	private Sala auxiliar;
	private Mochila mochila;
	private Sala salaCorrente;
	private boolean fim;
	private static final String save = ("Save.txt");

	private GameStart gameStart = new GameStart();
	private Rua rua = new Rua();
	private EntradaCasa entradaCasa = new EntradaCasa();
	private SalaDono salaDono = new SalaDono();
	private Cozinha cozinha = new Cozinha();
	private SalaGatos salaGatos = new SalaGatos();	
	
	private EntradaCasa auxE = new EntradaCasa();
	private SalaDono auxS = new SalaDono();
	
	public Engine (JanelaPrincipal jp) {
		janP = jp;
		criaLabirinto();
		mochila = new Mochila();
		salaInicial.entra (mochila);
		salaCorrente = salaInicial;
		fim = false;
	}

	private void criaLabirinto() {

		rua.getPortas().put(entradaCasa.getNome(), entradaCasa);
		
		entradaCasa.getPortas().put(salaDono.getNome(), salaDono);
		entradaCasa.getPortas().put(rua.getNome(), rua);
		
		salaDono.getPortas().put(entradaCasa.getNome(), entradaCasa);
		salaDono.getPortas().put(cozinha.getNome(), cozinha);
		salaDono.getPortas().put(salaGatos.getNome(),salaGatos);
		
		cozinha.getPortas().put(salaDono.getNome(),salaDono);
		
		salaGatos.getPortas().put(salaDono.getNome(), salaDono);

		salaInicial = gameStart;
		auxiliar = rua;
	}

	public void joga (String acao) {
		if (fim) {
			fimDeJogo();
			return;
		}

		String[] tokens = acao.split(" ");
		switch (tokens [0]) {
		case "pega":
			if (salaCorrente.pega(tokens[1])){
				janP.exibeTexto("Ok! " + tokens[1] + " na mochila!" + "\n");
			} else {
				janP.exibeTexto("Objeto " + tokens[1] + " nao encontrado." + "\n");
					}
			break;

		case "inventario":
			janP.exibeTexto("Conteudo na mochila: " + mochila.inventario() + "\n");
			break;

		case "usa":
			try {
				if (salaCorrente.usa(tokens[1])){
					janP.exibeTexto("Feito!");
				} else {
					janP.exibeTexto("Nao e possivel usar " + tokens[1] + " nesta sala");
				}
			} catch (FimDeJogoException e) {
				fim = true;
				fimDeJogo();
			}
			janP.exibeTexto("\n"+salaCorrente.textoDescricao()+"\n");
			janP.setImagem(Integer.parseInt(salaCorrente.getRepVisual()));
			break;

		case "entra":
			Sala novaSala = salaCorrente.sai(tokens[1]);
			if (novaSala == null){
				janP.exibeTexto("Sala desconhecida ou algum obstaculo o impede de passar\n");
			}else {
				if (novaSala.getNome().equals("EntradaCasa") && entradaCasa.getLiberaPorta().equals("false")) {
					janP.playSound("Latido.mp3");
				}
				if (novaSala.getNome().equals("SalaGatos")) {
					janP.playSound("salaGatos.mp3");
				}
				novaSala.entra(mochila);
				salaCorrente = novaSala;
				janP.exibeTexto("\n"+salaCorrente.textoDescricao()+"\n");
				janP.setImagem(Integer.parseInt(salaCorrente.getRepVisual()));
			}
			break;

		case "start":
			janP.exibeTexto("\n"+salaCorrente.textoDescricao()+"\n");
			janP.setImagem(Integer.parseInt(salaCorrente.getRepVisual()));
			break;
			
		case "miar":
			janP.playSound("Miado.mp3");
			break;
	
		case "iniciar":
			if (salaCorrente instanceof GameStart) {
				rua.jaPegou(false);
				cozinha.jaPegouArma(false);
				cozinha.jaPegouPote(false);
				auxiliar.entra(mochila);
				salaCorrente = auxiliar;
				janP.exibeTexto("\n"+salaCorrente.textoDescricao()+"\n");
				janP.setImagem(Integer.parseInt(salaCorrente.getRepVisual()));
				
			}else janP.exibeTexto("\n Comando Invalido \n");
			break;
			
		case "salvar":
			saveGame(save);
			break;
			
		case "carregar":
			loadGame();
			break;
			
		default:
			janP.exibeTexto("Comando desconhecido: "+ tokens[0]+ "\n");
			break;
		}
	}
	private void saveGame(String fileName){
        String salve = (System.getProperty("user.dir").replace('\\', '/') + "/" + fileName);
        String content = "";
    	List<String> aux = new ArrayList<String>();
    	aux =  mochila.inventario();
    	for (int i=0; i<aux.size();i++) {
    		content += (aux.get(i) + " ");
    	}
    	content += "\n" + salaCorrente.getNome();
    	
    	auxE = entradaCasa;
    	content += "\n" + auxE.getLiberaPorta();
    	
    	auxS = salaDono;
    	content += " "+ auxS.getEntregouPote();
    	
    	
        try (BufferedWriter bw = new BufferedWriter (new FileWriter(salve))){
        	bw.write(content);
        	janP.exibeTexto("Jogo Salvo!");
        }catch (IOException e) {
        	e.printStackTrace();
        }
    }
	
	private void loadGame() {
		String carrega = (System.getProperty("user.dir").replace('\\', '/') + "/" + save);
		try (BufferedReader br = new BufferedReader (new FileReader(new File(carrega)))) {
			int line = 0;
			for (String linha = br.readLine(); linha!= null; linha = br.readLine()) {
				if (line == 0) {
					boolean [] j = {false, false, false};
					String [] ferramentas = linha.split(" ");
					for (String ferramenta:ferramentas) {
						if (ferramenta.equals("Ossos")) {
							j[0] = true;
							Ossos osso = new Ossos();
							mochila.guardar(osso);
						}
						if (ferramenta.equals("Arma")) {
							j[1] = true;
							Arma arma = new Arma();
							mochila.guardar(arma);
						}
						if (ferramenta.equals("PoteRacao")) {
							j[2] = true;
							PoteRacao poteRacao = new PoteRacao ();
							mochila.guardar(poteRacao);
						}
					}
					rua.jaPegou(j[0]);
					cozinha.jaPegouArma(j[1]);
					cozinha.jaPegouPote(j[2]);
				}
				if (line == 1) {
					switch (linha) {
					case "Rua":
						salaCorrente = rua;
						
						break;
					
					case "EntradaCasa":
						auxiliar = entradaCasa;
						break;
						
					case "SalaDono":
						auxiliar = salaDono;
						break;
						
					case "Cozinha":
						auxiliar = cozinha;
						break;
						
					case "SalaGatos":
						auxiliar = salaGatos;
						break;
						
					default: 
						break;
					}
				}
				if (line == 2) {
					String [] bols = linha.split(" ");
					entradaCasa.setLiberaPorta(bols[0]);
					salaDono.setEntregouPote(bols[1]);
					if (bols[0].equals("true")) {
						entradaCasa.getObjetos().get("CaoBravo").setAcaoOk(true);
						entradaCasa.setRepVisual("3");
					}
					if (bols[1].equals("true")) {
						salaDono.getObjetos().get("DonoDestraido").setAcaoOk(true);
					}
				}
				line ++;
			}
			auxiliar.entra(mochila);
			salaCorrente = auxiliar;
			janP.exibeTexto("\n"+salaCorrente.textoDescricao()+"\n");
			janP.setImagem(Integer.parseInt(salaCorrente.getRepVisual()));
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fimDeJogo(){
		Alert alert = new Alert (AlertType.INFORMATION);
		Image image = new Image ("file:FimDeJogo.jpg");
		ImageView imageView = new ImageView (image);
		alert.setGraphic(imageView);
		alert.showAndWait();		
	}
}