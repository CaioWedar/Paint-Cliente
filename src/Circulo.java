import java.awt.*;
import java.util.*;
 
public class Circulo extends Figura
{
    protected Ponto centro;
    protected int raio;
    
    public Circulo (int x, int y, int raio)
    {
        this (x, y, raio, Color.BLACK, Color.BLACK);
    }
	
    public Circulo (int x, int y, int raio, Color cor, Color corFundo)
    {
		this.corFundo = corFundo;
		this.cor = cor;
        this.centro = new Ponto (x,y,cor,corFundo);
        this.raio = raio;
    }

    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
			int   x  = Integer.parseInt(quebrador.nextToken());
			int   y  = Integer.parseInt(quebrador.nextToken());
			
			int raio = Integer.parseInt(quebrador.nextToken());

			Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
								   Integer.parseInt(quebrador.nextToken()),  // G
								   Integer.parseInt(quebrador.nextToken())); // B
								   
			Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
								   Integer.parseInt(quebrador.nextToken()),  // G
								   Integer.parseInt(quebrador.nextToken())); // B
								   
		this.centro  = new Ponto (x,y,cor,corFundo);
        this.raio = raio; 
        this.cor = cor;
        this.corFundo = corFundo;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor(),this.getCorFundo());
    }

    public void setRaio (int raio)
    {
        this.raio = raio;
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio ()
    {
        return this.raio;
    }

    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corFundo);
        g.fillOval(this.centro.getX() - this.raio, this.centro.getY() - raio,
                   2 * this.raio, 2 * this.raio);
		
        g.setColor(this.cor);
        g.drawOval(this.centro.getX() - this.raio, this.centro.getY() - raio,
                   2 * this.raio, 2 * this.raio);
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
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
