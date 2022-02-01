import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Plano extends JPanel implements ActionListener {
    
    private final int FIM = 10;

    private long TempoTotal;
    private long TempoPassado;
    
    private final int PAINT = 16;
    
    private final int UPDATE = 0;
    
    private final Timer timer;
    
    @Override public void paint(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.LARGURA, Main.ALTURA);
       
        
        java.awt.Toolkit.getDefaultToolkit().sync();
        
    }
    
    public Plano(int largura, int altura)
    {
        setSize(new Dimension(largura, altura));
       
        
        this.TempoPassado = System.currentTimeMillis();
        timer = new Timer(UPDATE, this);
        timer.start();
    }

    
}
