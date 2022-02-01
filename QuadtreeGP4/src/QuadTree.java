import java.awt.Graphics;
import java.util.LinkedList;

public class QuadTree {
    
    public static final int MIN_SPLIT = 5;
    private static int MIN_SIZE;
    
    private static QuadTree _get;
    public static QuadTree get()
    {
        return _get;
    }
    
    public BoundingVolume bv;
    
    public LinkedList<BoundingVolume> folhas;
    
    public QuadTree[] filhos;
    
    public void Desenhar(Graphics g)
    {
        
        if (this.filhos != null && this.filhos.length > 0)
        {
            for (QuadTree q : this.filhos)
                q.Desenhar(g);
        }
        
    }
    
    public boolean isFolha()
    {
        return (folhas != null && folhas.size() > 0);
    }
    
    public static QuadTree build(BoundingVolume[] objs)
    {
        if (_get == null){
            double min = Main.LARGURA;
            for (int i = 0; i < MIN_SPLIT; i++)
                min *= 0.5f;
            MIN_SIZE = (int)min;
        }
        
        _get.filhos = null;
        _get.folhas = null;
        
        return _get;
    }
}    
    

