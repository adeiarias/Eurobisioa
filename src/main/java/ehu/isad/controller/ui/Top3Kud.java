package ehu.isad.controller.ui;

import java.net.URL;
import java.util.ResourceBundle;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaDBKud;
import ehu.isad.model.Top3;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Top3Kud {

    Main main;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView idEuro;

    @FXML
    private Button btn;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    public void setMainApp(Main main) {
        this.main = main;
    }

    @FXML
    void onClick(ActionEvent event) {
        System.exit(0);
    }

    private void labelakEguneratu(Top3 lista[]){
        Top3 t;
        for(int i = 0; i < lista.length; i++){
            t = lista[i];
            if(i==0){
                lbl1.setText(t.getIzena()+" - "+t.getPuntuak()+" puntu");
            }else if(i==1){
                lbl2.setText(t.getIzena()+" - "+t.getPuntuak()+" puntu");
            }else{
                lbl3.setText(t.getIzena()+" - "+t.getPuntuak()+" puntu");
            }
        }
    }
    @FXML
    void initialize() {
        idEuro.setImage(new Image("/images/eurobisioa_logo.png"));
        labelakEguneratu(EurobisioaDBKud.getInstantzia().lortuPodium());
    }
}