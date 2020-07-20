

import java.awt.*;
import java.util.*;
 
public class Texto extends Figura
{
    protected Ponto ponto;
    protected String text;
    
    public Texto (int x, int y, String text)
    {
        this (x, y, text, Color.BLACK, Color.BLACK);
    }
	
    public Texto (int x, int y, String text, Color cor, Color corFundo)
    {
		this.corFundo = corFundo;
		this.cor = cor;
        this.ponto = new Ponto (x,y,cor,corFundo);
        this.text = text;
    }

    public Texto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
			int   x  = Integer.parseInt(quebrador.nextToken());
			int   y  = Integer.parseInt(quebrador.nextToken());

			String text = quebrador.nextToken();

			Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
								   Integer.parseInt(quebrador.nextToken()),  // G
								   Integer.parseInt(quebrador.nextToken())); // B

								   
		this.ponto  = new Ponto (x,y,cor,corFundo);
        this.text = text;
        this.cor = cor;
        this.corFundo = corFundo;
    }

    public void setPonto (int x, int y)
    {
        this.ponto = new Ponto (x,y,this.getCor(),this.getCorFundo());
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public Ponto getPonto ()
    {
        return this.ponto;
    }

    public String getText ()
    {
        return this.text;
    }

    public void torneSeVisivel (Graphics g)
    {
        g.setColor(this.cor);
        g.drawString(this.text, this.ponto.getX(), this.ponto.getY());
    }

    public String toString()
    {
        return "t:" +
               this.ponto.getX() +
               ":" +
               this.ponto.getY() +
               ":" +
               this.text +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }
}
