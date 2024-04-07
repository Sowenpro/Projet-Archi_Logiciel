package abonnes;

import java.sql.Date;

public class Abonne {
    private int NumeroAb;
    private String NomAb;
    private Date DateDeNaissanceAb;

    public Abonne(Integer NumeroAb, String nom, Date datenaiss) {
        NumeroAb = null;
        NomAb = nom;
        DateDeNaissanceAb = datenaiss;
    }

	public int getNum() {
        return NumeroAb;
    }

    public String getNom() {
        return NomAb;
    }

    public int getAge() {
        Date d = new Date(System.currentTimeMillis() - DateDeNaissanceAb.getTime());
        return d.toLocalDate().getYear();
    }
}
