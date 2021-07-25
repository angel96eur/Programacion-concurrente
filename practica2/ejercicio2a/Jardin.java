package practica2.ejercicio2a;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Jardin extends Applet
{

    // 3A - Exclusión mútua con Algoritmo de Peterson

    Button goButton;
    Torno torno1;
    Torno torno2;
    Contador contador;
    NumberCanvas contadorD;
    NumberCanvas torno1D;
    NumberCanvas torno2D;

    // Checkbox fixit;
    public final static int MAX=20;

    @Override
    public void init()
    {

        super.init();
	setBackground(Color.lightGray);

        // Set up Button
        Panel p0= new Panel();
        p0.add(goButton = new Button(" Simula "));
        goButton.setFont(new Font("Helvetica",Font.BOLD,24));
        goButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(torno1==null && torno2==null)
                {
                    go();
                }
                else if(!torno1.isAlive() && !torno2.isAlive())
                {
                    go();
                }
            }
        });

	Panel p=new Panel();
        Label lb=new Label("Jardín Ornamental");
        lb.setFont(new Font("Arial",Font.BOLD,24));
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        p.add("South",lb);
        Panel p1= new Panel();
        p1.setLayout(new BorderLayout());
        p1.add("Center",p0);

        // Set up Display
        Panel p2=new Panel();
        contadorD=new NumberCanvas("Contador");
        torno1D=new NumberCanvas("Torno 1",Color.ORANGE);
        torno2D=new NumberCanvas("Torno 2",Color.ORANGE);
        contadorD.setSize(150,100);
        torno1D.setSize(100,100);
        torno2D.setSize(100,100);
        p2.add(torno1D);
        p2.add(contadorD);
        p2.add(torno2D);

        // Arrange Applet display
        setLayout(new BorderLayout());
        add("North",p);
        add("Center",p2);
        add("South",p1);

    }

    private void go()
    {

        contador=new Contador(contadorD);
        torno1=new Torno(torno1D,contador,0);
        torno2=new Torno(torno2D,contador,1);
        torno1.start();
        torno2.start();

    }

}

class Contador
{

    int value=0;
    NumberCanvas display;

    // Variables para Algoritmo de Peterson
    volatile boolean[] flags={false,false};
    volatile int turno=0;

    Contador(NumberCanvas n)
    {
        display=n;
        display.setvalue(value);
    }

    void incrementa(int id)
    {
        // Levanta bandera
        flags[id]=true;

        // Cede el turno al otro hilo
        turno=1-id;
        while(flags[1-id] && turno==(1-id))
        {
            // Aquí vacío
        }

        // Sección crítica
        int temp=value;             //read[v]
        CC.FuerzaCC();
        value=temp+1;               //write[v+1]
        display.setvalue(value);

        // Ya no entro
        flags[id]=false;
    }

}

class Torno extends Thread
{

    NumberCanvas display;
    Contador people;

    // Variables para Algoritmo de Peterson
    int id=0;

    Torno(NumberCanvas n,Contador c,int id)
    {
        display=n;
        people = c;
        this.id=id;
    }

    @Override
    public void run()
    {
        try
        {
            display.setvalue(0);
            for(int i=1;i<=Jardin.MAX;i++)
            {
                Thread.sleep(500);          //0.5 second
                display.setvalue(i);

                // Pasamos id como parámetro, tras nuestra modificación
                people.incrementa(id);
            }
        }
        catch(InterruptedException e)
        {

        }
    }

}

class CC
{

    public static void FuerzaCC()
    {
        if(Math.random()<0.5)
        {
            try
            {
                Thread.sleep(200);
            }
            catch(InterruptedException e)
            {

            };
           // Sirve para simular una concurrencia más explicita
           // y que Java cambie de un hilo a otro al dormirlo
        }
    }

}
