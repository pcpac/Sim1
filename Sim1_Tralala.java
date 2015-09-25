package Sim1;
/**
 * 
 * 
 * @author
 * @version 
 */

public class Sim1_Tralala {

    public static char lireOuiNon () {
        
        char reponse;
        
        System.out.print ( "Voulez-vous jouer une partie ? " );
        reponse = Clavier.lireChar ();
        Clavier.lireFinLigne ();  
        
        while ( reponse != 'o' && reponse != 'n' ) {
            System.out.print ( "*** vous devez repondre par o ou n : " );
            reponse = Clavier.lireChar ();
            Clavier.lireFinLigne ();
        }
        
        return reponse;
    } // lireOuiNon
    
    public static int lireSortePari () {
        
        int reponse;
        
        System.out.println ( "Quel pari voulez-vous faire ?" );
        System.out.print ( " 1 : paire, 2 : sequence, 3 : meme couleur, 4: somme des deux cartes inférieur ou égale à 7 => " );
        reponse = Clavier.lireInt (); 
        
        while ( reponse != 1 && reponse != 2 && reponse != 3 && reponse != 4 ) {
            System.out.print ( "*** vous devez repondre par 1, 2, 3 ou 4 : " );
            reponse = Clavier.lireInt ();
        }
        
        return reponse;
    } // lireSortePari
    
    //Amélioration apportée: pour avoir un solde de base superieur ou egal a 6$
    public static int lireMontantJoueur () {
    
        int reponse;
        
        System.out.print ( "Entrez le montant dont vous disposez (Il faut minimum 6$ pour jouer) : " );
        reponse = Clavier.lireInt();
        
        while ( reponse < 6 ) {
            System.out.print ( "*** Le montant doit etre superieur ou égal a 6 : " );
            reponse = Clavier.lireInt();
        }
        
        return reponse;
    } // lireMontantJoueur
    
    //Amelioration apportee: pour avoir une mise minimal de 3$
    public static int lireMiseJoueur ( int max ) {
    
        int reponse;
        
        System.out.println(" Il y a un cout de 3$ par pige obligatoire! ");
        System.out.print ( "Entrez le montant de la mise ( minimum: 3$, maximum : " + max + ") : " );
        reponse = Clavier.lireInt();
        
        while ( reponse < 3 || reponse > max ) {
            System.out.print ( "*** Le montant doit etre entre 3 et " + max + " : " );
            reponse = Clavier.lireInt();
        }
        
        return reponse;
    } // lireMiseJoueur
    
    public static int laSorte ( int carte ) {
        
    /* antécédent : 0 <= carte <= 51
     * conséquent : retourne la valeur de la carte (0, 1, ... 12)
     *              0 : as, 1 : 2, 2 : 3, ..., 9 : 10, 10 : valet, 11 : dame, 12 : roi
     */
    
        return carte % 13;
        
    } // laSorte
    
    public static int somme2Cartes ( int carte1, int carte2 ) {
        
    /* antécédent : 0 <= carte1 <= 51, 0 <= carte2 <= 51
     * conséquent : retourne la somme des 2 cartes. 
     *              L'As vaut 1, les figures valent 10,
     *              et toutes les autres cartes valent leur chiffre.
     */
    
    //Le + 1 sert à respecter les nouvelles exigences,
    // avant As vaut 0, 2 vaut 1, 3 vaut 2 etc
    // maintenant As vaut 1, 2 vaut 2, 3 vaut 3 etc
    int sorteCarte1 = laSorte(carte1)+ 1;
    int sorteCarte2 = laSorte(carte2) + 1;

    //Met les figures à valeur 10 
    if(sorteCarte1 > 10){
        sorteCarte1 = 10;
    }
    if(sorteCarte2 > 10){
        sorteCarte2 = 10;
    }
    

    
    return (sorteCarte1 + sorteCarte2);
    } //la Somme de 2 cartes
    
    //Modification apportee:fonction pour éviter de complexifié la comprehension du main
    //on sépare la verification de la victoire. Cela facilite l'ajout de nouveau 
    //type de pari.
    public static boolean verifierVictoire(int carte1, int carte2, int typePari){
    /* antécédent : 0 <= carte1 <= 51, 0 <= carte2 <= 51, typePari est dans l'ensemble(1,2,3,4)
     * conséquent : retourne vrai si le pari est gagne faux si il est perdu
     */
        boolean joueurGagne;
        
        switch(typePari){
            case 1:
                joueurGagne = estUnePaire ( carte1, carte2 );
                break;
            case 2:
                joueurGagne = estUneSequence ( carte1, carte2 );
                break;
            case 3:
                joueurGagne = sontMemeCouleur ( carte1, carte2 );
                break;
            case 4:
                joueurGagne = sontInferieurA7 ( carte1, carte2 );
                break;
            default:
                joueurGagne = false;
                break;
        }
        return joueurGagne;
    }
    
