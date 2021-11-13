import package2048.Jeu2048;

/**
 * Une classe pour lancer le programme (<em>Le Main</em>).<br/>

 * 
 * @author damien planchamp
 *
 */
public class Main{

	public static void main(String[] args){
		//Logger2048.init(); //défault
		//Logger2048.init("ALL", "System.out"); // on affiche tout dans la console
		Logger2048.init("ALL", Logger2048.nomFichierLog); // on affiche tout dans le fichier
		//Logger2048.init("ALL", "System.err", Logger2048.nomFichierConfig); // on sauvegarde la config dans le fichier
		//Logger2048.init(Logger2048.nomFichierConfig);	//on utilise une config sauvegardé
		
		
		new Fenetre2048(new Jeu2048());
	}
}
