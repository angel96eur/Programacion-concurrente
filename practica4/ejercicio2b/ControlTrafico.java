package practica4.ejercicio2b;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ControlTrafico
{
    int puente=0;
    int cocheizq=0;
    int cocheder=0;
    int contDer=0;  // se utilizará para controlar los coches máximos que pueden pasar del lado derecho
    int contIzq=0;  // se utilizará para controlar los coches máximos que pueden pasar del lado izquierdo
    boolean esperandoDer=false;   // con esto sabremos si hay coches esperando en uno u otro lado
    boolean esperandoIzq=false;

    int n=2;    // esto indica los coches que queremos que pasen por cada lado

    public synchronized void entraIzquierda() {
       
        while (cocheder!=0||contIzq>=n){ // no se pasa mientras haya conches circulando por el puente o los coches no sean azules
            esperandoIzq=true;         // como hay coches en el puente esperando se pone a true
            try {
                wait();
            } catch (InterruptedException ex){}
        }
        puente++;
        cocheizq++;
        contIzq++;
        if (esperandoDer==false)    // si no hay coches en la derecha
            contIzq=0;              // se resetea el contador para que puedan seguir pasando del mismo lado
        contDer=0;                  // cuando se vuelva a establecer el trafico se pondrá el contador del otro lado a 0

    }
    public synchronized void entraDerecha() {
    
        while (cocheizq!=0||contDer>=n){ // no se pasa mientras haya conches circulando por el puente o los coches no sean rojos
            esperandoDer = true; 
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        puente++;
        cocheder++;
        contDer++;
        if(esperandoIzq==false)     // si no hay coches en la izquierdo
            contDer=0;              // se resetea el contador para que puedan seguir pasando del mismo lado
        contIzq=0;                  // cuando se vuelva a establecer el trafico se pondrá el contador del otro lado a 0
    }
    public synchronized void dejaIzquierda() {
    
        puente--;
        cocheder--;
        esperandoIzq=false;            // al salir un coche del puente pone la variable de espera a falsa,
        if(cocheder==0&&puente==0)     // y se quedará asi si no hay otro coche esperando, que la pondrá a verdadera
            notifyAll();
        else
            notify();    
    }
    public synchronized void dejaDerecha() {
    
        puente--;
        cocheizq--;
        esperandoDer = false;           // al salir un coche del puente pone la variable de espera a falsa,
        if(cocheizq==0&&puente==0)     // y se quedará asi si no hay otro coche esperando, que la pondrá a verdadera
            notifyAll();
        else
            notify();
    }
    
}
