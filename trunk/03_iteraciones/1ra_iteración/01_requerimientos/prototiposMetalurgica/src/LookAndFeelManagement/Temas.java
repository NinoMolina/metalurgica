/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LookAndFeelManagement;

import com.easynth.lookandfeel.EaSynthLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;
import net.infonode.gui.laf.InfoNodeLookAndFeel;

/**
 *
 * @author Nino
 */
public enum Temas {

    EASYNTH,IDW,METAL,MOTIF,MULTI,NIMROD,QUAQUA,SYNTH,SYNTHETICA,WINDOWS,WINDOWSCLASSIC;
    
    /*
    public static final String METAL="METAL";
    public static final String MOTIF="MOTIF";
    public static final String MULTI="MULTI";
    public static final String NIMROD="NIMROD";
    public static final String SYNTH="SYNTH";
    public static final String WINDOWS="WINDOWS";
    public static final String WINDOWSCLASSIC="WINDOWSCLASSIC";
    */

    public static LookAndFeel getEaSynth() {
        return new EaSynthLookAndFeel();
    }

    public static LookAndFeel getSynthetica() {
        LookAndFeel x=null;
        try {
            x= new SyntheticaStandardLookAndFeel();
        } catch (ParseException ex) {
            Logger.getLogger(Temas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static LookAndFeel getIdw() {
        return new InfoNodeLookAndFeel();
    }

    public static LookAndFeel getQuaqua() {
        return null;
    }

    public static LookAndFeel getMetal() {
        return new MetalLookAndFeel();
    }

    public static LookAndFeel getNimROD() {
        return new NimRODLookAndFeel();
    }

    public static LookAndFeel getWindows() {
        return new WindowsLookAndFeel();
    }

    public static LookAndFeel getWindowsClassic() {
        return new WindowsClassicLookAndFeel();
    }

    public static LookAndFeel getMotif() {
        return new MotifLookAndFeel();
    }

    public static LookAndFeel getMulti() {
        return new MultiLookAndFeel();
    }

    public static LookAndFeel getSynth() {
        return new SynthLookAndFeel();
    }
    
}
