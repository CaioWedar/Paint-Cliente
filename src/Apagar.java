

import java.awt.*;
import java.util.*;
 
public class Apagar extends Figura
{
    protected Ponto ponto;
    protected int lado;
    
    public Apagar (int x, int y)
    {
        this (x, y, Color.BLACK, Color.BLACK);
    }
	
    public Apagar (int x, int y, Color cor, Color corFundo)
    {
		this.corFundo = corFundo;
		this.cor = cor;
        this.ponto = new Ponto (x,y,cor,corFundo);
    }

    public Apagar (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
			int   x  = Integer.parseInt(quebrador.nextToken());
			int   y  = Integer.parseInt(quebrador.nextToken());

			Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
								   Integer.parseInt(quebrador.nextToken()),  // G
								   Integer.parseInt(quebrador.nextToken())); // B
								   
			Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
										Integer.parseInt(quebrador.nextToken()),  // G
										Integer.parseInt(quebrador.nextToken())); // B
								   
		this.ponto  = new Ponto (x,y,cor,corFundo);
        this.cor = cor;
        this.corFundo = corFundo;
    }

    public void setPonto (int x, int y)
    {
        this.ponto = new Ponto (x,y,this.getCor(),this.getCorFundo());
    }
    

    public Ponto getPonto ()
    {
        return this.ponto;
    }
    

    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.cor);
		g.fillOval(this.ponto.getX(), this.ponto.getY(),
                   24, 24);
    }

    public String toString()
    {
        return "a:" +
               this.ponto.getX() +
               ":" +
               this.ponto.getY() +
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
