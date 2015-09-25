package Sim1;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Cyril
 * 
 * Les images de cartes utilisées dans cette classe proviennent de cet adresse:
 * http://www.jfitz.com/cards/
 * 
 */
public class FenetreCarteComplexe extends JFrame {

 
    /** Instance unique pré-initialisée */
    private static FenetreCarteComplexe INSTANCE = new FenetreCarteComplexe();
 
    /** Point d'accès pour l'instance unique du singleton */
    public static FenetreCarteComplexe getInstance()
    {	return INSTANCE;
    }
    
    JLabel carte1;
    JLabel carte2;
    
    private FenetreCarteComplexe(){
        this.setTitle("Affichage de vos cartes");
        this.setSize(250,150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
        
        //On cree un tapis vert comme fond
        JPanel tapis = new JPanel();
        tapis.setBackground(Color.GREEN);  
        
        //Carte 1
        carte1 = new JLabel(new ImageIcon("src/Sim1/images/retourne.png"));
        JPanel panCarte1 = new JPanel();
        panCarte1.setLayout(new BorderLayout());
        panCarte1.add(carte1);
        
        //Carte 2
        carte2 = new JLabel(new ImageIcon("src/Sim1/images/retourne.png"));
        JPanel panCarte2 = new JPanel();
        panCarte2.setLayout(new BorderLayout());
        panCarte2.add(carte2);
        
        tapis.add(panCarte1);
        tapis.add(panCarte2);
        
        this.setContentPane(tapis); 
        this.setVisible(true);
        
        
    }
    public void setCarte1(String nouvelleCarte){
        this.carte1.setIcon(new ImageIcon("src/Sim1/images/"+nouvelleCarte+".png"));
    }
    public void setCarte2(String nouvelleCarte){
        this.carte2.setIcon(new ImageIcon("src/Sim1/images/"+nouvelleCarte+".png"));
    }

    public void retourneCartes(){
        this.carte1.setIcon(new ImageIcon("src/Sim1/images/retourne.png"));
        this.carte2.setIcon(new ImageIcon("src/Sim1/images/retourne.png"));
    }
    
    public void fermer(){
        this.setVisible(false); //you can't see me!
        this.dispose(); //Destroy the JFrame object
    }
}
