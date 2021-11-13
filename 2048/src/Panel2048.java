import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import package2048.Jeu2048;


/**
 * Une classe pour representer la vue du jeu 2048 (<em>La Vue</em>).<br/>
 * 
 * Cette classe est JPanel qui affiche:<br/>
 * <ul>
 * <li>les scores</li>
 * <li>la grille du jeu</li>
 * </ul>
 * <br/>
 * La vue est modifié à chaque modification du modele via la méthode update
 * 
 * 
 * @author damien planchamp
 *
 */
public class Panel2048 extends JPanel implements Observer{
	private static final long serialVersionUID = 8882878491098398931L;

	private Jeu2048 jeu;
	

	
	private final Color couleur0 = new Color(27, 38, 49),
			couleur2 = new Color(241, 196, 15),
			couleur4 = new Color(243, 156, 18),
			couleur8 = new Color(230, 126, 34),
			couleur16 = new Color(211, 84, 0),
			couleur32 = new Color(192, 57, 43),
			couleur64 = new Color(231, 76, 60),
			couleur128 = new Color(155, 89, 182),
			couleur256 = new Color(52, 152, 219),
			couleur512 = new Color(26, 188, 156),
			couleur1024 = new Color(39, 174, 96),
			couleur2048 = new Color(255, 108, 228);
	
/**
 *  Constructeur de la classe
 *  
 *  ajoute le modele à sa vue
 *  appelle la méthode pour afficher le jeu
 *  
 * @param jeu
 */
	public Panel2048(Jeu2048 jeu) {
		this.jeu = jeu;
		jeu.addObserver(this);
		afficherJeu();
		Logger2048.log(Logger2048.DEBUG,"Nouvelle partie. NbCols: "+jeu.getNbCols()+", NbLignes: "+jeu.getNbLignes()+", But à atteindre: "+jeu.getNbBut());
	}
	
	/**
	 * appelle la méthode pour afficher le score et la grille de jeu
	 */
	private void afficherJeu() {		
        this.setLayout(new BorderLayout());
        this.add(score(), BorderLayout.NORTH);
        this.add(jeu(), BorderLayout.CENTER);
	}
	
	/**
	 * @return un jpanel contenant les scores
	 */
	private JPanel score() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    if (jeu.estTermine()) { 
	    	if (jeu.estVainquer()) {
				panel.add(creerLabelTexte("Vous avez gagné"));
	    	}
	    	else {
				panel.add(creerLabelTexte("Vous avez perdu"));
	    	}
			panel.add(creerLabelTexte("Appuyer sur 'n' pour une nouvelle partie"));	
	    }
	    else {
			panel.add(creerLabelTexte("Votre Score: " + Integer.toString(jeu.getScore())));
			panel.add(creerLabelTexte("Objectif: " + Integer.toString(jeu.getNbBut())));
			panel.add(creerLabelTexte("Record: " + Integer.toString(jeu.getBestScore())));	    		    	
	    }
		return panel;
	}
	
	/**
	 * @param texte un string 
	 * @return un jlabel contenant le texte
	 */
	private JLabel creerLabelTexte(String texte) {
		JLabel label = new JLabel(texte);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		return label;
	}

	/**
	 * @return un jpanel contenant la grille de jeu
	 */
	private JPanel jeu() {
		JPanel panel = new JPanel();
		int nbCol = jeu.getNbCols();
		int nbLigne = jeu.getNbLignes();
	    panel.setLayout(new GridLayout(nbCol, nbLigne));
	    for (int i=0; i<nbCol; i++) {
	    	for (int j=0; j<nbLigne; j++) {
	    		panel.add(creerCase(jeu.getGrilleInt()[i][j]));
	    	}
	    }
		return panel;
	}

	/**
	 * @param nombre un int qui le contenu de la case du tableau de la grille du jeu
	 * @return un jlabel contenant le nombre et un fond de couleur
	 */
	private JLabel creerCase(int nombre) {
		JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		label.setBackground(donneCouleur(nombre));
		if (nombre > 0) {
			label.setText(Integer.toString(nombre));
			int taille;
			if(nombre < 999) {
				taille = 30;
			}
			else {
				taille = 25;
			}
			label.setFont(new Font("Arial", Font.BOLD, taille));		
		}
		return label;
	}
	
	/**
	 * @param numero , nombre de la case qu'on traire
	 * @return couleur associé au nombre
	 */
	private Color donneCouleur(int numero) {
		Color couleur = null;
		
		switch (numero) {
		case 2:
			couleur = couleur2;
			break;
		case 4:
			couleur = couleur4;
			break;
		case 8:
			couleur = couleur8;
			break;
		case 16:
			couleur = couleur16;
			break;
		case 32:
			couleur = couleur32;
			break;
		case 64:
			couleur = couleur64;
			break;
		case 128:
			couleur = couleur128;
			break;
		case 256:
			couleur = couleur256;
			break;
		case 512:
			couleur = couleur512;
			break;
		case 1024:
			couleur = couleur1024;
			break;
		case 2048:
			couleur = couleur2048;
			break;
		default:
			couleur = couleur0;
			break;
		}
		return couleur;
	}
			
	@Override
	public void update(Observable o, Object arg) {
		this.removeAll();
		afficherJeu();
		this.revalidate();	
	}


}
