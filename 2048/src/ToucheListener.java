import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import package2048.Jeu2048;


/** 
 * Cette classe permet l'écoute des touches du clavier de la classe fenetre2048 (<em>KeyAdapter</em>)
 * <br/>
 * On peut modifier la grille avec les fleches directionnelles <br/>
 * ou relancer une partie en appuyant sur 'n', mais uniquement à la fin de la partie
 * 
 * @author damien planchamp
 *
 */
public class ToucheListener extends KeyAdapter {
	private Jeu2048 jeu;
	
	public ToucheListener(Jeu2048 jeu) {
		this.jeu = jeu;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			jeu.decaler(Jeu2048.HAUT);
			afficherLog("HAUT");
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			jeu.decaler(Jeu2048.BAS);
			afficherLog("BAS");
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			jeu.decaler(Jeu2048.GAUCHE);
			afficherLog("GAUCHE");
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			jeu.decaler(Jeu2048.DROITE);
			afficherLog("DROITE");
		}
		
		if(jeu.estTermine()) {
			if(e.getKeyCode() == KeyEvent.VK_N) {
				jeu.nouveauJeu();
				Logger2048.log(Logger2048.DEBUG,"Nouvelle partie. NbCols: "+jeu.getNbCols()+", NbLignes: "+jeu.getNbLignes()+", But à atteindre: "+jeu.getNbBut());
			}
		}
	}
	
	private void afficherLog(String direction) {
		Logger2048.log(Logger2048.DEBUG,"Décalage fléche " + direction);
		boolean[][] tabBool = jeu.tableauFusions();
		int[][] tabInt = jeu.getGrilleInt();
		boolean decalageImpossible = true;
		for (int i=0; i<jeu.getNbCols(); i++) {
			for (int j=0; j<jeu.getNbLignes(); j++) {
				if (tabBool[i][j]) { // si il y a fusion à la casee i j
					Logger2048.log(Logger2048.INFO,"  " +tabInt[i][j] + " resultat d'une fusion dans (" + i +"," +j+")");
					decalageImpossible = false;
				}
			}
		}
		if (decalageImpossible) {
			Logger2048.log(Logger2048.IMPORTANT,"  Aucune fusion");
		}
	}
}
