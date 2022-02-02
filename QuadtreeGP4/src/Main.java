import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {
    
    public static Main get;

    public static final int LARGURA = 1024;
    public static final int ALTURA = 720;
    public static String TIPO;
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame main = new Main();
            main.setVisible(true);
        });
    }
    
    public Main()
    {
        get = this;
        
        TIPO = Simulacao.TOOGLE_QUADTREE ? "com Quadtree" : "sem Quadtree";
        setTitle("Teste de Colisao " + TIPO);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Plano plano = new Plano(LARGURA, ALTURA);
        add(plano);
        getContentPane().setPreferredSize(new Dimension(LARGURA, ALTURA));
        pack();
    }

}
