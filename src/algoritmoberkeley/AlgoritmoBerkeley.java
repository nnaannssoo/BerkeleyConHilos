/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoberkeley;

/**
 *
 * @author NancyB
 */
public class AlgoritmoBerkeley {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ajustador ajustador= new Ajustador();
        Servidor serv= new Servidor(ajustador);
        serv.start();
        Cliente clientes[]= new Cliente[3];
        for(int i=0; i<3;i++){
        clientes[i] = new Cliente(i,ajustador);
        clientes[i].start();
        }
    }
    
}
