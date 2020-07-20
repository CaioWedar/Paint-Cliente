public class PedidoSalvamento extends Comunicado
{	
	private static final long serialVersionUID = 7323456804627089171L;
	private Desenho desenho;

	  public PedidoSalvamento(Desenho desenho)
	  {
	    this.desenho = desenho;
	  }
	  
	  public Desenho getDesenho()
	  {
		  return this.desenho;
	  }
}