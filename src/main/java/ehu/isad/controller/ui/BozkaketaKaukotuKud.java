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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;

public class BozkaketaKaukotuKud {
    int p;
    String nork;
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

    public void setNork(String h){
        nork = h;
        p = 0; //hasieran herrialde bati emandako puntuak 0 dira, gero eguenratuko dira
    }

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
        System.out.println(l.get(4));
        taula.setItems(l);

    }

    @FXML
    void onClick(ActionEvent event) {
        if(p==5){
            main.top3Ikusi();
        }else{
            System.out.println("Ez dituzu oraindik 5 puntuak eman");
        }

    }

    @FXML
    void initialize() {
        eurobisioArg.setImage(new Image("/images/eurobisioa_logo.png"));

        taula.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        herrialdeKol.setCellValueFactory(new PropertyValueFactory<>("Herrialdea"));
        artistaKol.setCellValueFactory(new PropertyValueFactory<>("Artista"));
        abestiKol.setCellValueFactory(new PropertyValueFactory<>("Abestia"));
       // puntuKol.setCellValueFactory(new PropertyValueFactory<>("Puntuak"));



       puntuKol.setOnEditCommit(
                t -> {
                    t.getTableView().getItems().get(t.getTablePosition().getRow())
                            .setPuntuak(t.getNewValue());
                    p += t.getNewValue();
                    if(p<=5){
                        String nori = t.getTableView().getItems().get(t.getTablePosition().getRow()).getHerrialdea();
                        EurobisioaDBKud.getInstantzia().ordezPuntuak(t.getNewValue(),nori);
                        EurobisioaDBKud.getInstantzia().bozkatu(nork,nori,t.getNewValue());
                    }
                }
        );

        //argazkiKol.setCellValueFactory(new PropertyValueFactory<>("banderaArg"));

        argazkiKol.setCellFactory(p -> new TableCell<>() {
            public void updateItem(Image image, boolean empty) {
                if (image != null && !empty){
                    final ImageView imageview = new ImageView();
                    imageview.setFitHeight(25);
                    imageview.setFitWidth(40);
                    imageview.setImage(image);
                    setGraphic(imageview);
                    setAlignment(Pos.CENTER);
                }else{
                    setGraphic(null);
                    setText(null);
                }
            };
        });
    }
}