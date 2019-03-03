/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoberkeley;

import java.util.Random;

/**
 *
 * @author NancyB
 */
public class Cliente extends Thread {
    private long tiempo;
    private int clienteID;
    private Ajustador ajus;
    public Random r;
    public Cliente(int id, Ajustador sm){
        tiempo = System.nanoTime();
        this.clienteID = id;
        this.ajus= sm;
        this. r= new Random();
        
    }
    public void run(){
        while(true){ 
                this.tiempo += (this.clienteID+1)*2; //Agrega un retrazo aleatorio para simular la diferencia de tiempos
                System.out.println(" Tiempo del cliente " + clienteID + " : " + this.tiempo);
                this.ajus.guardaDiferencia(this.tiempo,this.clienteID); // Manda el tiempo al servidor para que calcule la diferencia y la guarda
                this.tiempo += this.ajus.getTiempoAjustado(this.clienteID); // Obtiene el tiempo ajustado y se actualiza
                System.out.println(" Tiempo Ajustado " + clienteID + ": " + this.tiempo);
        }
    }
}

