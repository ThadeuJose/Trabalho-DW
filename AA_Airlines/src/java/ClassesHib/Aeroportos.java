package ClassesHib;
// Generated 17/11/2016 16:01:29 by Hibernate Tools 4.3.1



/**
 * Aeroportos generated by hbm2java
 */
public class Aeroportos  implements java.io.Serializable {


     private String idaer;
     private String nmaer;
     private double txemb;
     private String idcid;

    public Aeroportos() {
    }

    public Aeroportos(String idaer, String nmaer, double txemb, String idcid) {
       this.idaer = idaer;
       this.nmaer = nmaer;
       this.txemb = txemb;
       this.idcid = idcid;
    }
   
    public String getIdaer() {
        return this.idaer;
    }
    
    public void setIdaer(String idaer) {
        this.idaer = idaer;
    }
    public String getNmaer() {
        return this.nmaer;
    }
    
    public void setNmaer(String nmaer) {
        this.nmaer = nmaer;
    }
    public double getTxemb() {
        return this.txemb;
    }
    
    public void setTxemb(double txemb) {
        this.txemb = txemb;
    }
    public String getIdcid() {
        return this.idcid;
    }
    
    public void setIdcid(String idcid) {
        this.idcid = idcid;
    }




}

