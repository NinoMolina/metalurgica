/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft;

import metalsoft.presentacion.Principal;

/**
 *
 * @author Nino
 */
public abstract class HiloSyncBase {

    public synchronized void procesarDatos(){
        templatedMethod();
    }

    public abstract void templatedMethod();
    
    public abstract void setVtnPrincipal(Principal vtnPrincipal);
    
    public abstract void start();
    
    public abstract void stop();
}
