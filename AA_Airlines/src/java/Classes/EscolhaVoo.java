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
    
    public String horaPartida,horaChegada,nomeClasse, preco, aeroportoPartida, aeroportoChegada;

    public EscolhaVoo(String horaPartida, String horaChegada, String nomeClasse, String preco, String aeroportoPartida, String aeroportoChegada) {
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.nomeClasse = nomeClasse;
        this.preco = preco;
        this.aeroportoPartida = aeroportoPartida;
        this.aeroportoChegada = aeroportoChegada;
    }

    @Override
    public String toString() {
        return aeroportoPartida+" "+horaPartida+"  ->  "+horaChegada+" "+aeroportoChegada+" ||| "+nomeClasse+" R$"+preco;
    }
    
    
}
