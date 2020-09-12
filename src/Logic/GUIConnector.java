package Logic;

/**
 * Interface used for changing the User-Interface
 *
 * @author Stefan Procik
 */

import java.io.IOException;

public interface GUIConnector {

    /**
     * Abstract-Method to show Label of the choosen Primary-Arch
     *
     * @param AocArch Choosen primary Arch-String
     */
    void showLabelPrimaryArche(String AocArch);

    /**
     * Abstract-Method to show Label of the choosen Secondary-Arch
     *
     * @param AocArch Choosen secondary Arch-String
     */
    void showLabelSecondaryArche(String AocArch);

    /**
     * Displays combined Archname on the GUI benath the "Arch Name"-Label
     *
     * @param choosenArchesCoords Int-Array with arch coords
     * @throws IOException
     */
    void twoArchesCommitted(int[] choosenArchesCoords) throws IOException;

    /**
     * Downloads the Website-Source-Code and converts it to a Document-File. Converts Document into String.
     * Uses Pattern p to get Hyperlinks-Name and saves it in allLinks-String.
     * Gets Hyperlink-Name of allLinksString and "creates" Hyperlink in GUI and sets Text to Hyperlinkname and makes it
     * visible.
     *
     * @param choosenArch String of combined Archname
     * @throws IOException
     */
    void createHyperLinks(String choosenArch) throws IOException;

    /**
     * Resets the GUI to its original state
     */
    void resetGUI();

}
