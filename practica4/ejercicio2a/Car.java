package practica4.ejercicio2a;
import java.awt.*;

public class Car implements Runnable{

    public static final int REDCAR  = 0;
    public static final int BLUECAR = 1;

    private final static int tunnelY   = 95;
    private final static int tunnelXLeft = 210;
    private final static int tunnelXLeft2 = 290;
    private final static int tunnelXMid = 410;
    private final static int tunnelXRight2 = 530;
    private final static int tunnelXRight = 610;
    private final static int totalWidth = 900;
    private final static int initX[]  = {-80, totalWidth};
    private final static int initY[]  = {135, 55};
    private final static int outLeft = -200;
    private final static int outRight = totalWidth + 200;

    int cartype;
    int xpos, ypos;
    Car inFront;
    Image image;
    ControlTrafico controller;

    public Car(int cartype,Car inFront, Image image, ControlTrafico controller) {
	this.cartype = cartype;
	this.inFront = inFront;
        this.image = image;
	this.controller  = controller;
        if (cartype == REDCAR)
	    xpos = inFront==null ? outRight : Math.min(initX[cartype],inFront.getX()-90);
	else
	    xpos = inFront == null ? outLeft : Math.max(initX[cartype],inFront.getX()+90);
        ypos = initY[cartype];
    }


    public void move() {
	int xposOld =  xpos;
	if (cartype==REDCAR) {
            if (inFront.getX() - xpos > 100) {
                xpos += 4;
                if (xpos >= tunnelXLeft & xposOld < tunnelXLeft) controller.entraRojo();
                else if (xpos > tunnelXLeft && xpos < tunnelXMid) {if (ypos > tunnelY) ypos -= 2;}
                else if (xpos >= tunnelXRight2 && xpos < tunnelXRight) {if (ypos < initY[REDCAR]) ypos += 2;}
		else if (xpos >= tunnelXRight &&  xposOld < tunnelXRight) controller.saleRojo();
            }
	} else {
            if (xpos-inFront.getX() > 100) {
                xpos -= 4;
                if (xpos <= tunnelXRight && xposOld > tunnelXRight) controller.entraAzul();
                else if (xpos < tunnelXRight && xpos > tunnelXMid) {if (ypos < tunnelY) ypos += 2;}
                else if (xpos <= tunnelXLeft2 && xpos > tunnelXLeft) {if(ypos > initY[BLUECAR]) ypos -= 2;}
		else if (xpos <= tunnelXLeft && xposOld > tunnelXLeft) controller.saleAzul();
            }
	}
    }




    public void run() {
	boolean outOfSight = cartype == REDCAR ? xpos > totalWidth : xpos < -80;
        while (!outOfSight) {
            move();
	    outOfSight = cartype == REDCAR ? xpos > totalWidth : xpos < -80;
	    try {
		Thread.sleep(30);
	    } catch (InterruptedException e) {}
	    }
	xpos = cartype == REDCAR ? outRight: outLeft;
    }

    public int getX() {return xpos;}

    public void draw(Graphics g) {
	g.drawImage(image,xpos,ypos,null);
    }
}
