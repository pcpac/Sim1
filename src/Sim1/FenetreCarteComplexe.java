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
 * Les images de cartes utilisees dans cette classe proviennent de cet adresse:
 * http://www.jfitz.com/cards/
 * 
 */
public class FenetreCarteComplexe extends JFrame {

 
    // Instance unique pre-initialisee 
    private static FenetreCarteComplexe INSTANCE = new FenetreCarteComplexe();
    
    
    private JLabel carte1;
    private JLabel carte2;
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;
    
    // Point d'acces pour l'instance unique du singleton
    public static FenetreCarteComplexe getInstance()
    {	return INSTANCE;
    }
    

    
    //Constructeur
    private FenetreCarteComplexe(){
        
        this.setTitle("Affichage de vos cartes");
        this.setSize(400,170);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        this.setResizable(false);
        
        //On cree un tapis vert comme fond
        JPanel tapis = new JPanel();
        tapis.setBackground(Color.GREEN);  
        
        //Carte 1
        carte1 = new JLabel(new ImageIcon("images/retourne.png"));
        JPanel panCarte1 = new JPanel();
        panCarte1.setLayout(new BorderLayout());
        panCarte1.add(carte1);
        
        //Carte 2
        carte2 = new JLabel(new ImageIcon("images/retourne.png"));
        JPanel panCarte2 = new JPanel();
        panCarte2.setLayout(new BorderLayout());
        panCarte2.add(carte2);
        
        //Somme
        message1 = new JLabel("");
        message2 = new JLabel("");
        message3 = new JLabel("");
        
        tapis.add(panCarte1);
        tapis.add(panCarte2);
        tapis.add(message1);
        tapis.add(message2);
        tapis.add(message3);
        
        this.setContentPane(tapis); 
        this.setVisible(false);
        
        
    }
    
    public void setCarte1(String nouvelleCarte){
        this.carte1.setIcon(new ImageIcon("images/"+nouvelleCarte+".png"));
    }
    public void setCarte2(String nouvelleCarte){
        this.carte2.setIcon(new ImageIcon("images/"+nouvelleCarte+".png"));
    }
    
    public void setMessage(String nouveauMessage){
        this.message1.setText(nouveauMessage);
        this.message2.setText("");
        this.message3.setText("");
    }

    public void setMessages(String message1, String message2, String message3){
        this.message1.setText(message1);
        this.message2.setText(message2);
        this.message3.setText(message3);
    }

    public void retourneCartes(){
        this.carte1.setIcon(new ImageIcon("images/retourne.png"));
        this.carte2.setIcon(new ImageIcon("images/retourne.png"));
    }
    
    public void fermer(){
        this.setVisible(false);
        this.dispose();
    }
}
