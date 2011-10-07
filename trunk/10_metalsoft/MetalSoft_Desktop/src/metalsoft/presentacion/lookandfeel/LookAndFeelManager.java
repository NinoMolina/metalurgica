/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.presentacion.lookandfeel;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Nino
 */
public class LookAndFeelManager {

    @SuppressWarnings("empty-statement")
    public static void setLookAndFeel(Skins t) {
        try {
            //seleccion del tema visual
            switch (t) {
                case EASYNTH:
                    UIManager.setLookAndFeel(Skins.getEaSynth());
                    break;
                case IDW:
                    UIManager.setLookAndFeel(Skins.getIdw());
                    break;
                case METAL:
                    UIManager.setLookAndFeel(Skins.getMetal());
                    break;
                case MOTIF:
                    UIManager.setLookAndFeel(Skins.getMotif());
                    break;
                case MULTI:
                    UIManager.setLookAndFeel(Skins.getMulti());
                    break;
                case NIMROD:
                    UIManager.setLookAndFeel(Skins.getNimROD());
                    break;
                case QUAQUA:
                    UIManager.setLookAndFeel(Skins.getQuaqua());
                    break;
                case SYNTH:
                    UIManager.setLookAndFeel(Skins.getSynth());
                    break;
                case SYNTHETICA:
                    UIManager.setLookAndFeel(Skins.getSynthetica());
                    break;
                case WINDOWS:
                    UIManager.setLookAndFeel(Skins.getWindows());
                    break;
                case WINDOWSCLASSIC:
                    UIManager.setLookAndFeel(Skins.getWindowsClassic());
                    break;
                case SYSTEM:
                    UIManager.setLookAndFeel(Skins.getSystem());
                    break;
                case PGS:
                    UIManager.setLookAndFeel(Skins.getPgs());
                    break;
                case AERO:
                    UIManager.setLookAndFeel(Skins.getAero());
                    break;
                case MCWIN:
                    UIManager.setLookAndFeel(Skins.getMcWin());
                    break;
                case SMART:
                    UIManager.setLookAndFeel(Skins.getSmart());
                    break;
                case ACRYL:
                    UIManager.setLookAndFeel(Skins.getAcryl());
                    break;
                case ALUMNINIUM:
                    UIManager.setLookAndFeel(Skins.getAluminium());
                    break;
                case LUNA:
                    UIManager.setLookAndFeel(Skins.getLuna());
                    break;
                case BERNSTEIN:
                    UIManager.setLookAndFeel(Skins.getBernstein());
                    break;
                case FAST:
                    UIManager.setLookAndFeel(Skins.getFast());
                    break;
                case HIFI:
                    UIManager.setLookAndFeel(Skins.getHIFI());
                    break;
                case MINT:
                    UIManager.setLookAndFeel(Skins.getMint());
                    break;
                case NOIRE:
                    UIManager.setLookAndFeel(Skins.getNoire());
                    break;
                case Autumn:
                    UIManager.setLookAndFeel(Skins.getAutumn());
                    break;
                case BusinessBlackSteel:
                    UIManager.setLookAndFeel(Skins.getBusinessBlackSteel());
                    break;
                case BusinessBlueSteel:
                    UIManager.setLookAndFeel(Skins.getBusinessBlueSteel());
                    break;
                case Business:
                    UIManager.setLookAndFeel(Skins.getBusiness());
                    break;
                case ChallengerDeep:
                    UIManager.setLookAndFeel(Skins.getChallengerDeep());
                    break;
                case CremeCoffee:
                    UIManager.setLookAndFeel(Skins.getCremeCoffee());
                    break;
                case Creme:
                    UIManager.setLookAndFeel(Skins.getCreme());
                    break;
                case DustCoffee:
                    UIManager.setLookAndFeel(Skins.getDustCoffee());
                    break;
                case Dust:
                    UIManager.setLookAndFeel(Skins.getDust());
                    break;
                case EmeraldDusk:
                    UIManager.setLookAndFeel(Skins.getEmeraldDusk());
                    break;
                case Gemini:
                    UIManager.setLookAndFeel(Skins.getGemini());
                    break;
                case GraphiteAqua:
                    UIManager.setLookAndFeel(Skins.getGraphiteAqua());
                    break;
                case GraphiteGlass:
                    UIManager.setLookAndFeel(Skins.getGraphiteGlass());
                    break;
                case Magellan:
                    UIManager.setLookAndFeel(Skins.getMagellan());
                    break;
                case Mariner:
                    UIManager.setLookAndFeel(Skins.getMariner());
                    break;
                case MistAqua:
                    UIManager.setLookAndFeel(Skins.getMistAqua());
                    break;
                case MistSilver:
                    UIManager.setLookAndFeel(Skins.getMistSilver());
                    break;
                case Moderate:
                    UIManager.setLookAndFeel(Skins.getModerate());
                    break;
                case NebulaBrickWall:
                    UIManager.setLookAndFeel(Skins.getNebulaBrickWall());
                    break;
                case Nebula:
                    UIManager.setLookAndFeel(Skins.getNebula());
                    break;
                case OfficeBlack2007:
                    UIManager.setLookAndFeel(Skins.getOfficeBlack2007());
                    break;
                case OfficeBlue2007:
                    UIManager.setLookAndFeel(Skins.getOfficeBlue2007());
                    break;
                case OfficeSilver2007:
                    UIManager.setLookAndFeel(Skins.getOfficeSilver2007());
                    break;
                case Raven:
                    UIManager.setLookAndFeel(Skins.getRaven());
                    break;
                case Sahara:
                    UIManager.setLookAndFeel(Skins.getSahara());
                    break;
                case Twilight:
                    UIManager.setLookAndFeel(Skins.getTwilight());
                    break;
                default:
                    UIManager.setLookAndFeel(Skins.getSystem());
                    break;
            }
        } catch (UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "LookAndFeel", JOptionPane.ERROR_MESSAGE);
        }
    }
}
