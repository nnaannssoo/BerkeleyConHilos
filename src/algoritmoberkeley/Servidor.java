/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoberkeley;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NancyB
 */
public class Servidor extends Thread{
    private Ajustador ajus;
    private final int tiempoEspera;
    private long tiempo;
    
    public Servidor(Ajustador a)
    {
        this.ajus= a;
        this.tiempoEspera=10000; //10s
        this.tiempo= System.nanoTime();
    }
    public void run()
    {
        while (true)
        {
            try
            {
            Thread.sleep(this.tiempoEspera);
                System.out.println("Tiempo del servidor: "+this.tiempo);
                this.ajus.setTiempoServidor(tiempo);
                this.ajus.calculaPromedio();
                this.tiempo+=this.ajus.getPromedio();
                System.out.println("Tiempo ajustado (Servidor): "+this.tiempo);
                this.ajus.reiniciar();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
