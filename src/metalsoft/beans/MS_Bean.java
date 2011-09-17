package metalsoft.beans;


import java.io.Serializable;
import javax.swing.JComponent;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nino
 */
public class MS_Bean extends JComponent implements Serializable{

    protected String yourName;

    /**
     * Get the value of yourName
     *
     * @return the value of yourName
     */
    public String getYourName() {
        return yourName;
    }

    /**
     * Set the value of yourName
     *
     * @param yourName new value of yourName
     */
    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

}
