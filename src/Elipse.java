

import java.awt.*;
import java.util.*;
 
public class Elipse extends Figura
{
    protected Ponto centro;
    protected int raioA;
    protected int raioB;
    
    public Elipse (int x, int y, int raioA, int raioB)
    {
        this (x, y, raioA, raioB, Color.BLACK, Color.BLACK);
        
    }
	
    public Elipse (int x, int y, int raioA, int raioB, Color cor, Color corFundo)
    {
        this.corFundo = corFundo;
		this.cor = cor;
        this.centro = new Ponto (x,y,cor,corFundo);
        this.raioA = raioA;
        this.raioB = raioB;
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
			int   x  = Integer.parseInt(quebrador.nextToken());
			int   y  = Integer.parseInt(quebrador.nextToken());
			
			int raioA = Integer.parseInt(quebrador.nextToken());
			int raioB = Integer.parseInt(quebrador.nextToken());

			Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
								   Integer.parseInt(quebrador.nextToken()),  // G
								   Integer.parseInt(quebrador.nextToken())); // B
								   
			Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
										Integer.parseInt(quebrador.nextToken()),  // G
										Integer.parseInt(quebrador.nextToken())); // B
		this.centro  = new Ponto (x,y,cor,corFundo);
        this.raioA = raioA;
        this.raioB = raioB;
        this.cor = cor;
        this.corFundo = corFundo;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor(),this.getCorFundo());
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaioA() {
        return raioA;
    }

    public void setRaioA(int raioA) {
        this.raioA = raioA;
    }

    public int getRaioB() {
        return raioB;
    }

    public void setRaioB(int raioB) {
        this.raioB = raioB;
    }
    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corFundo);
        g.fillOval( this.centro.getX() - this.raioA, this.centro.getY() - this.raioB,
                    2* this.raioA, 2* this.raioB);
		
        g.setColor(this.cor);
        g.drawOval( this.centro.getX() - this.raioA, this.centro.getY() - this.raioB,
                    2* this.raioA, 2* this.raioB);
    }

    public String toString()
    {
        return "e:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raioA +
               ":" +
               this.raioB +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue() +
               ":" +
               this.getCorFundo().getRed() +
               ":" +
               this.getCorFundo().getGreen() +
               ":" +
               this.getCorFundo().getBlue();
    }
}
