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
    private long lag;
    private int marcas = 0;
    private long TempoPassado;
    
    private final int PAINT = 16;
    
    private final int UPDATE = 0;
    
    private final Timer timer;
    
    Simulacao sim;
    
    @Override public void paint(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.LARGURA, Main.ALTURA);
        
        sim.Desenhar(g);
        
        java.awt.Toolkit.getDefaultToolkit().sync();
        
    }
    
    public Plano(int largura, int altura)
    {
        setSize(new Dimension(largura, altura));
        
        sim = Simulacao.get();
        
        this.TempoPassado = System.currentTimeMillis();
        timer = new Timer(UPDATE, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    	marcas++;
        long TempoAtual = System.currentTimeMillis();
        long Decorrido = TempoAtual - this.TempoPassado;
        TempoTotal += Decorrido;
        this.TempoPassado = TempoAtual;
        
        sim.Simular(Decorrido);
        
        lag += Decorrido;
        while (lag >= PAINT)
        {
            repaint();
            lag -= PAINT;
        }
        
        if (TempoTotal >= FIM * 1000)
        {
            timer.stop();
            String log = "Teste " + Main.TIPO + " com " + sim.COUNT + " objetos: Media de " + ((TempoTotal / (float)marcas) * 1000) + " microssegundos por frame.";
            System.out.println(log);
            Main.get.setTitle(log);
        }
    }
}