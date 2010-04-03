/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgSoporteConexionCU;

/**
 *
 * @author Armando
 */
public abstract class Conexion implements Comparable{
  
    public abstract void llamarCU();
    public abstract void addRegistro(Comparable x);
    public abstract void finalizarConexion(); 
   
}
