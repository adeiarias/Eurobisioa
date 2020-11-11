package ehu.isad.controller.db;

import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkapena;
import ehu.isad.model.Top3;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    String query = "select * from Herrialde"; //select * from Herrialde where urtea=strftime('%Y','now');
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

  public List<Ordezkapena> ordezkapenakLortu(String herri){
    List<Ordezkapena> emaitza = new ArrayList<Ordezkapena>();
    String query = "select artista,herrialdea,abestia,bandera,puntuak from Herrialde,Ordezkaritza where herrialdea = izena"; //and urtea=(SELECT strftime('%Y','now')) order by izena asc";
    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
    ResultSet rs = dbkud.execSQL(query);
    try {
      while (rs.next()) {
        String abestia = rs.getString("abestia");
        String herrialdea = rs.getString("herrialdea");
        String artista = rs.getString("artista");
        String bandera = rs.getString("bandera");
        int puntuak = rs.getInt("puntuak");
        Image im = new Image("/images/"+bandera+".png");
        emaitza.add(new Ordezkapena(artista,abestia,herrialdea,puntuak,im));
      }
    }catch (SQLException e){
      System.err.println(e);
    }
    return emaitza;
  }

  public Top3[] lortuPodium(){
    Top3 array[] = new Top3[3];
    String query = "select puntuak,herrialdea from Ordezkaritza order by puntuak desc limit 3;";
    DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
    ResultSet rs = dbkud.execSQL(query);
    int i = 0;
    try {
      while (rs.next()) {
        String herrialdea = rs.getString("herrialdea");
        int puntuak= rs.getInt("puntuak");
        array[i] = new Top3(herrialdea,puntuak);
        i++;
      }
    }catch (SQLException e){
      System.err.println(e);
    }
    return array;
  }

  public void bozkatu(String nork,String nori,int p){
    String query = "insert into Bozkaketa values('"+nori+"','"+nork+"',select(strftime('%Y','now')),"+p+")";
    DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
    dbKudeatzaile.execSQL(query);
  }

  public void ordezPuntuak(int p,String herri){
      String query = "update ordezkaritza set puntuak=puntuak+"+p+" where " +
              "herrialdea='"+herri+"'";

      DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
      dbKudeatzaile.execSQL(query);
  }


}