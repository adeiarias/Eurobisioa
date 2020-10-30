package ehu.isad.model;

public class Herrialdea {
    String izena;
    String irudia;
    String tv;

    public Herrialdea(String izena, String irudia, String tv) {
        this.izena = izena;
        this.irudia = irudia;
        this.tv = tv;
    }

    @Override
    public String toString() {
        return izena;
    }

    public String getIzena() {
        return izena;
    }

    public String getIrudia() {
        return irudia;
    }

    public String getTv() {
        return tv;
    }
}
