package abonnes;

import java.sql.Date;

public class Abonne {
    private static int cpt = 0;
    private int NumeroAb;
    private String NomAb;
    private Date DateDeNaissanceAb;

    public Abonne(String nom, Date datenaiss) {
        NumeroAb = cpt++;
        NomAb = nom;
        DateDeNaissanceAb = datenaiss;
    }

    public int getNum() {
        return NumeroAb;
    }

    public String toString() {
        return NomAb;
    }

    public int getAge() {
        Date d = new Date(System.currentTimeMillis() - DateDeNaissanceAb.getTime());
        return d.toLocalDate().getYear();
    }
}
