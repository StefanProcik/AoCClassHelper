package gui;
/**
 * GUI-Class to control User-Interface and display Hyperlinks with Direct-Link to Website.
 *
 * @author Stefan Procik
 */

import Logic.ArcheComb;
import Logic.GUIConnector;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI implements GUIConnector {

    private Label lblPrimaryArche;
    private Label lblSecondaryArche;
    private Label lblResultArch;
    private Hyperlink[] hyperlinks;
    private VBox vBoxHyperlinks;

    private String[][] aocArches = new String[][]{
            {"Minstrel", "Soul Weaver", "Tellsword", "Magician", "Song Warden", "Trickster", "Songcaller Siren"},                  // 0
            {"Scryer", "High Priest", "Templar", "Oracle", "Protector", "Shadow Disciple", "Shaman", "Apostle"},                    // 1
            {"Bladedancer", "Highsword", "Weapon Master", "Spellsword", "Hunter", "Shadowblade", "Bladecaller", "Dreadnought"},     // 2
            {"Sorcerer", "Acolyte", "Battle Mage", "Archwizard", "Spellhunter", "Shadow Caster", "Warlock", "Spellstone"},          // 3
            {"Bowsinger", "Soulbow", "Strider", "Scion", "Hawkeye", "Scout", "Falconer", "Sentinel"},                              // 4
            {"Charlatan", "Cultist", "Duelist", "Nightspell", "Predator", "Assassin", "Shadow Lord", "Shadow Guardian"},            // 5
            {"Enchanter", "Necromancer", "Wild Blade", "Spellmancer", "Beastmaster", "Shadowmancer", "Conjurer", "Brood Warden"},   // 6
            {"Argent", "Paladin", "Knight", "Spellshield", "Warden", "Nightshield", "Keeper", "Guardian"},                         // 7
            {"Bard", "Cleric", "Fighter", "Mage", "Ranger", "Rogue", "Summoner", "Tank"}                                            // 8
    };


    public GUI(Label lblPrimaryArche, Label lblSecondaryArche, Label lblResultArch, Hyperlink[] hyperlinks, VBox vBoxHyperlinks) {
        this.lblPrimaryArche = lblPrimaryArche;
        this.lblSecondaryArche = lblSecondaryArche;
        this.lblResultArch = lblResultArch;
        this.hyperlinks = hyperlinks;
        this.vBoxHyperlinks = vBoxHyperlinks;
    }

    /**
     * Sets PrimaryArche-Label to enabled and visible. Sets Label-Text to given AoCArch-String.
     *
     * @param AocArch Choosen primary Arch-String
     */
    @Override
    public void showLabelPrimaryArche(String AocArch) {
        this.lblPrimaryArche.setDisable(false);
        this.lblPrimaryArche.setVisible(true);
        this.lblPrimaryArche.setText(AocArch);
    }

    /**
     * Sets SecondaryArche-Label to enabled and visible. Sets Label-Text to given AoCArch-String.
     *
     * @param AocArch Choosen secondary Arch-String
     */
    @Override
    public void showLabelSecondaryArche(String AocArch) {
        this.lblSecondaryArche.setDisable(false);
        this.lblSecondaryArche.setVisible(true);
        this.lblSecondaryArche.setText(AocArch);
    }

    /**
     * Displays combined Archname on the GUI benath the "Arch Name"-Label
     *
     * @param choosenArchesCoords Int-Array with arch coords
     * @throws IOException
     */
    @Override
    public void twoArchesCommitted(int[] choosenArchesCoords) throws IOException {
        this.lblResultArch.setVisible(true);
        this.lblResultArch.setDisable(false);
        this.lblResultArch.setText(aocArches[choosenArchesCoords[0]][choosenArchesCoords[1]]);
        this.vBoxHyperlinks.setVisible(true);
        this.vBoxHyperlinks.setDisable(false);
        this.createHyperLinks(aocArches[choosenArchesCoords[0]][choosenArchesCoords[1]].replace(" ", "_"));
    }

    /**
     * Downloads the Website-Source-Code and converts it to a Document-File. Converts Document into String.
     * Uses Pattern p to get Hyperlinks-Name and saves it in allLinks-String.
     * Gets Hyperlink-Name of allLinksString and "creates" Hyperlink in GUI and sets Text to Hyperlinkname and makes it
     * visible.
     *
     * @param choosenArch String of combined Archname
     * @throws IOException
     */
    @Override
    public void createHyperLinks(String choosenArch) throws IOException {
        Pattern p = Pattern.compile("(#\\p{Upper}\\p{Lower}\\p{Lower}.*?\")");
        Document doc = Jsoup.connect("https://ashesofcreation.wiki/" + choosenArch).get();
        String htmlString = doc.toString();
        Matcher m = p.matcher(htmlString);
        String allLinks = "";
        while (m.find()) {
            int patStart = m.start();
            int patEnd = m.end();
            String supTest = htmlString.substring(patStart, patEnd);
            htmlString = htmlString.substring(patEnd);
            allLinks = allLinks.concat(" " + supTest);
            m = p.matcher(htmlString);
        }
        for (int i = 0; allLinks.contains("#"); i++) {
            int idxOfFirst = allLinks.indexOf("#");
            int idxOfLast = allLinks.indexOf("\"");
            String allSub = allLinks.substring(idxOfFirst, idxOfLast);
            allLinks = allLinks.substring(idxOfLast + 1);
            if (!allSub.startsWith("#Comment")) {
                this.hyperlinks[i].setAccessibleText(allSub);
                this.hyperlinks[i].setText(this.polishHyperName(allSub));
                this.hyperlinks[i].setVisible(true);
                this.hyperlinks[i].setDisable(false);
            }
        }
    }

    /**
     * Resets the GUI to its original state
     */
    @Override
    public void resetGUI() {
        lblPrimaryArche.setText("");
        lblPrimaryArche.setDisable(true);
        lblSecondaryArche.setText("");
        lblSecondaryArche.setDisable(true);
        lblResultArch.setText("");
        lblResultArch.setDisable(true);
        for (Hyperlink hyper : this.hyperlinks) {
            hyper.setVisible(false);
            hyper.setDisable(true);
            hyper.setText("");
        }
    }

    /**
     * Replaces the HTML-Specific Character-Combinations to represent the actual content
     *
     * @param hyperLinkName Name displayed on the HyperLink
     * @return Polished hyperLinkName without HTML-Specific Character-Combinations
     */
    protected String polishHyperName(String hyperLinkName) {
        hyperLinkName = hyperLinkName.replace("_", " ");
        hyperLinkName = hyperLinkName.replace(".2F", " ");
        hyperLinkName = hyperLinkName.replace("#", "- ");
        return hyperLinkName;
    }
}
