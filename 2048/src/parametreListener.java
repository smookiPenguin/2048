import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import package2048.Jeu2048;

/**
 * Cette classe est liée aux boutons du menu parametre de la classe fenetre (<em>ActionListener</em>)
 * <br/>
 * Elle permet de modifier directement les parametres du jeu
 * 
 * @author damien planchamp
 *
 */
public class parametreListener implements ActionListener {
	private Jeu2048 jeu;
	private String type;
	private int quantite;
	
	public parametreListener(Jeu2048 jeu, String type, int quantite) {
		this.jeu = jeu;
		this.type = type;
		this.quantite = quantite;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (type == Fenetre2048.LIGNE) {
			jeu.setGrilleInt(new int [quantite][quantite]); //modification du nombre de colone
			Logger2048.log(Logger2048.DEBUG,"modification de la grille, on passe à " + quantite +"x"+ quantite);

		}
		else {
			jeu.setNbBut(quantite); //modification du but
			Logger2048.log(Logger2048.DEBUG,"modification du but, on passe à " + quantite);
		}
		jeu.nouveauJeu();
		Logger2048.log(Logger2048.DEBUG,"Nouvelle partie");
	}
}
