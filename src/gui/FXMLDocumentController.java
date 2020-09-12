package gui;

/**
 * Controller-Class for the GUI of the AoCHelper. Initiliazes the program.
 *
 * @author Stefan Procik
 */

import Logic.ArcheComb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

    public class FXMLDocumentController implements Initializable {

        @FXML
        private VBox vBoxHyperlinks;
        @FXML
        private Hyperlink hypLink1;
        @FXML
        private Hyperlink hypLink2;
        @FXML
        private Hyperlink hypLink3;
        @FXML
        private Hyperlink hypLink4;
        @FXML
        private Hyperlink hypLink5;
        @FXML
        private Hyperlink hypLink6;
        @FXML
        private Hyperlink hypLink7;
        @FXML
        private Hyperlink hypLink8;

        //-----------------------------

        @FXML
        private Label lblPrimaryArche;
        @FXML
        private Label lblSecondaryArche;
        @FXML
        private Label lblResultArche;

        private ArcheComb archeComb;
        @FXML
        private Hyperlink hypLink9;
        @FXML
        private Hyperlink hypLink10;
        @FXML
        private Hyperlink hypLink11;
        @FXML
        private Hyperlink hypLink12;
        @FXML
        private Hyperlink hypLink13;
        @FXML
        private Hyperlink hypLink14;
        @FXML
        private Hyperlink hypLink15;

        /**
         * Initialisiert das Spiel
         *
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            lblPrimaryArche.setText("");
            lblPrimaryArche.setDisable(true);
            lblSecondaryArche.setText("");
            lblSecondaryArche.setDisable(true);
            lblResultArche.setText("");
            lblResultArche.setDisable(true);
            Hyperlink[] hyperlinks = new Hyperlink[]{hypLink1, hypLink2, hypLink3, hypLink4, hypLink5, hypLink6, hypLink7, hypLink8, hypLink9, hypLink10,hypLink11,hypLink12,hypLink13,hypLink14,hypLink15};
            for (Hyperlink hyper: hyperlinks) {
                hyper.setVisible(false);
                hyper.setDisable(true);
                hyper.setText("");
            }
            this.archeComb = new ArcheComb(2, new GUI(lblPrimaryArche, lblSecondaryArche, lblResultArche, hyperlinks, vBoxHyperlinks));
        }

        /**
         * Gets Name of pressed Button and uses chooseArche-Method to compute and safe the Arch-Name.
         *
         * @param actionEvent Button press
         * @throws IOException
         */
        @FXML
        private void handleBtnArche(ActionEvent actionEvent) throws IOException {
            archeComb.chooseArche(((Button) actionEvent.getSource()).getText());
        }

        /**
         * Gets Name of Hyperlink pressed and uses it to create clickable Link for openWebpage-Method to compute
         *
         * @param actionEvent Hyperlink press
         * @throws URISyntaxException Throws Exception, if created URI isn´t valid
         */
        @FXML
        private void handleHyperLink(ActionEvent actionEvent) throws URISyntaxException {
            String hyperlinkName = ((Hyperlink) actionEvent.getSource()).getAccessibleText();
            String website = "https://ashesofcreation.wiki/" + lblResultArche.getText().replace(" ", "_") + hyperlinkName;
            URI websiteURI = new URI(website);
            openWebpage(websiteURI);
        }

        /**
         * Checks if User has compatible Desktop/Program to open Hyperlink. Opens Hyperlink on Dekstop/Program
         *
         * @param uri Created URI for Hyperlink
         */
        public static void openWebpage(URI uri) {
            Desktop desktop = null;
            if(Desktop.isDesktopSupported())
                desktop = Desktop.getDesktop();
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


