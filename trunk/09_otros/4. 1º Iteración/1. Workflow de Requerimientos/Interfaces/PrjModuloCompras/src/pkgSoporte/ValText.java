/*
 * ValText.java
 *
 * Created on 8 de junio de 2008, 17:39
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pkgSoporte;

/**
 *
 * @author Armando
 */
public class ValText {
    
    /** Creates a new instance of ValText */
 
    public ValText() {
   }

  public static int getEntero(String m){
    Integer i=Integer.valueOf(m);
    int valor=i.intValue();
    return valor;
  }

  public static float getFloat(String m){
    Float f=Float.valueOf(m);
    float valor=f.floatValue();
    return valor;
  }

  public static double getDouble(String m){
  Double d=Double.valueOf(m);
  double valor=d.doubleValue();
  return valor;
}


  public static Integer getIntegerClass(String m){
    Integer i=Integer.valueOf(m);
    return i;
  }

  public static Float getFloatClass(String m){
    Float f=Float.valueOf(m);
    return f;
  }

  public static String getMayusculas(String m){
    String may=m.toUpperCase();
    return may;
  }

   public static String getMinusculas(String m){
    String may=m.toLowerCase();
    return may;
  }

   public static boolean existeNumeros(String m){
    int i;char nom[]=m.toCharArray();
    char num[]={'0','1','2','3','4','5','6','7','8','9'};
    for(i=0;i<nom.length;i++){
     for(int j=0;j<num.length;j++){
      if(nom[i]==num[j]){return true;}
     }
    }
    return false;

   }
   
   public static boolean existeLetras(String m){
    m=m.toUpperCase();
    int i;char nom[]=m.toCharArray();
    char abc[]={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    for(i=0;i<nom.length;i++){
     for(int j=0;j<abc.length;j++){
      if(nom[i]==abc[j]){return true;}
     }
    }
    return false;

   }
   
 
   public static String getPrimerCrtMayuscula(String m){
    char n[]=m.toCharArray();
    for(int i=1;i<n.length;i++){
     String c=String.valueOf(n[i]);
     c=c.toLowerCase();
     n[i]=c.charAt(0);
    }

    String cad=String.valueOf(n[0]);
    cad=cad.toUpperCase();
    n[0]=cad.charAt(0);
    cad=String.valueOf(n);

    return cad;
  }

  public static String getStringInt (int x){
   String aux=String.valueOf(x);

   return aux;

  }

  public static String getStringDouble (double x){
   String aux=String.valueOf(x);

   return aux;

  }
  
  public static String getStringFloat (float x){
   String aux=String.valueOf(x);

   return aux;

  }
    
    
}
