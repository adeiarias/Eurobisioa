package ehu.isad.controller.ui;

import java.net.URL;
import java.util.ResourceBundle;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaDBKud;
import ehu.isad.model.Ordezkapena;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class BozkaketaKaukotuKud {

    Main main;
    private ObservableList<Ordezkapena> l;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Ordezkapena> taula;

    @FXML
    private TableColumn<Ordezkapena, Image> argazkiKol;

    @FXML
    private TableColumn<Ordezkapena, String> herrialdeKol;

    @FXML
    private TableColumn<Ordezkapena, String> artistaKol;

    @FXML
    private TableColumn<Ordezkapena, String> abestiKol;

    @FXML
    private TableColumn<Ordezkapena, Integer> puntuKol;

    @FXML
    private Button bozkaketaButton;

    @FXML
    private ImageView eurobisioArg;

    @FXML
    private ImageView HerrialdeArg;

    @FXML
    private Label herriLabel;

    @FXML
    private Label herriLabel1;

    public void setMainApp(Main main) {
        this.main = main;
    }

    public void herriIrudiaJarri(String herrialdea){
        if(herrialdea.equals("Azerbaiyán")){
            herriLabel.setText("Azerbaiyanek horrela nahi");
            herriLabel1.setText("ditu bere puntuak banatu:");
            HerrialdeArg.setImage(new Image("/images/azerbaiyan.png"));
        }else{
            herriLabel.setText(herrialdea.toLowerCase()+"k horrela nahi ditu");
            herriLabel1.setText("bere puntuak banatu:");
            HerrialdeArg.setImage(new Image("/images/"+herrialdea.toLowerCase()+".png"));
        }
    }

    public void ordezkaritzaKargatu(String herrialdea){
        l = FXCollections. observableArrayList(EurobisioaDBKud.getInstantzia().ordezkapenakLortu(herrialdea));
        taula.setItems(l);
    }

    @FXML
    void onClick(ActionEvent event) {
        main.top3Ikusi();
    }

    @FXML
    void initialize() {
        eurobisioArg.setImage(new Image("/images/eurobisioa_logo.png"));
        taula.setEditable(true);
        herrialdeKol.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));
        artistaKol.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        abestiKol.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
        puntuKol.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));
        argazkiKol.setCellValueFactory(new PropertyValueFactory<Ordezkapena, Image>("bandera"));

        puntuKol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        puntuKol.setOnEditCommit(balioa -> {

        });


    }
}