package ehu.isad.controller.ui;

import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HerriInfoKud {

        private Main main;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Label lblInfo;

        @FXML
        private ImageView logoIrudia;

        @FXML
        private Label lblErdikoa;

        @FXML
        private Button botoia;

        @FXML
        void onClick(ActionEvent event) {
                main.bozkaketaErakutsi();
        }

        public void labelakEguneratu(String izena){
                lblInfo.setText(izena+"ren inguruko informazioa");
                lblErdikoa.setText(izena+"k jada banatu ditu bere puntuak");
        }

        public void setMainApp(Main main) {
                this.main = main;
        }

        @FXML
        void initialize() {
                Image logoa = new Image("/eurobisioa_logo.png");
                logoIrudia.setImage(logoa);
        }
}
