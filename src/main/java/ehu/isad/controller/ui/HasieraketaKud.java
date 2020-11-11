package ehu.isad.controller.ui;

import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HasieraketaKud {

  private Main main;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button bozkatuId;

  @FXML
  private ImageView eurobisioLogo;

  @FXML
  void onClick(ActionEvent event) {
    main.bozkaketaErakutsi();
  }

  public void setMainApp(Main main) {
    this.main = main;
  }

  @FXML
  void initialize() {
    Image logo = new Image("/images/eurobisioa_logo.png");
    eurobisioLogo.setImage(logo);
  }
}