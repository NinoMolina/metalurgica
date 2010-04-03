/*
 * Mensaje.java
 *
 * Created on 8 de junio de 2008, 23:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgSoporte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Armando
 */
public class Mensaje {
    
    //Valores predeterminados para los tipos de mensaje
    
    public static final int TIPO_ERROR=0;
    
    public static final int TIPO_INFORMACION=1;
    
    public static final int TIPO_AVISO=2;
    
    public static final int TIPO_INTERROGACION=3;
    
    
    public Mensaje() {
    }
    
   
    
    
    
    /**
     *Muestra un mensaje del tipo estandar.
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro título indica el tíulo del mensaje. 
     *El parámetro tipo_Mensaje indica el tipo de mensaje, que se refleja en el icono del mismo:
     *TIPO_ERROR=Mensaje de error, 
     *TIPO_INFORMACION=Mensaje de información,
     *TIPO_AVISO=Mensaje de aviso,
     *TIPO_INTEROGACION=Mensaje de interrogación a usuario. 
     *  
     */
    public static void mensaje_Estandar(String texto,String titulo,int tipo_Mensaje){
       if(texto==null || texto.trim().compareTo("")==0){
        return;
       } 
        
        
      if(titulo==null || titulo.trim().compareTo("")==0){ JOptionPane.showMessageDialog(null,texto,"Mensaje",tipo_Mensaje);}
      else{JOptionPane.showMessageDialog(null,texto,titulo,tipo_Mensaje);}
      
    }
    
     /**
     *Muestra un mensaje del tipo estandar.
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro tipo_Mensaje es del tipo:
     
     *TIPO_INFORMACION=Mensaje de información,
      
     *  
     */
    public static void mensaje_Estandar(String texto){
       if(texto==null || texto.trim().compareTo("")==0){
        return;
       } 
        
        
    JOptionPane.showMessageDialog(null,texto,"Mensaje",1);
    
      
    }
       
       
    
 
    
    
    /** 
     *Muestra un mensaje de interrogacion al usuario. 
     *Sus posibles opciones son SI-NO.
     *Segun la opción que se haya seleccionado, el método devuele:
     *SI=0,
     *NO=1,
     *CANCELAR=2.
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro título indica el tíulo del mensaje. 
     */
    public static int mensaje_Consulta_YES_NO_OPTION(String texto,String titulo){
       if(texto==null || texto.trim().compareTo("")==0){
        return -1;
       } 
       
       int opc=1;
        
      
       if(titulo==null || titulo.trim().compareTo("")==0){opc=JOptionPane.showConfirmDialog(null,texto,titulo,JOptionPane.YES_NO_OPTION);}
       else{opc=JOptionPane.showConfirmDialog(null,texto,"Mensaje",JOptionPane.YES_NO_OPTION);}
     
       return opc;
    }
    
    
     /** 
     *Muestra un mensaje de interrogacion al usuario. 
     *Sus posibles opciones son SI-NO.
     *Segun la opción que se haya seleccionado, el método devuele:
     *SI=0,
     *NO=1,
     *CANCELAR=2.
     *El parámetro texto indica lo que va a mostrar el mensaje. 
     */
    public static int mensaje_Consulta_YES_NO_OPTION(String texto){
       if(texto==null || texto.trim().compareTo("")==0){
        return -1;
       } 
       
       int opc=1;
        
      
      
      opc=JOptionPane.showConfirmDialog(null,texto,"Mensaje",JOptionPane.YES_NO_OPTION);
     
       return opc;
    }
    