    //Modification apportee: fonction pour éviter de complexifié la comprehension du main
    //on a sépare le calcul du gain. Cela facilite le calcul de gains pour
    //des nouveaux type de pari.
    public static int calculGain(int typePari, int mise, int somme2Cartes){
    /* antécédent :typePari est dans l'ensemble(1,2,3,4), mise >= 3, somme2Cartes: la somme des cartes pigees selon le type de pari #4.
     * conséquent : retourne le gain obtenu en fonction du type de parie, de la mise et des regles d'affaire.
     */
        int montantGagne;
       
        switch(typePari){
            case 1:
                montantGagne = 4 * mise;
                break;
            case 2:
                montantGagne = 2 * mise;
                break;
            case 3:
                montantGagne = mise;
                break;
            case 4:
                montantGagne = mise * somme2Cartes;
                break;
            default:
                montantGagne = 0;
                break;
        }
        
        return montantGagne;
    }
    
    public static int laCouleur ( int carte ) {
        
    /* antécédent : 0 <= carte <= 51
     * conséquent : retourne la couleur de la carte (0, 1, 2, 3)
     *              0 : coeur, 1 : carreau, 2 : trefle, 3 : pique
     */
    
        return carte / 13;
        
    } // laCouleur
    
    public static boolean estUnePaire ( int carte1, int carte2 ) { 

    /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
     * conséquent : retourne vrai si carte1 et carte 2 constituent une paire,
     *              faux sinon
     */
    
      return laSorte ( carte1 ) == laSorte ( carte2 );
      
    } // estUnePaire

    public static boolean sontMemeCouleur ( int carte1, int carte2 ) { 

    /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
     * conséquent : retourne vrai si carte1 et carte 2 sont de la même
     *              couleur.  Les 4 couleurs possibles sont : coeur, carreau,
     *              trefle et pique.
     */
        
        return laCouleur ( carte1 ) == laCouleur ( carte2 );
        
    } // sontMemeCouleur

    public static boolean estUneSequence ( int carte1, int carte2 ) { 

    /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
     * conséquent : retourne vrai si carte1 et carte 2 forment une séquence,
     *              peu importe leur couleur, faux sinon.  Une séquence de
     *              deux cartes sont deux cartes de valeur consécutive.  L'as
     *              et le 2 sont considérées comme consécutives ainsi que l'as
     *              et le roi.
     */
    
        int sorte1 = laSorte ( carte1 );
        int sorte2 = laSorte ( carte2 );
    
        return sorte1 == sorte2 + 1 || 
               sorte1 == sorte2 - 1 ||
               sorte1 == 12 && sorte2 == 0 ||    // as et roi
               sorte2 == 12 && sorte1 == 0;
               
    } // estUneSequence
    public static boolean sontInferieurA7 ( int carte1, int carte2 ) { 

    /* antécédent : 0 <= carte1 <= 51 et 0 <= carte2 <= 51
     * conséquent : retourne vrai si l'addition des valeurs de carte 1 et carte2,
     *              sont inférieur ou égale 7.
     */
    
    return (somme2Cartes(carte1,carte2) <= 7);
               
    } 

    public static String chaineCouleur ( int carte ) {
        
        String reponse;
        
        int couleur = laCouleur ( carte );
        if (couleur == 0) {
            reponse = "coeur";
        } else if (couleur == 1) {
            reponse = "carreau";
        } else if (couleur == 2) {
            reponse = "trefle";
        } else {
            reponse = "pique";
        }
        
        return reponse;
    } // chaineCouleur
    
    public static String chaineSorte ( int carte ) {
        
        String reponse;
        
        int sorte = laSorte ( carte );
        if (sorte == 0) {
            reponse = "as";
        } else if (sorte == 10) {
            reponse = "valet";
        } else if (sorte == 11) {
            reponse = "dame";
        } else if (sorte == 12) {
            reponse = "roi";
        } else {
            reponse = String.valueOf ( sorte + 1 );
        }
        
        return reponse;
    } // chaineCouleur
    
    public static void afficherCarte ( int carte ) { 

    /* antécédent : 0 <= carte <= 51
     * conséquent : Affiche la carte selon sa couleur et sa valeur
     */
    
        System.out.print ( chaineSorte ( carte ) + " " + chaineCouleur ( carte ) );
        
    } // afficherCarte
    

