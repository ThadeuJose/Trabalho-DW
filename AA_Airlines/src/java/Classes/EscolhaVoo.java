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
    
    private String horaPartida,horaChegada,preco;

    public EscolhaVoo(String horaPartida, String horaChegada, String preco) {
        this.horaPartida = horaPartida;
        this.horaChegada = horaChegada;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return horaPartida+"-"+horaChegada+"|"+preco;
    }
    
    
}
