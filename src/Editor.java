public class Editor {
  
    public static void main(String[] args)
    {
      new Janela();
  
      if (args.length > 1)
      {
        System.err.println("Uso esperado: java Editor [HOST [PORTA]]\n");
      }
  
  
    }
  }