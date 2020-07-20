
public class PedidoDesenho extends Comunicado
{
    protected String nome;
    public PedidoDesenho(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }
}
