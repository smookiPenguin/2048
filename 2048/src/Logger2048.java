import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Cette classe permet de configurer un logger 
 * et d'afficher les messages log
 * 
 * 
 * @author damien planchamp
 *
 */
 public abstract class Logger2048 {
	 
    //Les différents niveau de logs
    public static final int ALL = 0, DEBUG = 100, INFO = 500, IMPORTANT = 900, OFF = Integer.MAX_VALUE;
 
    //le fichier ou seront ecrit les logs ou la config
    public static final String nomFichierLog = "defaultTrace.txt", nomFichierConfig = "config.txt";

     //Le niveau minimum a afficher
    private static int level;

    //la direction des messages (console ou fichier)
    private static PrintWriter pw;

    
    //séparateur pour le fichier config entre level et printWriter
    private static final String SEPARATEUR = " ";

    /**
     * Mode par dégault, il envera les logs sur la console avec un level DEBUG
     */
    public static void init() {
        level = DEBUG;
        pw = new PrintWriter(System.out);
    }

    /**
     * Mode pour lire les logs supérieurs ou égal au level souhaité
     * @param level : souhaité pour les logs égales ou supérieurs
     */
    public static void init(String level, String flotSortie) {
    	Logger2048.level = convertStringToLevel(level);    	
    	creerFlot((flotSortie));
    }
    
    /**
     * Mode pour utiliser une configuration sauvgardé
     * @param nomFichier : fichier ou l'on recupere la configuration
     */
    public static void init(String nomFichierConfig) {
    	File fichier = new File(nomFichierConfig);
        try {
    		BufferedReader br = new BufferedReader(new FileReader(fichier));
    		String ligne = br.readLine();
            String[] regex = ligne.split(SEPARATEUR, 2); 
            level = convertStringToLevel(regex[0]);
            creerFlot(regex[1]);
            br.close();
        }catch (IOException e) {
			System.err.println("ce fichier n'existe pas");
			e.printStackTrace();
		}			
    }
    

    /**
     * Mode pour sauvegarder une configuration et l'utiliser 
     * 
     * @param : level souhaité pour les logs égales ou supérieurs
     * @param nomFichier : fichier ou l'on stock la configuration
     */
    public static void init(String level, String flotSortie, String nomFichierConfig) {
    	Logger2048.level = convertStringToLevel(level);
    	creerFlot((flotSortie));
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(nomFichierConfig) );
			bw.write(level+SEPARATEUR+flotSortie);
			bw.close();			
		} catch (IOException e) {
			System.err.println("erreur dans l'enregistrement");
		}
    }


    /**
     * Permet d'envoyer des logs 
     *
     * @param level : le niveau du log
     * @param message : le message du log
     */
    public static void log(int level, String message) {
    	if (level >= Logger2048.level) {
        	pw.write(message + "\n");
        	pw.flush();
        }
    }
    
        
    /**
     * Retourne le level correspondant a la chaine 
     * 
     * @param chaine : le string correspondant à un level
     * @return level : le level 
     */ 
    private static int convertStringToLevel(String chaine) {
    	int level = -1;
    	if (chaine.equals("ALL")) {
    		level = Logger2048.ALL;
    	}
    	if (chaine.equals("DEBUG")) {
    		level = Logger2048.DEBUG;
    	}
    	if (chaine.equals("INFO")) {
    		level = Logger2048.INFO;
    	}
    	if (chaine.equals("IMPORTANT")) {
    		level = Logger2048.IMPORTANT;
    	}
    	if (chaine.equals("OFF")) {
    		level = Logger2048.OFF;
    	}
    	
    	return level;
    }
    
    
    /**
     * Creer un PrintWriter adapter à la chaine
     * 
     * @param chaine : un string qui correspond à un flot
     * @return ps : le PrintWriter pour ecrire les message flot
     */
    private static void creerFlot(String chaine) {
    	if (chaine.equals("System.out") || chaine.equals("System.err")) {
        	if (chaine.equals("System.out")) {
        		pw = new PrintWriter(System.out);
        	}
        	if (chaine.equals("System.err")) {
        		pw = new PrintWriter(System.err);
        	}
    	}
    	else {
    		try {
				pw = new PrintWriter(new File(chaine));
			} catch (FileNotFoundException e) {
				System.err.println("erreur dans la conversion string to PrintStream");
			}
    	}
    }
}
