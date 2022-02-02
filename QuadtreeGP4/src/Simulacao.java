import java.awt.Graphics;
import java.util.Random;

public class Simulacao extends Thread {

    private static Simulacao _get;
    public static Simulacao get()
    {
        if (_get == null)
            _get = new Simulacao();
        return _get;
    }
    
    public static final boolean TOOGLE_QUADTREE = false;
    
    public final int COUNT = 500;
    
    private Particula[] objs;
    private BoundingVolume[] bvs;
    
    public void Desenhar(Graphics g)
    {
        for (Particula p : this.objs)
            p.Desenhar(g);
        
        if (QuadTree.get() != null)
            QuadTree.get().Desenhar(g);
    }
    
    public void Simular(long decorrido)
    {
        for (Particula p : this.objs)
            p.Update(decorrido);
        
        if (TOOGLE_QUADTREE)
        {
            QuadTree.build(this.bvs);
            
            QuadTree.checarTodasColisoes();
            
        }
        else
        {
            for (Particula p1 : this.objs)
            {
                for (Particula p2 : this.objs)
                {
                    if (p1 == p2)
                        continue;
                    
                    if (p1.bv.Cruza(p2.bv))
                    {
                        p1.Colisao();
                    }
                }
            }
        }
    }
    
    private Simulacao()
    {
        this.objs = new Particula[COUNT];
        this.bvs = new BoundingVolume[COUNT];
        Random rand = new Random();
        
        for (int i = 0; i < COUNT; i++)
        {
            int x = rand.nextInt(Main.LARGURA);
            int y = rand.nextInt(Main.ALTURA);
            Particula p = new Particula(x, y);
            this.objs[i] = p;
            this.bvs[i] = p.bv;
        }
    }
    
}

