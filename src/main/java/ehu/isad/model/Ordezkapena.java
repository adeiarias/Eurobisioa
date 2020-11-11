package ehu.isad.model;

public class Ordezkapena {

    private String artista;
    private String abestia;
    private String herrialdea;
    private int puntuak;

    public Ordezkapena(String art, String abes, String herr, int p){
        artista = art;
        abestia = abes;
        herrialdea = herr;
        puntuak = p;
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
}