    public static void afficherLesDeuxCartes ( int carte1, int carte2 ) {
        
        FenetreCarteComplexe fenetre = FenetreCarteComplexe.getInstance();    
        
        System.out.print ( "Voici la premiere carte : " );
        afficherCarte ( carte1 );
        fenetre.setCarte1(chaineCouleur ( carte1 ) + laSorte ( carte1 ) );
        System.out.println ();
            
        System.out.print ( "Voici la deuxieme carte : " );
        afficherCarte ( carte2 );
        fenetre.setCarte2(chaineCouleur ( carte2 ) + laSorte ( carte2 ) );
        
        System.out.println ( '\n' );

        System.out.print ( "La somme des deux cartes est de : " + somme2Cartes(carte1,carte2));
        
        System.out.println ( '\n' );
            
    } // afficherLesDeuxCartes

    public static void afficherFin ( int montant ) {
        
        System.out.println ( "Merci d'avoir joue avec moi !" );
        System.out.println ( "Vous quittez avec " + montant + " $ en poche." );
        
    } // afficherFin

    public static void initialiserJeuDeCarte () {
        
        System.out.print ( "Entrez un nombre entier pour initialiser le jeu : " );
        JeuDeCartes.initialiserJeuDeCarte ( Clavier.lireInt () );
        System.out.println ();
        
    } // initialiserJeuDeCarte

    
    public static void main ( String[] parametres ) {
                
        char    reponse;        // saisi : pour la reponse o ou n
        int     pari;           // saisi : pour la sorte de pari 1, 2 ou 3
        int     montantJoueur;  // saisi puis ajuste : montant dont dispose le joueur
        int     montantGagne;   // calcule : montant gagne selon le pari effectue.
        
        int     mise;           // saisi : montant mise par le joueur
        int     deuxCartes;     // les deux cartes pigees par l'ordinateur
        int     carte1;         // la premiere carte pigee
        int     carte2;         // la deuxieme carte pigee
        
        boolean joueurGagne;    // si le joueur a gagne ou non la partie 
        

        
        // Initialiser le procede aleatoire
        initialiserJeuDeCarte ();
        
        //Initialisation de la fenetre affichant les cartes complexes
        FenetreCarteComplexe fenetreCarte = FenetreCarteComplexe.getInstance();
                
        // Saisir et valider le montant initial du joueur
        
        montantJoueur = lireMontantJoueur ();
        System.out.println ();
        
        // Boucle pour les parties
        
        reponse = lireOuiNon ();
        System.out.println ();
        
        while ( reponse == 'o' ) { 
            
            //On retourne les 2 carte pour un autre pari
            fenetreCarte.retourneCartes();
            
            // saisie et validation du type de pari
            
            pari = lireSortePari ();
            System.out.println ();
            
            // saisie et validation du montant de la mise
            
            //Nous passons montantJoueur - 3 comme mise maximale car on prend en compte le coût de 3$ par pige
            mise = lireMiseJoueur ( montantJoueur - 3 );
            
            //On enleve le cout de 3$ par mise
            montantJoueur = montantJoueur - 3;
            
            montantJoueur = montantJoueur - mise;
            
            // faire piger deux cartes par l'ordinateur
            
            deuxCartes = JeuDeCartes.pigerDeuxCartes ();
            
            carte1 = deuxCartes / 100;
            carte2 = deuxCartes % 100;
            
            afficherLesDeuxCartes ( carte1, carte2 );
            
            //Modification apportee: Calcul de la somme des cartes pour le type de pari 4
            int somme2Cartes = somme2Cartes(carte1, carte2);
            
            //Modification apportee: On verifie la victoire dans une fonction auxiliaire
            joueurGagne = verifierVictoire(carte1,carte2,pari);
            
            //Modification apportee: On calcul le gain dans une fonction auxilaire
            if(joueurGagne){
                montantGagne = calculGain(pari,mise,somme2Cartes);
            }else{
                montantGagne = 0;
            }
            
            // afficher si le joueur a gagne ou perdu ainsi que son gain s'il y a lieu
            
            if ( joueurGagne ) {
                System.out.println ( "Bravo ! Vous avez gagne " + montantGagne + " $" );
                montantJoueur = montantJoueur + montantGagne;
            } else {
                System.out.println ( "Desole ! Vous avez perdu votre mise !" );
            }
            
            System.out.println ();
            System.out.println ( "Vous disposez maintenant de " + montantJoueur + " $" );
            System.out.println ();
            
            // determiner si on continue ou pas
            
            if ( montantJoueur >= 6 ) {//Amelioration apportee: pour que le solde ne puisse pas descendre en dessous de 6$
                reponse = lireOuiNon ();
            } else {
                System.out.println ( "Vous n'avez plus assez d'argent, vous ne pouvez continuer." );
                reponse = 'n';
            }

        } // boucle de jeu
        
        //On ferme la fenetre
        fenetreCarte.fermer();
        afficherFin ( montantJoueur );
        
    } // main
    
} // Tp2