    /**
     *Muestra un mensaje de interrogacion al usuario. 
     *Sus posibles opciones son SI-NO-CANCELAR.
     *Segun la opción que se haya seleccionado, el método devuele:
     *SI=0,
     *NO=1,
     *CANCELAR=2.
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro título indica el tíulo del mensaje. 
     */
    public static int mensaje_Consulta_YES_NO_CANCEL_OPTION(String texto,String titulo){
     if(texto==null || texto.trim().compareTo("")==0){
        return -1;
     } 
        
     int opc=1;
     
     if(titulo==null || titulo.trim().compareTo("")==0){opc=JOptionPane.showConfirmDialog(null,texto,"Mensaje",JOptionPane.YES_NO_CANCEL_OPTION);}
     else{opc=JOptionPane.showConfirmDialog(null,texto,titulo,JOptionPane.YES_NO_CANCEL_OPTION);}
     return opc;
    }
    
    
    
    
    /**
     * 
     *Muestra un mensaje de interrogacion al usuario. 
     *Sus posibles opciones son SI-NO.
     *Segun la opción que se haya seleccionado, el método devuele:
     *SI=0,
     *NO=1,
     *CANCELAR=2.
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro título indica el tíulo del mensaje.
     *El parámetro tipo_Mensaje indica el tipo de mensaje, que se refleja en el icono del mismo:
     *TIPO_ERROR=Mensaje de error, 
     *TIPO_INFORMACION=Mensaje de información,
     *TIPO_AVISO=Mensaje de aviso,
     *TIPO_INTEROGACION=Mensaje de interrogación a usuario. 
     */
    public static int mensaje_Consulta_YES_NO_OPTION(String texto,String titulo,int tipoMensaje){
       if(texto==null || texto.trim().compareTo("")==0){
        return -1;
       } 
        
       int opc=1;
        
       if(titulo==null || titulo.trim().compareTo("")==0){opc=JOptionPane.showConfirmDialog(null,texto,titulo,JOptionPane.YES_NO_OPTION,tipoMensaje);}
       else{opc=JOptionPane.showConfirmDialog(null,texto,"Mensaje",JOptionPane.YES_NO_OPTION,tipoMensaje);}
     
       return opc;
    }
    
    /**
     * 
     *Muestra un mensaje de interrogacion al usuario. 
     *Sus posibles opciones son SI-NO-CANCELAR.
     *Segun la opción que se haya seleccionado, el método devuele:
     *SI=0,
     *NO=1,
     *CANCELAR=2.
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro título indica el tíulo del mensaje. 
     *El parámetro tipo_Mensaje indica el tipo de mensaje, que se refleja en el icono del mismo:
     *TIPO_ERROR=Mensaje de error, 
     *TIPO_INFORMACION=Mensaje de información,
     *TIPO_AVISO=Mensaje de aviso,
     *TIPO_INTEROGACION=Mensaje de interrogación a usuario.
     */
    public static int mensaje_Consulta_YES_NO_CANCEL_OPTION(String texto,String titulo,int tipoMensaje){
     if(texto==null || texto.trim().compareTo("")==0){
        return -1;
       } 
        
     int opc=1;
     
     if(titulo==null || titulo.trim().compareTo("")==0){opc=JOptionPane.showConfirmDialog(null,texto,"Mensaje",JOptionPane.YES_NO_CANCEL_OPTION,tipoMensaje);}
     else{opc=JOptionPane.showConfirmDialog(null,texto,titulo,JOptionPane.YES_NO_CANCEL_OPTION,tipoMensaje);}
     return opc;
    }
    
    
    

    
    
    
    
    
    /**
     *Muestra un mensaje que permite al usuario ingresar un dato al sistema . 
     *El parámetro texto indica lo que va a mostrar el mensaje.
     *El parámetro título indica el tíulo del mensaje. 
     *El parámetro tipo_Mensaje indica el tipo de mensaje, que se refleja en el icono del mismo:
     *TIPO_ERROR=Mensaje de error, 
     *TIPO_INFORMACION=Mensaje de información,
     *TIPO_AVISO=Mensaje de aviso,
     *TIPO_INTEROGACION=Mensaje de interrogación a usuario. 
     */
    public static String mensaje_Entrada(String texto,String titulo,int tipo_Mensaje){
      if(texto==null || texto.trim().compareTo("")==0){
        return null;
       } 
        
      String valor=null;
              
      if(titulo==null || titulo.trim().compareTo("")==0){valor=JOptionPane.showInputDialog(null,texto,"Mensaje",tipo_Mensaje);}
      else{valor=JOptionPane.showInputDialog(null,texto,titulo,tipo_Mensaje);}
      
      return valor;
    }
    
    
    
    
    
    
    
    
    
}
