import java.net.*;
import java.io.*;


public class Cliente
{
	public static final String HOST_PADRAO  = "localhost";
	public static final int PORTA_PADRAO = 3000;

	protected Parceiro servidor = null;

	public Cliente(Janela janela) throws ConnectException
	{
		this(HOST_PADRAO, PORTA_PADRAO, janela);
	}

	public Cliente(String host, Janela janela) throws ConnectException
	{
		this(host, PORTA_PADRAO, janela);
	}

	public Cliente(String host, int porta, Janela janela) throws ConnectException
	{
		Socket conexao;
		try
		{
			conexao = new Socket(host, porta);
			System.out.println("Conectado ao servidor " + host + ":" + porta);
		}
		catch (Exception erro)
		{
			System.err.println("Indique o servidor e a porta corretos!\n");
			throw new ConnectException();
		}

		ObjectOutputStream transmissor;
		try
		{
			transmissor = new ObjectOutputStream(conexao.getOutputStream());
		}
		catch (Exception erro)
		{
			System.err.println("Indique o servidor e a porta corretos!\n");
			throw new ConnectException();
		}

		ObjectInputStream receptor;
		try
		{
				receptor = new ObjectInputStream(conexao.getInputStream());
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				throw new ConnectException();
		}

		Parceiro servidor;
		try
		{
				servidor = new Parceiro(conexao, receptor, transmissor);
				this.servidor = servidor;
		}
		catch (Exception erro)
		{
				System.err.println("Indique o servidor e a porta corretos!\n");
				throw new ConnectException();
		}

		TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento;
		try
		{
			tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento(servidor, janela);
		}
		catch (Exception ignored)
		{
			throw new ConnectException();
		}

		tratadoraDeComunicadoDeDesligamento.start();
	}

	public void salvar(Desenho desenho)
	{
		try
		{
			servidor.receba (new PedidoSalvamento(desenho));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Desenho carregar(String nome) throws Exception
	{
		try
		{
			servidor.receba(new PedidoDesenho(nome));
			Comunicado comunicado;
			do
			{
				comunicado = servidor.espie();
			}
			while (!(comunicado instanceof Desenho));
			return (Desenho)servidor.envie();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex);
		}
	}
	public void desconectarSe()
	{
		try
		{
			servidor.receba(new PedidoParaSair());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}