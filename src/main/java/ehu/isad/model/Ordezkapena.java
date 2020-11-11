package ehu.isad.model;

import javafx.scene.image.Image;

public class Ordezkapena {

    private String artista;
    private String abestia;
    private String herrialdea;
    private Integer puntuak;
    private Image bandera;

    public Ordezkapena(String art, String abes, String herr, Integer p, Image ban){
        artista = art;
        abestia = abes;
        herrialdea = herr;
        puntuak = p;
        bandera = ban;
    }

    public String getArtista() {
        return artista;
    }

    public String getAbestia() {
        return abestia;
    }

    public String getHerrialdea() {
        return herrialdea;
    }

    public Integer getPuntuaK(){ return puntuak; }

    public void setPuntuak(Integer p) {
        puntuak = p;
    }
}
