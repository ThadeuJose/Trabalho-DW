package Classes;
// Generated 17/11/2016 16:01:29 by Hibernate Tools 4.3.1



/**
 * Reservas generated by hbm2java
 */
public class Reservas  implements java.io.Serializable {


     private String idres;
     private String cdchk;
     private String idusu;

    public Reservas() {
    }

    public Reservas(String idres, String cdchk, String idusu) {
       this.idres = idres;
       this.cdchk = cdchk;
       this.idusu = idusu;
    }
   
    public String getIdres() {
        return this.idres;
    }
    
    public void setIdres(String idres) {
        this.idres = idres;
    }
    public String getCdchk() {
        return this.cdchk;
    }
    
    public void setCdchk(String cdchk) {
        this.cdchk = cdchk;
    }
    public String getIdusu() {
        return this.idusu;
    }
    
    public void setIdusu(String idusu) {
        this.idusu = idusu;
    }




}


