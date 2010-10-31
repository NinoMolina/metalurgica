/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion.lookandfeel;

import ch.randelshofer.quaqua.BasicQuaquaLookAndFeel;
import ch.randelshofer.quaqua.QuaquaLookAndFeel;
import ch.randelshofer.quaqua.jaguar.Quaqua14JaguarLookAndFeel;
import ch.randelshofer.quaqua.snow_leopard.Quaqua16SnowLeopardLookAndFeel;
import com.easynth.lookandfeel.EaSynthLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import com.jtattoo.plaf.aero.AeroLookAndFeel;
import com.jtattoo.plaf.mcwin.McWinLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;
import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.fast.FastLookAndFeel;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import com.jtattoo.plaf.luna.LunaLookAndFeel;
import com.jtattoo.plaf.mint.MintLookAndFeel;
import com.jtattoo.plaf.noire.NoireLookAndFeel;

/**
 *
 * @author Nino
 */
public enum Temas {

    EASYNTH, IDW, METAL, MOTIF, MULTI, NIMROD, QUAQUA,
    SYNTH, SYNTHETICA, WINDOWS, WINDOWSCLASSIC, NIMBUS,
    SYSTEM, AERO, MCWIN, SMART, ACRYL, ALUMNINIUM, LUNA,
    BERNSTEIN, FAST, HIFI, MINT, NOIRE;

    public static LookAndFeel getBernstein() {
        return new BernsteinLookAndFeel();
    }

    public static LookAndFeel getFast() {
        return new FastLookAndFeel();
    }

    public static LookAndFeel getHIFI() {
        return new HiFiLookAndFeel();
    }

    public static LookAndFeel getMint() {
        return new MintLookAndFeel();
    }

    public static LookAndFeel getNoire() {
        return new NoireLookAndFeel();
    }

    public static LookAndFeel getLuna() {
        return new LunaLookAndFeel();
    }

    public static LookAndFeel getAluminium() {
        return new AluminiumLookAndFeel();
    }

    public static LookAndFeel getAcryl() {
        return new AcrylLookAndFeel();
    }

    public static LookAndFeel getSmart() {
        return new SmartLookAndFeel();
    }

    public static LookAndFeel getMcWin() {
        return new McWinLookAndFeel();
    }

    public static LookAndFeel getEaSynth() {
        return new EaSynthLookAndFeel();
    }

    public static LookAndFeel getAero() {
        return new AeroLookAndFeel();
    }

    public static LookAndFeel getSystem() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Temas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Temas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Temas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Temas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return UIManager.getLookAndFeel();
    }

    public static LookAndFeel getSynthetica() {
        LookAndFeel x = null;
        try {
            x = new SyntheticaStandardLookAndFeel();
        } catch (ParseException ex) {
            Logger.getLogger(Temas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static LookAndFeel getIdw() {
        return new InfoNodeLookAndFeel();
    }

    public static LookAndFeel getQuaqua() {
        return new QuaquaLookAndFeel();
    }

    public static LookAndFeel getMetal() {
        return new MetalLookAndFeel();
    }

    public static LookAndFeel getNimROD() {
        return new NimRODLookAndFeel();
    }

    public static LookAndFeel getNimbus() {
        return new NimbusLookAndFeel();
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
