import java.awt.*;

public abstract class Figura extends Comunicado
{
    protected Color cor;
    protected Color corFundo;
	  
    protected Figura ()
    {
        this (Color.BLACK, Color.BLACK);
    }
	  
    protected Figura (Color cor, Color corFundo)
    {
        this.cor = cor;
        this.cor = corFundo;
    } 
	  
    public void setCor (Color cor)
    {
        this.cor = cor;
    }
    
    public void setCorFundo (Color corFundo)
    {
        this.corFundo = corFundo;
    }
	  
    public Color getCor()
    {
    	return this.cor;
    }
    
        public Color getCorFundo()
    {
    	return this.corFundo;
    }

  //public abstract boolean equals         (Object obj);
  //public abstract int     hashCode       ();
  //public abstract Object  clone          ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);
}
