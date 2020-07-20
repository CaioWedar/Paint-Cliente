

import java.awt.*;
import java.util.*;
 
public class Poligono extends Figura
{
    protected int[] verticeX, verticeY;
    protected int lados, i;
    protected Color cor;
    
    public Poligono (int[] verticeX, int[] verticeY, int lados)
    {
        this (verticeX, verticeY, lados, Color.BLACK, Color.BLACK);
    }
	
    public Poligono (int[] verticeX, int[] verticeY, int lados, Color cor, Color corFundo)
    {
        this.corFundo = corFundo;
		this.cor = cor;
        this.cor = cor;
        this.verticeX = verticeX;
        this.verticeY = verticeY;
        this.lados = lados;
    }

    public Poligono (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();
        for(i = 0; i < lados; i++)
        {
	        verticeX[i]  = Integer.parseInt(quebrador.nextToken());
	        verticeY[i]  = Integer.parseInt(quebrador.nextToken());
        }
        int   lados = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B
                               
        Color corFundo = new Color (Integer.parseInt(quebrador.nextToken()),  // R
									Integer.parseInt(quebrador.nextToken()),  // G
									Integer.parseInt(quebrador.nextToken())); // B

        this.verticeX  	= verticeX;
        this.verticeY 	= verticeY;
        this.lados 		= lados;
        this.cor 		= cor;
        this.corFundo 	= corFundo;
    }

    public void setPontox (int x)
    {
        this.verticeX = new int[] {x};
    }
    
    public void setPontoy (int y)
    {
        this.verticeY = new int[] {y};
    }
    
    public void setLados (int lados)
    {
    	this.lados = lados;
    }

    public int[] getPontox ()
    {
        return this.verticeX;
    }

    public int[] getPontoy ()
    {
        return this.verticeY;
    }
    
    public int getLados()
    {
    	return this.lados;
    }

    public void torneSeVisivel (Graphics g)
    {
		g.setColor(this.corFundo);
		g.fillPolygon(verticeX, verticeY, lados);
		
        g.setColor(this.cor);
        g.drawPolygon(verticeX, verticeY, lados);
    }

    public String toString()
    {
    	StringBuilder string = new StringBuilder("p" + this.lados + ":");
    	
    	for(i = 0; i < lados; i++)
    	{
    		string.append(this.verticeX[i]).append(":").append(this.verticeY[i]).append(":");
        }
        string.append(this.getCor().getRed()).append(":").append(this.getCor().getGreen()).append(":").
          append(this.getCor().getBlue()).append(":").append(this.getCor().getAlpha()).append(":").
          append(this.getCorFundo().getRed()).append(":").append(this.getCorFundo().getGreen()).
          append(":").append(this.getCorFundo().getBlue()).append(":").append(this.getCorFundo().getAlpha());
        return string.toString();
    }
}
