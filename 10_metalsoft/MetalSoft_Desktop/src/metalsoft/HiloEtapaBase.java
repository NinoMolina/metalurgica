/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft;

/**
 *
 * @author Nino
 */
public abstract class HiloEtapaBase {

    public synchronized void procesarDatos(){
        templatedMethod();
    }

    public abstract void templatedMethod();
}
