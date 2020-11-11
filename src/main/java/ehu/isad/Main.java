package ehu.isad;

import ehu.isad.controller.ui.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent hasieraUI;
  private Parent bozkaketaUI;
  private Parent herriInfoUI;
  private Parent bozkatuUI;
  private Parent topUI;

  private Stage stage;

  private HasieraketaKud hasieraketaKud;
  private BozkaketaAukeratuKud bozkaketaKud;
  private HerriInfoKud herrinfokud;
  private BozkaketaKaukotuKud bozkatuKud;
  private Top3Kud topKud;

  private Scene hasieraketa,bozkaketa,herriInfo,bozkatu,top;


  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("EUROBISIOA");
    stage.setScene(hasieraketa);
    stage.show();
  }

  private void pantailakKargatu() throws IOException {

    FXMLLoader loaderHasieraketa = new FXMLLoader(getClass().getResource("/Hasieraketa.fxml"));
    hasieraUI = (Parent) loaderHasieraketa.load();
    hasieraketaKud = loaderHasieraketa.getController();
    hasieraketaKud.setMainApp(this);
    hasieraketa = new Scene(hasieraUI);

    FXMLLoader loaderBozkaketa = new FXMLLoader(getClass().getResource("/BozkaketaAukeratu.fxml"));
    bozkaketaUI = (Parent) loaderBozkaketa.load();
    bozkaketaKud = loaderBozkaketa.getController();
    bozkaketaKud.setMainApp(this);
    bozkaketa = new Scene(bozkaketaUI);

    FXMLLoader loaderHerriInfo = new FXMLLoader(getClass().getResource("/HerrialdearenInformazioa.fxml"));
    herriInfoUI = (Parent) loaderHerriInfo.load();
    herrinfokud = loaderHerriInfo.getController();
    herrinfokud.setMainApp(this);
    herriInfo = new Scene(herriInfoUI);

    FXMLLoader loaderBozkatuInfo = new FXMLLoader(getClass().getResource("/BozkaketaKaukotu.fxml"));
    bozkatuUI = (Parent) loaderBozkatuInfo.load();
    bozkatuKud = loaderBozkatuInfo.getController();
    bozkatuKud.setMainApp(this);
    bozkatu = new Scene(bozkatuUI);

    FXMLLoader loaderTop = new FXMLLoader(getClass().getResource("/top3.fxml"));
    topUI = (Parent) loaderTop.load();
    topKud = loaderTop.getController();
    topKud.setMainApp(this);
    top = new Scene(topUI);

  }


  public static void main(String[] args) {
    launch(args);
  }

  public void bozkaketaErakutsi() {
    bozkaketaKud.setComBoxBalioak();
    stage.setScene(bozkaketa);
    stage.show();
  }

  public void herriInfoIkusi(String izena){
    herrinfokud.labelakEtaIrudiEguneratu(izena);
    stage.setScene(herriInfo);
    stage.show();
  }

  public void bozkatu(String izena){
    bozkatuKud.herriIrudiaJarri(izena);
    stage.setScene(bozkatu);
    stage.show();
    bozkatuKud.ordezkaritzaKargatu(izena);

  }

  public void top3Ikusi(){
    stage.setScene(top);
    stage.show();
  }


}