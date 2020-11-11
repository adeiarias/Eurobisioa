package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaDBKud;
import ehu.isad.model.Herrialdea;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class taulaErakutsi {

  private Main main;
  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private ImageView irudiBalioa;


  @FXML
  private Button okBotoia;

  @FXML
  private ComboBox<Herrialdea> comboBoxa;

  @FXML
  void onClick(ActionEvent event) {
    //lehenik eta behin ikusiko da ea herrialde horrek puntu guztiak eman dituen ala ez
    Herrialdea herrialdea = (Herrialdea)comboBoxa.getValue();
    if(EurobisioaDBKud.getInstantzia().puntuGuztiakEman(herrialdea.getIzena())){//10 puntuak eman dira
      main.herriInfoIkusi(herrialdea.getIzena());
    }else{
      main.bozkatu(herrialdea.getIzena());
    }
  }

  public void setComBoxBalioak(){
    ObservableList<Herrialdea> lista = EurobisioaDBKud.getInstantzia().lortuHerrialdeak();
    comboBoxa.setItems(lista);
    comboBoxa.setEditable(false);
  }

  public void setMainApp(Main main) {
    this.main = main;
  }

  @FXML
  void initialize() {
    Image logoa = new Image("/images/eurobisioa_logo.png");
    irudiBalioa.setImage(logoa);
  }
}
