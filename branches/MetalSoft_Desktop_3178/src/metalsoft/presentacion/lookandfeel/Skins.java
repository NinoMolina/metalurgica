/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion.lookandfeel;

import ch.randelshofer.quaqua.QuaquaLookAndFeel;
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
import com.pagosoft.plaf.PgsLookAndFeel;
import com.seaglasslookandfeel.SeaGlassLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceAutumnLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustCoffeeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel;

/**
 *
 * @author Nino
 */
public enum Skins {

    EASYNTH, IDW, METAL, MOTIF, MULTI, NIMROD, QUAQUA,
    SYNTH, SYNTHETICA, WINDOWS, WINDOWSCLASSIC, NIMBUS,
    SYSTEM, AERO, MCWIN, SMART, ACRYL, ALUMNINIUM, LUNA,
    BERNSTEIN, FAST, HIFI, MINT, NOIRE, SEAGLASS,PGS,
    Autumn, BusinessBlackSteel, BusinessBlueSteel, Business,
    ChallengerDeep, CremeCoffee, Creme, DustCoffee, Dust, EmeraldDusk,
    Gemini, GraphiteAqua, GraphiteGlass, Magellan, Mariner,
    MistAqua, MistSilver, Moderate, NebulaBrickWall, Nebula,
    OfficeBlack2007, OfficeBlue2007, OfficeSilver2007, Raven,
    Sahara, Twilight;

    public static LookAndFeel getPgs(){
        return new PgsLookAndFeel();
    }

    public static LookAndFeel getAutumn() {
        return new SubstanceAutumnLookAndFeel();
    }

    public static LookAndFeel getBusinessBlackSteel() {
        return new SubstanceBusinessBlackSteelLookAndFeel();
    }

    public static LookAndFeel getBusinessBlueSteel() {
        return new SubstanceBusinessBlueSteelLookAndFeel();
    }

    public static LookAndFeel getBusiness() {
        return new SubstanceBusinessLookAndFeel();
    }

    public static LookAndFeel getChallengerDeep() {
        return new SubstanceChallengerDeepLookAndFeel();
    }

    public static LookAndFeel getCremeCoffee() {
        return new SubstanceCremeCoffeeLookAndFeel();
    }

    public static LookAndFeel getCreme() {
        return new SubstanceCremeLookAndFeel();
    }

    public static LookAndFeel getDustCoffee() {
        return new SubstanceDustCoffeeLookAndFeel();
    }

    public static LookAndFeel getDust() {
        return new SubstanceDustLookAndFeel();
    }

    public static LookAndFeel getEmeraldDusk() {
        return new SubstanceEmeraldDuskLookAndFeel();
    }

    public static LookAndFeel getGemini() {
        return new SubstanceGeminiLookAndFeel();
    }

    public static LookAndFeel getGraphiteAqua() {
        return new SubstanceGraphiteAquaLookAndFeel();
    }

    public static LookAndFeel getGraphiteGlass() {
        return new SubstanceGraphiteGlassLookAndFeel();
    }

    public static LookAndFeel getMagellan() {
        return new SubstanceMagellanLookAndFeel();
    }

    public static LookAndFeel getMariner() {
        return new SubstanceMarinerLookAndFeel();
    }

    public static LookAndFeel getMistAqua() {
        return new SubstanceMistAquaLookAndFeel();
    }

    public static LookAndFeel getMistSilver() {
        return new SubstanceMistSilverLookAndFeel();
    }

    public static LookAndFeel getModerate() {
        return new SubstanceModerateLookAndFeel();
    }

    public static LookAndFeel getNebulaBrickWall() {
        return new SubstanceNebulaBrickWallLookAndFeel();
    }

    public static LookAndFeel getNebula() {
        return new SubstanceNebulaLookAndFeel();
    }

    public static LookAndFeel getOfficeBlack2007() {
        return new SubstanceOfficeBlack2007LookAndFeel();
    }

    public static LookAndFeel getOfficeBlue2007() {
        return new SubstanceOfficeBlue2007LookAndFeel();
    }

    public static LookAndFeel getOfficeSilver2007() {
        return new SubstanceOfficeSilver2007LookAndFeel();
    }

    public static LookAndFeel getRaven() {
        return new SubstanceRavenLookAndFeel();
    }

    public static LookAndFeel getSahara() {
        return new SubstanceSaharaLookAndFeel();
    }

    public static LookAndFeel getTwilight() {
        return new SubstanceTwilightLookAndFeel();
    }

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
            Logger.getLogger(Skins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Skins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Skins.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Skins.class.getName()).log(Level.SEVERE, null, ex);
        }
        return UIManager.getLookAndFeel();
    }

    public static LookAndFeel getSynthetica() {
        LookAndFeel x = null;
        try {
            x = new SyntheticaStandardLookAndFeel();
        } catch (ParseException ex) {
            Logger.getLogger(Skins.class.getName()).log(Level.SEVERE, null, ex);
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
