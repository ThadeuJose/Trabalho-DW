package Classes;
// Generated 17/11/2016 16:01:29 by Hibernate Tools 4.3.1



/**
 * Classesassento generated by hbm2java
 */
public class Classesassento  implements java.io.Serializable {


     private String idcla;
     private String nmcla;
     private double pccla;

    public Classesassento() {
    }

    public Classesassento(String idcla, String nmcla, double pccla) {
       this.idcla = idcla;
       this.nmcla = nmcla;
       this.pccla = pccla;
    }
   
    public String getIdcla() {
        return this.idcla;
    }
    
    public void setIdcla(String idcla) {
        this.idcla = idcla;
    }
    public String getNmcla() {
        return this.nmcla;
    }
    
    public void setNmcla(String nmcla) {
        this.nmcla = nmcla;
    }
    public double getPccla() {
        return this.pccla;
    }
    
    public void setPccla(double pccla) {
        this.pccla = pccla;
    }




}

