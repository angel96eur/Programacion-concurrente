package practica4.ejercicio1;

public class Main
{
    
    static final int HILOS_A_DORMIR=15;
//    static final boolean DESPIERTA_SOLO_A_UNO=true;
    static final boolean DESPIERTA_SOLO_A_UNO=false;

    /*
     * Si es uno a uno usa FIFO
     * pero cuando lo hace con LIFO
     * Nunca lo hace aleatorio
     */


    public static void main(String[] args) throws InterruptedException
    {
        Semantica s=new Semantica();
        HiloEspera[] h=new HiloEspera[HILOS_A_DORMIR];

        for(int i=0;i<(HILOS_A_DORMIR-1);i++)
        {
            h[i]=new HiloEspera(s,(i+1));
        }
        Thread.sleep(300);
        if(DESPIERTA_SOLO_A_UNO==true)
        {
            HiloDespierta1a1 h1=new HiloDespierta1a1(s,HILOS_A_DORMIR);
        }
        else
        {
            HiloDespiertaTodos hT=new HiloDespiertaTodos(s);
        }
    }
    
}
