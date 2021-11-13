import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import package2048.Jeu2048;


/**
 * Cette classe est liée au bouton du menu "ouvrir" de la classe fenetre (<em>ActionListener</em>)
 * <br/>
 * Elle permet d'ouvrir une ancienne partie du jeu
 * 
 * @author damien planchamp
 *
 */

public class ouvrirListener implements ActionListener {
	private Jeu2048 jeu;
	private String nomFichier;
	
	public ouvrirListener(Jeu2048 jeu) {
		this.jeu = jeu;
		nomFichier = "defaultSave.txt";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File fichier = new File(nomFichier);
		if (fichier.exists()) {
			chargerFichier(fichier);
		}			
	}

	private void chargerFichier(File fichier){	
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(fichier));
			try {
				Jeu2048 jeu = (Jeu2048)ois.readObject() ;
				remplacePartieParSauvegarde(jeu);
			}catch(ClassNotFoundException e) {
				Logger2048.log(Logger2048.DEBUG,"ce fichier n'est pas un jeu 2048");
				e.printStackTrace();				
			}
		} catch (IOException e) {
			Logger2048.log(Logger2048.DEBUG,"ce fichier n'existe pas");
			e.printStackTrace();
		}			
	}
	
	private void remplacePartieParSauvegarde(Jeu2048 jeu) {
		this.jeu.setNbBut(jeu.getNbBut());
		this.jeu.setGrilleInt(jeu.getGrilleInt());
		Logger2048.log(Logger2048.DEBUG,"le fichier " + nomFichier+ " a été chargé");
	}
}
