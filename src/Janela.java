import java.util.*;
import javax.imageio.*;
import javax.swing.JFileChooser;

import java.awt.*;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto   	= new JButton ("Ponto"),
                      btnLinha   	= new JButton ("Linha"),
                      btnCirculo	= new JButton ("Circulo"),
                      btnElipse  	= new JButton ("Elipse"),
                      btnRetangulo 	= new JButton ("Retangulo"),
                      btnQuadrado   = new JButton ("Quadrado"),
                      btnPoligono   = new JButton ("Poligono"),
                      btnTexto		= new JButton ("Texto"),
                      btnApagar  	= new JButton ("Apagar"),
                      btnCores   	= new JButton ("Contorno"),
                      btnTinta      = new JButton ("Fundo"),
                      btnConectar   = new JButton ("Conectar"),
                      btnDesconectar= new JButton ("Desconectar"),
                      btnAbrirServer= new JButton ("Abrir Online"),
                      btnSalvarServer= new JButton("Salvar Online"),
                      btnAbrir   	= new JButton ("Abrir"),
                      btnSalvar  	= new JButton ("Salvar"),
                      btnSair    	= new JButton ("Sair");
    protected MeuJPanel pnlDesenho = new MeuJPanel ();
    protected JLabel statusBar1 = new JLabel ("Mensagem:"),
                     statusBar2 = new JLabel ("Coordenada:");

    protected boolean esperaPonto, esperaInicioReta, esperaFimReta; 
    protected boolean esperaCentro, esperaRaio, esperaCentroElipse , esperaRaioElipse;
    protected boolean esperaInicioRetangulo, esperaFimRetangulo; 
    protected boolean esperaInicioQuadrado, esperaFimQuadrado;
    protected boolean esperaApagar;
    protected boolean esperaTexto;
    protected boolean esperaInicioPoligono,esperaEncherPoligono, esperaFimPoligono;

    protected Color corAtual = Color.BLACK;
    protected Color corFundo = Color.BLACK;
    protected Color corBackground = UIManager.getColor ( "Panel.background" );
    protected Ponto p1;
    protected Ponto pInicial, pFinal;
    protected int[] pontoX, pontoY;
    protected int i = 0;
    protected int raioInicial;
    protected int lados = 99;
    protected int count = 0;
    protected int pressed = 0;
    protected int contadorDeMovimento = 0;
    
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date data = new Date();
    protected String text;
    protected Cliente cliente = null;
    protected Desenho desenho = new Desenho("", formatter.format(data), formatter.format(data));
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Grafico");

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("resources/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("resources/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("resources/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("resources/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("resources/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("resources/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnApagarImg = ImageIO.read(getClass().getResource("resources/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSairImg = ImageIO.read(getClass().getResource("resources/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("resources/retangulo.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo retangulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("resources/quadrado.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo quadrado.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnPoligonoImg = ImageIO.read(getClass().getResource("resources/poligono.jpg"));
            btnPoligono.setIcon(new ImageIcon(btnPoligonoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo poligono.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnTintaImg = ImageIO.read(getClass().getResource("resources/cores.jpg"));
            btnTinta.setIcon(new ImageIcon(btnTintaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo tinta.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        try
        {
            Image btnTextoImg = ImageIO.read(getClass().getResource("resources/texto.jpg"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo texto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnConectarImg = ImageIO.read(getClass().getResource("resources/conectar.jpg"));
            btnConectar.setIcon(new ImageIcon(btnConectarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo conectar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnDesconectarImg = ImageIO.read(getClass().getResource("resources/desconectar.jpg"));
            btnDesconectar.setIcon(new ImageIcon(btnDesconectarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo desconectar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAbrirServerImg = ImageIO.read(getClass().getResource("resources/open-server.jpg"));
            btnAbrirServer.setIcon(new ImageIcon(btnAbrirServerImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo open-server.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarServerImg = ImageIO.read(getClass().getResource("resources/save-server.jpg"));
            btnSalvarServer.setIcon(new ImageIcon(btnSalvarServerImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo save-server.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }
        
        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo ());
        btnElipse.addActionListener (new DesenhoDeElipse ());
        btnRetangulo.addActionListener (new DesenhoDeRetangulo ());
        btnQuadrado.addActionListener (new DesenhoDeQuadrado ());
        btnCores.addActionListener (new TrocaCor ());
        btnPoligono.addActionListener (new DesenhoDePoligono ());
        btnTinta.addActionListener (new TrocaCorFundo ());
        btnConectar.addActionListener(new Conectar ());
        btnDesconectar.addActionListener(new Desconectar ());
        btnAbrirServer.addActionListener(new AbrirRemotamente ());
        btnSalvarServer.addActionListener(new SalvarBD ());
        btnAbrir.addActionListener (new Abrir ());
        btnSalvar.addActionListener (new Salvar ());
        btnApagar.addActionListener (new ApagarDesenho ());
        btnTexto.addActionListener	(new DesenhoDeTexto ());

    JPanel pnlBotoesFerramentas = new JPanel();
    JPanel pnlBotoesDesenhos = new JPanel();
    JPanel botoes = new JPanel();
    FlowLayout flwBotoes1 = new FlowLayout();
    FlowLayout flwBotoes2 = new FlowLayout();
    GridLayout grdBotoes = new GridLayout(2, 1);
    
    pnlBotoesFerramentas.setLayout(flwBotoes1);
    pnlBotoesDesenhos.setLayout(flwBotoes2);

    pnlBotoesFerramentas.add(btnConectar);
    pnlBotoesFerramentas.add(btnAbrirServer);
    pnlBotoesFerramentas.add(btnSalvarServer);
    pnlBotoesFerramentas.add(btnAbrir);
    pnlBotoesFerramentas.add(btnSalvar);
    pnlBotoesDesenhos.add(btnPonto);
    pnlBotoesDesenhos.add(btnLinha);
    pnlBotoesDesenhos.add(btnPoligono);
    pnlBotoesDesenhos.add(btnCirculo);
    pnlBotoesDesenhos.add(btnElipse);
    pnlBotoesDesenhos.add(btnTexto);
    pnlBotoesDesenhos.add(btnQuadrado);
    pnlBotoesDesenhos.add(btnRetangulo);
    pnlBotoesFerramentas.add(btnCores);
    pnlBotoesFerramentas.add(btnTinta);
    pnlBotoesFerramentas.add(btnApagar);
    pnlBotoesFerramentas.add(btnDesconectar);
    pnlBotoesFerramentas.add(btnSair);
    botoes.setLayout(grdBotoes);
    botoes.add(pnlBotoesFerramentas);
    botoes.add(pnlBotoesDesenhos);

    JPanel pnlStatus = new JPanel();
    GridLayout grdStatus = new GridLayout(1, 2);
    pnlStatus.setLayout(grdStatus);

    pnlStatus.add(statusBar1);
    pnlStatus.add(statusBar2);

    Container cntForm = this.getContentPane();
    cntForm.setLayout(new BorderLayout());
    cntForm.add(botoes, BorderLayout.NORTH);
    cntForm.add(pnlDesenho, BorderLayout.CENTER);
    cntForm.add(pnlStatus, BorderLayout.SOUTH);
        this.addWindowListener (new FechamentoDeJanela());
        this.setSize (1400,700);
        this.setVisible (true);
    }

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {
	/**
		 * 
		 */
		private static final long serialVersionUID = 4441375905908878902L;

	public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
        public void mousePressed (MouseEvent e)
        {
            if (esperaPonto)
            {
				statusBar1.setText("Mensagem: clique o local do ponto desejado");    
                figuras.add (new Ponto (e.getX(), e.getY(), corAtual, corFundo));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = true;
            }
            else
                if (esperaInicioReta)
                {
                    p1 = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
                    esperaInicioReta = false;
                    esperaFimReta = true;
                    statusBar1.setText("Mensagem: clique o ponto final da reta");    
                 }
                    else
						if (esperaCentro)
						{
							p1 = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
							esperaCentro = false;
							esperaRaio = true;
							statusBar1.setText("Mensagem: clique o ponto final do raio do circulo");    
						}
						else
							if (esperaCentroElipse)
							{
								p1 = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
								esperaCentroElipse = false;
								esperaRaioElipse = true;
								statusBar1.setText("Mensagem: clique o ponto final da elipse");    
							}
							else
								if (esperaInicioRetangulo)
								{
									p1 = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
									esperaInicioRetangulo = false;
									esperaFimRetangulo = true;
									statusBar1.setText("Mensagem: clique o ponto final do retangulo");    
								}
								else
									if (esperaInicioQuadrado)
									{
										p1 = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
										esperaInicioQuadrado = false;
										esperaFimQuadrado = true;
										statusBar1.setText("Mensagem: clique o ponto final do quadrado");    
										}
										else
											if(esperaInicioPoligono)
											{	
													pontoX = new int[lados];
													pontoY = new int[lados];
													pInicial = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
													raioInicial =  (int)(Math.round (Math.sqrt (Math.pow (((pInicial.getX()*2.5)), 2) + Math.pow (((pInicial.getY()*2.5)), 2))));
													pontoX[i] = e.getX();
													pontoY[i] = e.getY();
													i ++;
													count++;
													statusBar1.setText("Mensagem: clique para inicar o poligono");																
													esperaInicioPoligono = false;
													esperaFimPoligono = true;																
											}
											else
												if(esperaFimPoligono)
												{	
													pontoX[i] = e.getX();
													pontoY[i] = e.getY();
													pFinal = new Ponto (e.getX(), e.getY(), corAtual, corFundo);
													i ++;
													count++;
													statusBar1.setText("Mensagem: continue clicando para fechar o poligono");
													boolean flag = false;
													
													int dx = (int)(Math.pow(pFinal.getX() - pInicial.getX(), 2));
													int dy = (int)(Math.pow(pFinal.getY() - pInicial.getY(), 2));
													
													if((int)(Math.pow(dx + dy, 2)) <= (Math.pow(raioInicial, 2)))
													{
														flag = true;

													}	
													
													if(flag == true)
													{	
														lados =  count;
														i = 0;
														count = 0;
														figuras.add(new Poligono(pontoX, pontoY, lados, corAtual, corFundo));
														figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
														esperaInicioPoligono = true;
														esperaFimPoligono = false;
														lados = 99;
														statusBar1.setText("Mensagem: clique o ponto inicial do poligono");	
													}
												}
												else
													if (esperaApagar)
													{
														figuras.add ( new Apagar( e.getX(), e.getY(), corBackground, corFundo));
														figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
														esperaApagar = true;
														statusBar1.setText("Mensagem: Arraste para apagar");
													}
			}
        
        public void mouseReleased (MouseEvent e)
        {
			if (esperaFimReta)
			{
				esperaInicioReta = true;
				esperaFimReta = false;
				figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corFundo));
				figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
				statusBar1.setText("Mensagem: clique o ponto inicial da reta");
				contadorDeMovimento = 0;  
			}
			else
				if (esperaRaio)
				{
					int raio = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2) + Math.pow ((e.getY() - p1.getY() ), 2))));
					figuras.add ( new Circulo( p1.getX(), p1.getY(), raio, corAtual, corFundo));
					figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
					esperaCentro = true;
					esperaRaio = false;
					statusBar1.setText("Mensagem: clique o ponto central do circulo");
					contadorDeMovimento = 0;
				}
				else
					if (esperaRaioElipse)
					{
						int raioA = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2))));
						int raioB = (int) (Math.round (Math.sqrt (Math.pow ((e.getY() - p1.getY() ), 2))));
						figuras.add ( new Elipse( p1.getX(), p1.getY(), raioA, raioB, corAtual, corFundo));
						figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
						esperaCentroElipse = true;
						esperaRaioElipse = false;
						statusBar1.setText("Mensagem: clique o pomto central da elipse");
						contadorDeMovimento = 0; 
					}
					else
						if (esperaFimRetangulo)
						{
							int largura = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2))));
							int altura = (int) (Math.round (Math.sqrt (Math.pow ((e.getY() - p1.getY() ), 2))));
							figuras.add ( new Retangulo( p1.getX(), p1.getY(), largura, altura, corAtual, corFundo));
							figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
							esperaInicioRetangulo = true;
							esperaFimRetangulo = false;
							statusBar1.setText("Mensagem: clique o ponto inicial do retangulo");
							contadorDeMovimento = 0;   
						}
						else
							if (esperaFimQuadrado)
							{
								int lado = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2))));
								figuras.add ( new Quadrado( p1.getX(), p1.getY(), lado, corAtual, corFundo));
								figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
								esperaInicioQuadrado = true;
								esperaFimQuadrado = false;
								statusBar1.setText("Mensagem: clique o ponto inicial do quadrado");
								contadorDeMovimento = 0;  
							}
		}
        
        public void mouseClicked (MouseEvent e)
        {
			if (esperaTexto)
			{	
				figuras.add ( new Texto( e.getX(), e.getY(), text, corAtual, corFundo));
				figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
				esperaTexto = true;
				statusBar1.setText("Mensagem: clique o ponto onde deseja adicionar o texto");
			}
		}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {
			if(contadorDeMovimento > 0)
			{
				pnlDesenho.revalidate();
				pnlDesenho.repaint();
				setSize(1400,700);
			}
			if (esperaFimReta)
			{
				Linha l = new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corAtual, corFundo);
				l.torneSeVisivel(pnlDesenho.getGraphics());
				contadorDeMovimento++;
			}
			else
				if (esperaRaio)
				{
					int raio = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2) + Math.pow ((e.getY() - p1.getY() ), 2))));
					Circulo c = new Circulo( p1.getX(), p1.getY(), raio, corAtual, corFundo);
					c.torneSeVisivel(pnlDesenho.getGraphics());
					contadorDeMovimento++;
				}
				else
					if (esperaRaioElipse)
					{
						int raioA = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2))));
						int raioB = (int) (Math.round (Math.sqrt (Math.pow ((e.getY() - p1.getY() ), 2))));
						Elipse el = new Elipse( p1.getX(), p1.getY(), raioA, raioB, corAtual, corFundo);
						el.torneSeVisivel(pnlDesenho.getGraphics());
						contadorDeMovimento++;
					}
					else
						if (esperaFimRetangulo)
						{
							int largura = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2))));
							int altura = (int) (Math.round (Math.sqrt (Math.pow ((e.getY() - p1.getY() ), 2))));
							Retangulo r = new Retangulo( p1.getX(), p1.getY(), largura, altura, corAtual, corFundo);
							r.torneSeVisivel(pnlDesenho.getGraphics());
							contadorDeMovimento++;
						}
						else
							if (esperaFimQuadrado)
							{
								int lado = (int) (Math.round (Math.sqrt (Math.pow ((e.getX() - p1.getX() ), 2))));
								Quadrado q = new Quadrado( p1.getX(), p1.getY(), lado, corAtual, corFundo);
								q.torneSeVisivel(pnlDesenho.getGraphics());
								contadorDeMovimento++;
							}
							else
								if (esperaApagar)
								{
									figuras.add ( new Apagar( e.getX(), e.getY(), corBackground, corFundo));
									figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
									esperaApagar = true;
									statusBar1.setText("Mensagem: Arraste para apagar");
								}
		}

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+", "+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
			esperaPonto				= true;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;


              statusBar1.setText("Mensagem: clique o local do ponto desejado");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= true;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }
        protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= true;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: clique o ponto central do circulo");
        }
    }
    
        protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= true;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: clique o ponto central da elipse");
        }
    }
        
        protected class DesenhoDeRetangulo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= true;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: clique o ponto inicial do retangulo");
        }
    }
        
        protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= true;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: clique o ponto inicial do quadrado");
        }
    }
    
         protected class DesenhoDePoligono implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= false;
			esperaInicioPoligono	= true;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: clique o ponto inicial do poligono");
        }
    }
    
         protected class ApagarDesenho implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= true;
			esperaTexto				= false;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

            statusBar1.setText("Mensagem: Arraste para apagar");
        }
    }
    
         protected class DesenhoDeTexto implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
			esperaPonto				= false;
			esperaInicioReta		= false;
			esperaFimReta			= false; 
			esperaCentro			= false;
			esperaRaio				= false;
			esperaCentroElipse		= false;
			esperaRaioElipse		= false;
			esperaInicioRetangulo	= false;
			esperaFimRetangulo		= false; 
			esperaInicioQuadrado	= false;
			esperaFimQuadrado		= false;
			esperaApagar			= false;
			esperaTexto				= true;
			esperaInicioPoligono	= false;
			esperaEncherPoligono	= false;
			esperaFimPoligono		= false;

			text = JOptionPane.showInputDialog( " Digite o texto " );
			
            statusBar1.setText("Mensagem: clique o ponto onde deseja adicionar o texto");
        }
    }
    
        protected class TrocaCor implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
		corAtual = JColorChooser.showDialog(null, "Escolha a cor", Color.BLACK);
	}
    }
    
        protected class TrocaCorFundo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
		corFundo = JColorChooser.showDialog(null, "Escolha a cor", Color.BLACK);
		}
    }
    
  protected class Abrir implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filtro = new FileNameExtensionFilter("Arquivos JVP", "jvp");
      fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
      fileChooser.setFileFilter(filtro);
      int result = fileChooser.showOpenDialog(Janela.this);
      if (result == JFileChooser.APPROVE_OPTION)
      {
        File selectedFile = fileChooser.getSelectedFile();
        try 
        {
          Scanner leitor = new Scanner(selectedFile);
          figuras.clear();
          pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
          while (leitor.hasNextLine())
          {
            String figura = leitor.nextLine();
            StringTokenizer quebrador = new StringTokenizer(figura,":");
            switch (quebrador.nextToken())
            {
              case "l":
                figuras.add(new Linha(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "e":
                figuras.add(new Elipse(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "d":
                figuras.add(new Ponto(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "c":
                figuras.add(new Circulo(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
			  case "t":
                figuras.add(new Texto(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "p":
                figuras.add(new Poligono(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "q":
                figuras.add(new Quadrado(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "r":
                figuras.add(new Retangulo(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
              case "a":
                figuras.add(new Apagar(figura));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                break;
            }
          }
          leitor.close();
        }
        catch (FileNotFoundException ex)
        {
          JOptionPane.showMessageDialog(Janela.this, "Arquivo não identificado");
          ex.printStackTrace();
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
      }
    }
  }
	
  protected class Salvar implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogTitle("Salvar");

      int returnVal = fileChooser.showSaveDialog(Janela.this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
        File arquivo = fileChooser.getSelectedFile();
        File arq;
        if ( arquivo.getName().split("\\.").length <= 1 ||
          ( arquivo.getName().split("\\.").length > 1 && ! arquivo.getName().split("\\.")[1].equals("jvp"))) {
          arq = new File(arquivo.getAbsolutePath() + ".jvp");
        } 
        else
        {
          arq = arquivo;
        }
        try
        {
          if (arq.createNewFile())
          {
            System.out.println("Arquivo constituido: " + arq.getName());
          } 
          else
          {
            System.out.println("Arquivo ja existe.");
            if (JOptionPane.showConfirmDialog(Janela.this, "Arquivo ja existente\n" +
                "Deseja sobrescrever?", "Cuidado!", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
              return;
          }
          try
          {
            FileWriter escritor = new FileWriter(arq.getAbsoluteFile());
            for (Figura figura : figuras)
            {
              escritor.write(figura.toString() + "\n");
            }
            escritor.close();
          }
          catch (IOException ex)
          {
            JOptionPane.showMessageDialog(Janela.this, "Erro na escrita do arquivo!");
            ex.printStackTrace();
          }
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(Janela.this, "Erro na cria��o do arquivo!");
          ex.printStackTrace();
        }
      }
    }
  }
  
  protected class Conectar implements ActionListener {
    public void actionPerformed(ActionEvent e)
    {
      try
      {
        String host = "localhost";
        int porta = 3000;
        cliente = new Cliente(host, porta, Janela.this);
      }
      catch (ConnectException ex)
      {
        JOptionPane.showMessageDialog(Janela.this, "Erro ao conectar");
      }
    }
  }

  protected class Desconectar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (cliente == null)
      {
        JOptionPane.showMessageDialog(Janela.this, "N�o existe nenhuma conex�o!");
        return;
      }
      cliente.desconectarSe();
      cliente = null;
    }
  }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
    
    protected class SalvarBD implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    	      if (cliente == null)
    	      {
    	        JOptionPane.showMessageDialog(Janela.this, "Não existe nenhuma conexão!");
    	        return;
    	      }
    	      String nome = desenho.getNome();
    	      if (nome.equals(""))
    	        nome = JOptionPane.showInputDialog(Janela.this, "Digite um nome para o desenho");
    	      for (Figura figura : figuras)
    	      {
    	    	 System.out.println(figura.toString());
    	        desenho.addFigura(figura.toString());
    	      }
	          	desenho.setNome(nome);
	          	cliente.salvar(desenho);
                nome = "";

    		}
    	 }

    protected class AbrirRemotamente implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		try 
    		{
                if (cliente == null)
                {
                    JOptionPane.showMessageDialog(Janela.this, "Não existe nenhuma conexão!");
                    return;
                }
    			String nome = desenho.getNome();
      	        if (nome.equals(""))
      	        nome = JOptionPane.showInputDialog(Janela.this, "Digite um nome para o desenho");
    			 Desenho desenho = cliente.carregar(nome);
    	         figuras.clear();
    	         pnlDesenho.getGraphics().clearRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());
    	         String[] figurasNoDesenho = desenho.toString().split("\n");
    	            for (String figura : figurasNoDesenho) 
    	            {
    	              StringTokenizer quebrador = new StringTokenizer(figura, ":");
    	              try 
    	              {
    	                switch (quebrador.nextToken()) 
    	                {
    	                case "l":
    	            	    figuras.add(new Linha(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "e":
    	            	    figuras.add(new Elipse(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "d":
    	            	    figuras.add(new Ponto(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "c":
    	            	    figuras.add(new Circulo(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            		  case "t":
    	            	    figuras.add(new Texto(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "p":
    	            	    figuras.add(new Poligono(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "q":
    	            	    figuras.add(new Quadrado(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "r":
    	            	    figuras.add(new Retangulo(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	            	  case "a":
    	            	    figuras.add(new Apagar(figura));
    	            	    figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
    	            	    break;
    	                }
    	              }
    	              catch (Exception ex) 
    	              {
    	                ex.printStackTrace();
    	              }
    	            }
                    nome = "";
    	        } 
    			catch (Exception ex) 
            {
	                ex.printStackTrace();
	              }
    		}
    	}
 
           
    }
