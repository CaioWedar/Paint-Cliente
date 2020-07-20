

import java.awt.*;
import java.util.*;
 
public class Quadrado extends Figura
{
    protected Ponto ponto;
    protected int lado;
    
    public Quadrado (int x, int y, int lado)
    {
        this (x, y, lado, Color.BLACK, Color.BLACK);
    }
	
    public Quadrado (int x, int y, int lado, Color cor, Color corFundo)
    {
		this.corFundo = corFundo;
		this.cor = cor;
        this.ponto = new Ponto (x,y,cor,corFundo);
        this.lado = lado;
    }

    public Quadrado (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
			int   x  = Integer.parseInt(quebrador.nextToken());
			int   y  = Integer.parseInt(quebrador.nextToken());
			
			int lado = Integer.parseInt(quebrador.nextToken());

			Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
								   Integer.parseInt(quebrador.nextToken()),  // G
								   Integer.parseInt(quebrador.nextToken())); // B
								   
			Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
										Integer.parseInt(quebrador.nextToken()),  // G
										Integer.parseInt(quebrador.nextToken())); // B
								   
		this.ponto  = new Ponto (x,y,cor,corFundo);
        this.lado = lado;
        this.cor = cor;
        this.corFundo = corFundo;
    }

    public void setPonto (int x, int y)
    {
        this.ponto = new Ponto (x,y,this.getCor(),this.getCorFundo());
    }

    public void setLado (int altura)
    {
        this.lado = altura;
    }

    public Ponto getPonto ()
    {
        return this.ponto;
    }

    public int getLado ()
    {
        return this.lado;
    }

    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corFundo);
		g.fillRect(this.ponto.getX(), this.ponto.getY(),
                   this.lado, this.lado);
                   
        g.setColor(this.cor);
        g.drawRect(this.ponto.getX(), this.ponto.getY(),
                   this.lado, this.lado);
    }

    public String toString()
    {
        return "q:" +
               this.ponto.getX() +
               ":" +
               this.ponto.getY() +
               ":" +
               this.lado +
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
