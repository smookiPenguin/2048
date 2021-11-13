import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import package2048.Jeu2048;


/**
 * Cette classe est liée au bouton "nouvelle partie" de la classe fenetre de la barre du menu (<em>ActionListener</em>)
 * <br/>
 * Elle permet de relancer une partie sans modifier les parametres
 * 
 * @author damienp planchamp
 *
 */

public class nouvellePartieListener implements ActionListener  {
	private Jeu2048 jeu;
	
	
	public nouvellePartieListener(Jeu2048 jeu) {
		this.jeu = jeu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jeu.nouveauJeu();
		Logger2048.log(Logger2048.DEBUG,"Nouvelle partie. NbCols: "+jeu.getNbCols()+", NbLignes: "+jeu.getNbLignes()+", But à atteindre: "+jeu.getNbBut());
	}

}
