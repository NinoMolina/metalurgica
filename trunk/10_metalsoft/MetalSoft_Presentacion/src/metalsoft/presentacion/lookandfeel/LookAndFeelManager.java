/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion.lookandfeel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nino
 */
public class LookAndFeelManager {

    @SuppressWarnings("empty-statement")
    public static void setLookAndFeel(Temas t) {
        try {
            //seleccion del tema visual
            switch (t) {
                case EASYNTH:
                    UIManager.setLookAndFeel(Temas.getEaSynth());
                    break;
                case IDW:
                    UIManager.setLookAndFeel(Temas.getIdw());
                    break;
                case METAL:
                    UIManager.setLookAndFeel(Temas.getMetal());
                    break;
                case MOTIF:
                    UIManager.setLookAndFeel(Temas.getMotif());
                    break;
                case MULTI:
                    UIManager.setLookAndFeel(Temas.getMulti());
                    break;
                case NIMBUS:
                    UIManager.setLookAndFeel(Temas.getNimbus());
                    break;
                case NIMROD:
                    UIManager.setLookAndFeel(Temas.getNimROD());
                    break;
                case QUAQUA:
                    UIManager.setLookAndFeel(Temas.getQuaqua());
                    break;
                case SYNTH:
                    UIManager.setLookAndFeel(Temas.getSynth());
                    break;
                case SYNTHETICA:
                    UIManager.setLookAndFeel(Temas.getSynthetica());
                    break;
                case WINDOWS:
                    UIManager.setLookAndFeel(Temas.getWindows());
                    break;
                case WINDOWSCLASSIC:
                    UIManager.setLookAndFeel(Temas.getWindowsClassic());
                    break;
                case SYSTEM:
                    UIManager.setLookAndFeel(Temas.getSystem());
                    break;
                case AERO:
                    UIManager.setLookAndFeel(Temas.getAero());
                    break;
                case MCWIN:
                    UIManager.setLookAndFeel(Temas.getMcWin());
                    break;
                case SMART:
                    UIManager.setLookAndFeel(Temas.getSmart());
                    break;
                case ACRYL:
                    UIManager.setLookAndFeel(Temas.getAcryl());
                    break;
                case ALUMNINIUM:
                    UIManager.setLookAndFeel(Temas.getAluminium());
                    break;
                case LUNA:
                    UIManager.setLookAndFeel(Temas.getLuna());
                    break;
                case BERNSTEIN:
                    UIManager.setLookAndFeel(Temas.getBernstein());
                    break;
                case FAST:
                    UIManager.setLookAndFeel(Temas.getFast());
                    break;
                case HIFI:
                    UIManager.setLookAndFeel(Temas.getHIFI());
                    break;
                case MINT:
                    UIManager.setLookAndFeel(Temas.getMint());
                    break;
                case NOIRE:
                    UIManager.setLookAndFeel(Temas.getNoire());
                    break;
                case Autumn:
                    UIManager.setLookAndFeel(Temas.getAutumn());
                    break;
                case BusinessBlackSteel:
                    UIManager.setLookAndFeel(Temas.getBusinessBlackSteel());
                    break;
                case BusinessBlueSteel:
                    UIManager.setLookAndFeel(Temas.getBusinessBlueSteel());
                    break;
                case Business:
                    UIManager.setLookAndFeel(Temas.getBusiness());
                    break;
                case ChallengerDeep:
                    UIManager.setLookAndFeel(Temas.getChallengerDeep());
                    break;
                case CremeCoffee:
                    UIManager.setLookAndFeel(Temas.getCremeCoffee());
                    break;
                case Creme:
                    UIManager.setLookAndFeel(Temas.getCreme());
                    break;
                case DustCoffee:
                    UIManager.setLookAndFeel(Temas.getDustCoffee());
                    break;
                case Dust:
                    UIManager.setLookAndFeel(Temas.getDust());
                    break;
                case EmeraldDusk:
                    UIManager.setLookAndFeel(Temas.getEmeraldDusk());
                    break;
                case Gemini:
                    UIManager.setLookAndFeel(Temas.getGemini());
                    break;
                case GraphiteAqua:
                    UIManager.setLookAndFeel(Temas.getGraphiteAqua());
                    break;
                case GraphiteGlass:
                    UIManager.setLookAndFeel(Temas.getGraphiteGlass());
                    break;
                case Magellan:
                    UIManager.setLookAndFeel(Temas.getMagellan());
                    break;
                case Mariner:
                    UIManager.setLookAndFeel(Temas.getMariner());
                    break;
                case MistAqua:
                    UIManager.setLookAndFeel(Temas.getMistAqua());
                    break;
                case MistSilver:
                    UIManager.setLookAndFeel(Temas.getMistSilver());
                    break;
                case Moderate:
                    UIManager.setLookAndFeel(Temas.getModerate());
                    break;
                case NebulaBrickWall:
                    UIManager.setLookAndFeel(Temas.getNebulaBrickWall());
                    break;
                case Nebula:
                    UIManager.setLookAndFeel(Temas.getNebula());
                    break;
                case OfficeBlack2007:
                    UIManager.setLookAndFeel(Temas.getOfficeBlack2007());
                    break;
                case OfficeBlue2007:
                    UIManager.setLookAndFeel(Temas.getOfficeBlue2007());
                    break;
                case OfficeSilver2007:
                    UIManager.setLookAndFeel(Temas.getOfficeSilver2007());
                    break;
                case Raven:
                    UIManager.setLookAndFeel(Temas.getRaven());
                    break;
                case Sahara:
                    UIManager.setLookAndFeel(Temas.getSahara());
                    break;
                case Twilight:
                    UIManager.setLookAndFeel(Temas.getTwilight());
                    break;
                default:
                    UIManager.setLookAndFeel(Temas.getMetal());
                    break;
            }
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "LookAndFeel", JOptionPane.ERROR_MESSAGE);
        }
    }
}
