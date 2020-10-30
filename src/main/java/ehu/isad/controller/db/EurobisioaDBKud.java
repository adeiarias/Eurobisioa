package ehu.isad.controller.db;

import ehu.isad.model.Herrialdea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EurobisioaDBKud {

  // singleton patroia

  private static EurobisioaDBKud instantzia = new EurobisioaDBKud();

  public static EurobisioaDBKud getInstantzia(){
    return instantzia;
  };

  private EurobisioaDBKud(){}

  public ObservableList<Herrialdea> lortuHerrialdeak(){

    ObservableList<Herrialdea> emaitza = FXCollections.observableArrayList();
    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
    String query = "select * from Herrialde";
    ResultSet rs = dbkud.execSQL(query);

    try {
      while (rs.next()) {
        String izena = rs.getString("izena");
        String irudia = rs.getString("bandera");
        String tv = rs.getString("tv");
        emaitza.add(new Herrialdea(izena,irudia,tv));
      }
    }catch (SQLException e){
      System.err.println(e);
    }

    return emaitza;
  }

  public boolean puntuGuztiakEman(String izena){
    String query = "select sum(puntuak) from Bozkaketa where bozkatuDu ='"+izena+"'";
    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
    ResultSet rs = dbkud.execSQL(query);
    int puntuak = 0;
    try {
      while (rs.next()) {
        puntuak = rs.getInt("sum(puntuak)");
      }
    }catch (SQLException e){
      System.err.println(e);
    }
    return puntuak==10;
  }

}