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
public class Ajustador {
    private long tiempoServidor;
    private long [] diferencias;
    private long auxPromedio;
    private final int numClientes=3;
    private int clientesActualizados;
    public Ajustador()
    {
   
    this.tiempoServidor= 0;
    this.clientesActualizados=this.numClientes;
    this.diferencias= new long[numClientes];
    this.auxPromedio=0;
    }
    public synchronized void setTiempoServidor(long tiempo)
    {
        this.tiempoServidor= tiempo;
        try
        {
            notifyAll();
            wait();
        }
        catch(InterruptedException e)
        {
           
        }
    }
    
    public synchronized  void guardaDiferencia(long tiempo, int cliente) {
        try{
            if(tiempoServidor==0)         
                wait();
            
            this.diferencias[cliente]= (tiempo- this.tiempoServidor); //Aqui calcula la diferencia entre el tiempo del cliente y el del servidor y lo guarda en el arreglo
            this.auxPromedio+= tiempo; //Suma para posteriormente poder obtener el promedio
            clientesActualizados--;//Como ya atendió a un cliente hace la resta de los que estan por atender
             if(clientesActualizados==0) notify();    // Si ya han operado todos los clientes, se despierta al servidor de la cola de espera
            wait();  
        } catch (InterruptedException ex) {
            
        }
    }
    public synchronized  long getTiempoAjustado(int clienteID)
    {
        return this.diferencias[clienteID];
    }
    public synchronized void calculaPromedio()
    {
        long prom= (this.auxPromedio/(this.numClientes+1)); //Aqui calcula el promedio incluyendo al servidor 
        for(int i=0; i< this.numClientes; i++)
        {
            this.diferencias[i]= ((-this.diferencias[i])+prom);// Aqui hace el ajuste 
            }notifyAll();
    }
    public synchronized long getPromedio()
    {
        return this.auxPromedio/(this.numClientes+1);
    }
    public synchronized void reiniciar()
    {
        this.tiempoServidor=0;
        this.clientesActualizados=this.numClientes;
        this.auxPromedio=0;
    }
}
