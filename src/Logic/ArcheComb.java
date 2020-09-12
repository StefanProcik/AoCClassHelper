package Logic;

/**
 * Class that computes the Arches/Classes picked by the User.
 *
 * @author Stefan Procik
 */

import java.io.IOException;

public class ArcheComb {

    /**
     * List of all available Classes in AoC
     */
    public enum MainArches {
        BARD, CLERIC, FIGHTER, MAGE, RANGER, ROGUE, SUMMONER, TANK, NONE, VOID          //VOID = Not picked
    }

    /**
     * Connection to the gui
     */
    private GUIConnector gui;

    /**
     * Array with picked Classes
     */
    private MainArches[] choosenArches;

    /**
     * Constructor with number of maxCombinations and a link to the GUI
     *
     * @param maxCombination Number of max combination number of classes
     * @param gui Link to the GUI
     */
    public ArcheComb(int maxCombination, GUIConnector gui) {
        this.choosenArches = new MainArches[maxCombination];
        this.gui = gui;
        for (int i = 0; i < maxCombination; i++) {
            choosenArches[i] = MainArches.VOID;
        }
    }

    /**
     * Fills choosenArches Array with Archname and changes Archlabel to Archname.
     * If choosenArche-Array is fully filled(both elements are not VOID(not changed)), the GUI is reset.
     *
     * @param pickedArches String, with the name of the picked Arche
     * @throws IOException
     */
    public void chooseArche(String pickedArches) throws IOException {
        if (choosenArches[0] != MainArches.VOID && choosenArches[1] != MainArches.VOID) {
            choosenArches[0] = MainArches.VOID;
            choosenArches[1] = MainArches.VOID;
            this.gui.resetGUI();
        }
        if (choosenArches[0] == MainArches.VOID) {
            if (!pickedArches.toUpperCase().equals(MainArches.NONE.toString())) {    //Test, if first Class isn´t NONE
                choosenArches[0] = MainArches.valueOf(pickedArches.toUpperCase());   //Sets Array-choosenClasses[0] to Arches.(pickedArches)
                this.gui.showLabelPrimaryArche(pickedArches);                    //Sets Label "Primary Archetype" to pickedArches-String
            }
        } else {
            choosenArches[1] = MainArches.valueOf(pickedArches.toUpperCase());       //Sets Array-choosenClasses[0] to Arches.(pickedArches)
            this.gui.showLabelSecondaryArche(pickedArches);                    //Sets Label "Secondary Archetype" to pickedArches-String
            this.gui.twoArchesCommitted(getArchesCoordinates(choosenArches));
        }

    }

    /**
     * Gets the coordinates of the choosenArches in the MainArches-Enum
     *
     * @param arches choosen Arches of User
     * @return Int-Array with coordinates of choosenArches
     */
    public int[] getArchesCoordinates(MainArches[] arches){
        int[] coords = new int[]{-1,-1};
        MainArches[] archesList = MainArches.values();         //Array with all Arches of AoC
        MainArches toFind = MainArches.valueOf(arches[0].toString().toUpperCase()); //
        for (int i = 0; i < archesList.length; i++) {
            if (toFind == archesList[i]) {
                coords[0] = i;
            }
        }
        toFind = MainArches.valueOf(arches[1].toString().toUpperCase());
        for (int i = 0; i < archesList.length; i++) {
            if (toFind == archesList[i]) {
                coords[1] = i;
            }
        }
        if (coords[1] == 8) {
            coords[1] = coords[0];
            coords[0] = 8;
        }
        return coords;
    }
}
