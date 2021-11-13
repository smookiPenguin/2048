import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import package2048.Jeu2048;

/**
 * Une classe pour creer la fentre du jeu 2048 (<em>LA Fenetre</em>).<br/>

 * @author damien planchamp
 *
 */
public class Fenetre2048 extends JFrame {
	private static final long serialVersionUID = 1L;
	public final static String LIGNE = "ligne";
	public final String BUT = "but";
	public static int HAUTEUR = 500, LARGEUR = 400;
	Jeu2048 jeu;
	
	/**
	 * Creer la fenetre du jeu
	 * 
	 * @param jeu
	 */
	public Fenetre2048 (Jeu2048 jeu) {
		this.jeu = jeu;
		setTitle("Tp4 2048");
		setSize(LARGEUR,HAUTEUR);
		setLayout(new BorderLayout());
		Panel2048 panel2048 = new Panel2048(jeu);
		
		this.add(panel2048, BorderLayout.CENTER);
		
		this.addKeyListener(new ToucheListener(jeu));
		creationBarreMenu();
		this.addWindowListener(new FermerFenetre(this));
		setVisible(true);		
	}
	
	/**
	 * Creer la barre du menu
	 * Il permet à l'utilisateur de creer une nouvelle partie, quitter le jeu,
	 * de modifier les parametre du jeu en modifiant le but ou le nombre de ligne/colonne,
	 * de sauvegarder le jeu ou de recuperer une sauvegarde
	 */
	private void creationBarreMenu() {
		JMenuBar menu = new JMenuBar();
		
	    JMenu fichier = new JMenu("Fichier");

		
/*=============================================================================*/
	    JMenu sauvegarde = new JMenu("Sauvegarde");

		JMenuItem sauvegarder = new JMenuItem("Sauvegarder", KeyEvent.VK_S);
	    KeyStroke ctrlSave = KeyStroke.getKeyStroke("control S");
	    sauvegarder.setAccelerator(ctrlSave);
	    sauvegarder.addActionListener(new sauvegarderListenner(jeu));

		JMenuItem ouvrir = new JMenuItem("Ouvrir une partie", KeyEvent.VK_O);
	    KeyStroke ctrlOpen = KeyStroke.getKeyStroke("control O");
	    ouvrir.setAccelerator(ctrlOpen);
	    ouvrir.addActionListener(new ouvrirListener(jeu));
	    
	    sauvegarde.add(sauvegarder);
	    sauvegarde.add(ouvrir);

/*=============================================================================*/	    
	    JMenu parametre = new JMenu("Parametre");
	    
	    JMenu nbLigne = new JMenu("nombre de ligne/colonne");

	    JMenuItem ligneFacile = new JMenuItem("6");
	    ligneFacile.addActionListener(new parametreListener(jeu, LIGNE, 6));
	    JMenuItem ligneMoyen = new JMenuItem("5");
	    ligneMoyen.addActionListener(new parametreListener(jeu, LIGNE, 5));
	    JMenuItem ligneDifficile = new JMenuItem("4");
	    ligneDifficile.addActionListener(new parametreListener(jeu, LIGNE, 4));
	    
	    nbLigne.add(ligneFacile);
	    nbLigne.add(ligneMoyen);
	    nbLigne.add(ligneDifficile);
	    parametre.add(nbLigne);
	    
/*=============================================================================*/	      
	    JMenu nbBut = new JMenu("objectif");

	    JMenuItem butFacile = new JMenuItem("2048");
	    butFacile.addActionListener(new parametreListener(jeu, BUT, 2048));
	    JMenuItem butMoyen = new JMenuItem("1024");
	    butMoyen.addActionListener(new parametreListener(jeu, BUT, 1024));
	    JMenuItem butDifficile = new JMenuItem("512");
	    butDifficile.addActionListener(new parametreListener(jeu, BUT, 512));

	    nbBut.add(butFacile);
	    nbBut.add(butMoyen);
	    nbBut.add(butDifficile);
	    parametre.add(nbBut);

/*=============================================================================*/	      	    
	    fichier.add(sauvegarde);
	    fichier.add(parametre);
	    menu.add(fichier);
	    
		JMenuItem newGame = new JMenuItem("Nouvelle partie", KeyEvent.VK_N);
	    KeyStroke ctrlNew = KeyStroke.getKeyStroke("control N");
	    newGame.setAccelerator(ctrlNew);
	    newGame.addActionListener(new nouvellePartieListener(jeu));

	    menu.add(newGame);

	    JMenuItem quitter = new JMenuItem("Quitter le jeu", KeyEvent.VK_Q);
	    KeyStroke ctrlQuitter = KeyStroke.getKeyStroke("control Q");
	    quitter.setAccelerator(ctrlQuitter);
	    quitter.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent event){
	        	Logger2048.log(Logger2048.DEBUG, "jeu fermé");
	          System.exit(0);
	        }
	      });		

	    menu.add(quitter);
	    
		this.setJMenuBar(menu);	
	}

}
