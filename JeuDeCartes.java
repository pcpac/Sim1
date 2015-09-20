import java.util.*;   // pour pouvoir utiliser Random

/**
 * Generation de cartes aleatoires.
 * Un jeu de carte contient 52 cartes.  Chacune sera representee par un nombre entre 0 et 51.
 * 
 * @author
 * @version
 */

public class JeuDeCartes {

    private static Random generateur;  // generateur de nombres aleatoires.  Doit etre cree (avec initialiserJeuDeCartes par exemple.)
    
    public static void initialiserJeuDeCarte ( int germe ) {
        
    /*
     * antecedent : -
     * consequent : Initialise le processus aleatoire.  Un meme germe generera les memes cartes
     */
    
         generateur = new Random ( germe );
         
    } // initialiserJeuDeCarte

    private static int alea ( int a, int b ) {
        
    /*
     * antecedent : a <= b (les nombres generes ne seront plus uniformes sinon)
     * consequent : retourne un nombre aleatoire uniformement distribue entre a et b inclusivement.
     */
    
        return (int) Math.floor ( ( b - a + 1 ) * generateur.nextDouble () ) + a;
        
    } // alea

    public static int pigerDeuxCartes () {
        
    /*
     * antecedent : -
     * consequent : retourne un nombre correspondant a deux cartes differentes pigees aleatoirement
     *              dans un jeu de 52 cartes (numerotees de 0 a 51). Les deux premiers chiffres du nombre
     *              correspondent a la premiere carte, les deux derniers correspondent a la deuxieme.
     *              Exemple : 450 => 0450 => 04 et 50 => la carte numero 4 et la carte numero 50. 
     */
    
        int carte1;
        int carte2;
        
        carte1 = alea ( 0, 51 );   // piger au hasard une carte parmi les 52 cartes
        carte2 = alea ( 0, 50 );   // pour simuler le fait qu'il reste 51 cartes
        if ( carte2 >= carte1 ) {  // pour simuler le fait que carte1 ne fait plus partie du paquet
            carte2 = carte2 + 1;
        }
        return carte1 + 100 * carte2;
       
    } // pigerDeuxCartes
        
} // JeuDeCartes
