/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Thadeu Jose
 */
public class EscolhaVoo {
    
    public String idVoo,horaPartida,horaChegada,nomeClasse, preco, aeroportoPartida, aeroportoChegada;

    public EscolhaVoo(String idVoo, String horaPartida, String horaChegada, String nomeClasse, String preco, String aeroportoPartida, String aeroportoChegada) {
        this.idVoo = idVoo;
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.nomeClasse = nomeClasse;
        this.preco = preco;
        this.aeroportoPartida = aeroportoPartida;
        this.aeroportoChegada = aeroportoChegada;
    }

    public String info() {
        return idVoo+"/"+aeroportoPartida+"/"+horaPartida+"/"+horaChegada+"/"+aeroportoChegada+"/"+nomeClasse+"/"+preco;
    }
    
    @Override
    public String toString() {
        return aeroportoPartida+" "+horaPartida+"  ->  "+horaChegada+" "+aeroportoChegada+" || R$"+preco;
    }
    
    
}
