package practica3.ejercicio1b;

public class Main1 {

    private static void nap(int millisecs) {
        try {
	    Thread.sleep(millisecs);
	} catch (InterruptedException e) {
	    System.err.println(e.getMessage());
	}
    }


    public static void main(String [] args) {

	PanelHw d = new IPanel();

        d.write(5,2,'A');
        d.write(8,11,'B');
        nap(3000);
	d.write(5,2,' ');

    }
}