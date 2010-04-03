/*
 * Pantalla.java
 *
 * Created on 9 de junio de 2008, 19:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgSoporte;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Armando
 */
public class Pantalla {
    
    /** Creates a new instance of Pantalla */
    public Pantalla() {
        
    }
    
   public static void centrarEnPantallaJDialog(javax.swing.JDialog plla){
        Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension tamañoVtna = kit.getScreenSize();
	int alto = tamañoVtna.height;
	int ancho = tamañoVtna.width;


	plla.setLocation(ancho/4, alto/4);
       
       
       
   } 
   
   public static void centrarEnPantallaJDialog(javax.swing.JDialog plla,int divisorAncho,int divisorAlto){
        Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension tamañoVtna = kit.getScreenSize();
	int alto = tamañoVtna.height;
	int ancho = tamañoVtna.width;


	plla.setLocation(ancho/divisorAncho, alto/divisorAlto);
       
       
       
   } 
   
   public static void centrarEnPantallaJFrame(javax.swing.JFrame plla){
        Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension tamañoVtna = kit.getScreenSize();
	int alto = tamañoVtna.height;
	int ancho = tamañoVtna.width;


	plla.setLocation(ancho/4, alto/4);
   }
   
   public static void centrarEnPantallaJFrame(javax.swing.JFrame plla,int divisorAncho,int divisorAlto){
        Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension tamañoVtna = kit.getScreenSize();
	int alto = tamañoVtna.height;
	int ancho = tamañoVtna.width;


	plla.setLocation(ancho/divisorAncho, alto/divisorAlto);
   }
   
   
   public static void centrarEnPantallaTamañoJFrame(javax.swing.JFrame plla){
   	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension tamañoVtna = kit.getScreenSize();
	int alto = tamañoVtna.height;
	int ancho = tamañoVtna.width;

	
	plla.setSize(ancho/2, alto/2);
	plla.setLocation(ancho/4, alto/4);

       
   }
   
   public static void insertarIcono(javax.swing.JFrame plla,String path){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage(path); //gif y jpeg
	plla.setIconImage(img);
   }
   
  
    
}
