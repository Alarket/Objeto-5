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
    
    public boolean Cruza(BoundingVolume outro)
    {
        return this.bv.Cruza(outro);
    }
    
    public void Desenhar(Graphics g)
    {
        this.bv.Desenhar(g);
        
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
            _get = new QuadTree(0, 0, Main.LARGURA, Main.ALTURA);
            double min = Main.LARGURA;
            for (int i = 0; i < MIN_SPLIT; i++)
                min *= 0.5f;
            MIN_SIZE = (int)min;
        }
        
        _get.filhos = null;
        _get.folhas = null;
        
        for (BoundingVolume v : objs)
            _get.add(v);
        
        return _get;
    }
    
    public void add(BoundingVolume bv)
    {
        if (this.bv == bv)
            return;
        
	if (!this.Cruza(bv)){
            return;
	}
        
        if (this.filhos == null)
        {
            if (this.folhas == null)
                this.folhas = new LinkedList<>();
            
            if (this.folhas.size() < 2){
		this.folhas.add(bv);
            }
            else
            {
                float largura = this.bv.getLargura() * 0.5f;
                if (largura < MIN_SIZE)
                {
                    this.folhas.add(bv);
                }
                else
                {
                    float esquerda = this.bv.Esquerda;
                    float cima = this.bv.Cima;
                    float centerX = esquerda + this.bv.getLargura() * 0.5f;
                    float centerY = cima + this.bv.getAltura() * 0.5f;
                    float direita = this.bv.Direita;
                    float baixo = this.bv.Baixo;

                    this.filhos = new QuadTree[4];

                    this.filhos[0] = new QuadTree(esquerda, cima, centerX, centerY);
                    this.filhos[1] = new QuadTree(centerX, cima, direita, centerY);
                    this.filhos[2] = new QuadTree(esquerda, centerY, centerX, baixo);
                    this.filhos[3] = new QuadTree(centerX, centerY, direita, baixo);
                    
                    for (QuadTree c : this.filhos)
                    {
                        c.add(bv);
                        
                        for (BoundingVolume b : this.folhas)
                            c.add(b);
                    }
                    this.folhas.clear();
                }
            }
        }
        else
        {
            for (QuadTree c : this.filhos)
            {
                c.add(bv);
            }
        }
    }
    

    public static void checarTodasColisoes()
    {
        _get.checarTodasColisoes(_get);
    }
    
    private QuadTree(float l, float t, float r, float b)
    {
        this.bv = new BoundingVolume(l, t, r, b);
    }
    
    public void checarTodasColisoes(QuadTree _this)
    {

        if (_this.isFolha())
        {
            for (BoundingVolume v1 : _this.folhas)
            {
                for (BoundingVolume v2 : _this.folhas)
                {
                    if (v1 == v2)
                        continue;
                    
                    if (v1.Cruza(v2))
                        v1.particula.Colisao();
                }
            }
        }

        else if (_this.filhos != null)
        {
            for (QuadTree c : _this.filhos)
            	checarTodasColisoes(c);
        }
    }
}

