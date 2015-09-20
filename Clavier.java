import java.io.*;

/**********************************************************
 * U Q A M   -   I N F 1 1 2 0   e t   I N F 2 1 2 0
 *
 * Classe permettant l'entree simple au clavier (console).
 *
 * Louise Laforest (laforest.louise@uqam.ca)
 * Creation      : 2004/08/18
 * Modifications : 2004/10/07 - ajout de viderTampon()
 *               : 2005/08/25 - ajout des methodes ...Ln()
 **********************************************************/
 
/*
 * separateur : (utilise par isWhitespace)
 * ----------
 *   voir http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Character.html#isWhitespace(char)
 * 
 *   Un caractere est un separateur en Java si et seulement si l'un des
 *   criteres suivants est rencontre :
 *
 *   - C'est un caractere Unicode espace (SPACE_SEPARATOR, LINE_SEPARATOR,
 *      ou PARAGRAPH_SEPARATOR) mais n'est pas aussi un caractère espace
 *      non-sécable ('\u00A0', '\u2007', '\u202F'). 
 *   - C'est '\u0009', HORIZONTAL TABULATION.
 *   - C'est '\u000A', LINE FEED.                 LF
 *   - C'est '\u000B', VERTICAL TABULATION.
 *   - C'est '\u000C', FORM FEED.
 *   - C'est '\u000D', CARRIAGE RETURN.           CR
 *   - C'est '\u001C', FILE SEPARATOR.
 *   - C'est '\u001D', GROUP SEPARATOR.
 *   - C'est '\u001E', RECORD SEPARATOR.
 *   - C'est '\u001F', UNIT SEPARATOR.
 * 
 *   voir aussi http://www.unicode.org
 *
 * fins de lignes
 * --------------
 *
 *   Les fins de lignes sont codees de facon differente selon la plateforme
 *
 *     UNIX, Linux et Mac OS X			LF			\n
 *     Windows                          CR LF		\r \n
 *     Mac OS Classique                 CR			\r
 *
 *  La presente classe gere les fins de lignes pour UNIX, Linux, Mac OS X et Windows
 */
 
 
public class Clavier {


    public static void viderTampon() {
       
    /* antecedent : -
     * consequent : Vide le tampon d'entree associe a la console.
     */
    
        try {
            System.in.skip( System.in.available() );
        } catch( IOException e ) {
            System.out.println ( "Le tampon d'entree ne peut etre vide." );
        }

    } // viderTampon
    

    public static void lireFinLigne() {

    /*  antecedent : 
     *  consequent : Lit les caracteres, a partir du curseur.
     *               La fin de la ligne est determinee par :
     *                  LF (line feed) ou par CR (carriage return)
     *                  suivi de LF.
     *               LF et CR seront lus.
     */
    
        lireString(); // On ne retourne pas la chaine (String) lue.

    } // lireFinLigne

    public static String lireString() {

    /*  antecedent : 
     *  consequent : Retourne la chaine lue, a partir du curseur.
     *               La fin de la chaine est determinee par :
     *                  LF (line feed) ou par CR (carriage return)
     *                  suivi de LF.
     *               Ni LF ni CR ne seront presents dans la chaine mais
     *               auront ete lus.
     */
    
        char    prochainCaractere;
        String  resultat              = "";
        boolean fini                  = false;

        while ( ! fini ) {
            prochainCaractere = lireChar();           // Lire un caractere
            if ( prochainCaractere == '\r' ) {        // CR
                prochainCaractere = lireChar();
                if ( prochainCaractere == '\n' ) {    // LF
                    fini = true;
                } else {                              // CR doit etre suivi de LF, sous Windows
                    System.out.println (
                        "Erreur fatale dans la methode lireLigne de la classe Clavier.");
                    System.exit(1);                   // 1 : pour signifier un arret anormal
                } // if non LF
            } else if ( prochainCaractere == '\n' ) { // UNIX et Mac OS X
                fini = true;
            } else {   // concatener le caractere a la fin de la chaine
                resultat = resultat + prochainCaractere;  
            }
        } // while
        
        return resultat;
    } // lireString

