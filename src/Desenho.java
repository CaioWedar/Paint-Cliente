import java.util.*;

public class Desenho extends Comunicado
{

	private static final long serialVersionUID = 1297360756346882819L;
	private static Integer codigo;
    private String nome;
    private String dataCriacao;
    private String dataUltimaAtualizacao;
    private ArrayList<String> conteudo = new ArrayList<>();
    

    public Desenho(String nomeDesenho, String dtCria, String dtUltAtu)
    {	
        nome = nomeDesenho;
        dataCriacao = dtCria;
        dataUltimaAtualizacao = dtUltAtu;
    }
    
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof Desenho)) return false;
        Desenho desenho = (Desenho) objeto;
        return 	 nome.equals(desenho.nome) 
        		&& dataCriacao.equals(desenho.dataCriacao) 
        		&& dataUltimaAtualizacao.equals(desenho.dataUltimaAtualizacao) 
        		&& conteudo.equals(desenho.conteudo);
      }

    public int hashCode() 
    {
    	return Objects.hash(nome, dataCriacao, dataUltimaAtualizacao, conteudo);
    }

    public String toString()
    {
	    StringBuilder stringBuilder = new StringBuilder();
	    for (String figura : this.conteudo)
	    {
	    	stringBuilder.append(figura).append("\n");
	    }
	    return stringBuilder.toString();
    }

    public void addFigura (String fig) 
    {
        conteudo.add(fig);
    }

    public int getQtd() {
        return conteudo.size();
    }

    public String getFigura(final int i)
    {
        return conteudo.get(i);
    }
    
    public ArrayList<String> getConteudo()
    {
	    return conteudo;
	}
    
    public Integer getCodigo()
    {
    	return Desenho.codigo;
    }
	
    public String getNome() 
    {
    	return this.nome;
 	}

    public void setNome(String nomeDesenho)
    {
    	this.nome = nomeDesenho;
    }

    public String getDataCriacao() 
    {
    	return dataCriacao;
    }

    public String getDataUltimaAtualizacao() 
    {
    	return dataUltimaAtualizacao;
    }
    
}