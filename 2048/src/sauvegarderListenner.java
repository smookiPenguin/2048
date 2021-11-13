import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import package2048.Jeu2048;


/**
 * Cette classe est liée au bouton du menu "sauvgarder" de la classe fenetre (<em>ActionListener</em>)
 * <br/>
 * Elle permet de sauvegarder une partie du jeus
 * 
 * @author damien planchamp
 *
 */

public class sauvegarderListenner implements ActionListener {
	private Jeu2048 jeu;
	private String nomFichier;
	
	public sauvegarderListenner(Jeu2048 jeu) {
		this.jeu = jeu;
		nomFichier = "defaultSave.txt";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		enregistrer();
	}

	private void enregistrer(){
		enregistrerSous(nomFichier);
	}

	private void enregistrerSous(String nomFichier) {
		this.nomFichier = nomFichier;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(nomFichier) );
			oos.writeObject(jeu);
			oos.close();
			Logger2048.log(Logger2048.DEBUG,"fichier enregistré sous " + nomFichier);
		} catch (IOException e) {
			Logger2048.log(Logger2048.DEBUG,"Exception enregistrerSous");
		}		
	}
}