    public static byte lireByte() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier octet lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut). Le separateur est lu.
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     */
    
        return Byte.parseByte( lireMot() );
        
    } // lireByte

    public static byte lireByteLn() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier octet lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut).
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     *              Le reste de la ligne, s'il y a lieu, est lu et ignore.
     */
    
        byte byteLu;
        byteLu = Byte.parseByte( lireMot() );
        if ( ! finDeLigneLue ) {
            lireFinLigne();
        }
        return byteLu;
        
    } // lireByteLn

    public static short lireShort() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier court lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut). Le separateur est lu.
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     */
    
        return Short.parseShort( lireMot() );
        
    } // lireShort


    public static short lireShortLn() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier court lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut).
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     *              Le reste de la ligne, s'il y a lieu, est lu et ignore.
     */
    
        short shortLu;
        shortLu = Short.parseShort( lireMot() );
        if ( ! finDeLigneLue ) {
            lireFinLigne();
        }
        return shortLu;
        
    } // lireShortLn


    public static int lireInt() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut). Le separateur est lu.
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     */
    
        return Integer.parseInt( lireMot() );
        
    } // lireInt

    public static int lireIntLn() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut).
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     *              Le reste de la ligne, s'il y a lieu, est lu et ignore.
     */
    
        int intLu;
        intLu = Integer.parseInt( lireMot() );
        if ( ! finDeLigneLue ) {
            lireFinLigne();
        }
        return intLu;
        
    } // lireIntLn

    public static long lireLong() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier long lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut). Le separateur est lu.
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     */
    
        return Long.parseLong( lireMot() );
        
    } // lireLong

    public static long lireLongLn() { 
    
    /* antecedent : -
     * consequent : retourne le nombre entier long lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut).
     *              Le nombre peut commencer par - (negatif) mais ne
     *              peut commencer par +.
     *              Le reste de la ligne, s'il y a lieu, est lu et ignore.
     */
    
        long longLu;
        longLu = Long.parseLong( lireMot() );
        if ( ! finDeLigneLue ) {
            lireFinLigne();
        }
        return longLu;
        
    } // lireLongLn

    public static float lireFloat() { 
    
    /* antecedent : -
     * consequent : retourne le nombre reel lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut). Le separateur est lu.
     *              Le nombre peut commencer par - (negatif) ou + (positif).
     *              Le nombre peut ne pas contenir le point decimal.
     */
    
        return Float.parseFloat( lireMot() );
        
    } // lireFloat

    public static float lireFloatLn() { 
    
    /* antecedent : -
     * consequent : retourne le nombre reel lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut).
     *              Le nombre peut commencer par - (negatif) ou + (positif).
     *              Le nombre peut ne pas contenir le point decimal.
     *              Le reste de la ligne, s'il y a lieu, est lu et ignore.
     */
    
        float floatLu;
        floatLu = Float.parseFloat( lireMot() );
        if ( ! finDeLigneLue ) {
            lireFinLigne();
        }
        return floatLu;
        
    } // lireFloatLn


    public static double lireDouble() { 
    
    /* antecedent : 
     * consequent : retourne le nombre reel double lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut). Le separateur est lu.
     *              Le nombre peut commencer par - (negatif) ou + (positif).
     *              Le nombre peut ne pas contenir le point decimal.
     */
    
        return Double.parseDouble( lireMot() );
        
    } // lireDouble

    public static double lireDoubleLn() { 
    
    /* antecedent : 
     * consequent : retourne le nombre reel double lu, a partir du
     *              curseur. La fin du nombre est determinee par un
     *              separateur (voir ci-haut).
     *              Le nombre peut commencer par - (negatif) ou + (positif).
     *              Le nombre peut ne pas contenir le point decimal.
     *              Le reste de la ligne, s'il y a lieu, est lu et ignore.
     */
    
        double doubleLu;
        doubleLu = Double.parseDouble( lireMot() );
        if ( ! finDeLigneLue ) {
            lireFinLigne();
        }
        return doubleLu;
        
    } // lireDoubleLn

    public static char lireChar() {
    
    /* antecedent : -
     * consequent : Retourne le caractere lu, a partir du curseur.
     *              Le curseur est rendu sur le caractere qui suit le
     *              caractere lu.
     */
    
        int codeAscii = -1; // valeur bidon

        try {
            codeAscii = System.in.read();
        } catch( IOException e ) {
            // on ne fait rien
        }

        return ( char ) codeAscii;
        
    } // lireChar
    
    public static char lireCharLn() {
    
    /* antecedent : -
     * consequent : Retourne le caractere lu, a partir du curseur.
     *              Le reste de la ligne est ignore.
     */
    
        String ligneLue;
        
        do {
            ligneLue = lireString();
        } while ( ligneLue.length() == 0 );
        
        return ligneLue.charAt(0);
        
    } // lireCharLn
    
    private static String lireMot() {
    
    /* antecedent : -
     * consequent : Retourne le mot lu, a partir du curseur.
     *              Les separateurs (voir plus haut) de debut sont
     *              ignores. La fin du mot est determinee par un sepa-
     *              rateur. Le separateur qui suit le mot est lu.
     *              Le curseur est rendu sur le premier caractere suiv-
     *              ant le separateur ayant determine la fin du mot.
     */
    
        String resultat = "";
        char   car;            // caractere lu
        
        finDeLigneLue = false;

        // Sauter les separateurs
        
        do {
            car =  lireChar();
        } while ( Character.isWhitespace( car ) );
        
        // car ne contient pas un separateur.
        // Lire et stocker dans resultat jusqu'a un separateur
        
        do {
            resultat = resultat + car;
            car =  lireChar();
        } while ( ! Character.isWhitespace( car ) );

        // car contient un separateur.
        // S'il s'agit de CR, il faut qu'il soit suivi de LF
        
        if ( car == '\n' ) {         // LF
            finDeLigneLue = true;
        } else if ( car == '\r' ) {  // CR
            finDeLigneLue = true;
            car = lireChar();
            if ( car != '\n' ) {     // LF
                System.out.println(
                    "Erreur fatale dans la methode lireMot de la classe Clavier.");
                System.exit(1);      // 1 : pour signifier un arret anormal
            } // if non LF
        } // if CR

        return resultat;
        
    } // lireMot
    
    private static boolean finDeLigneLue; // modifie par lireMot
                
}  // Clavier
