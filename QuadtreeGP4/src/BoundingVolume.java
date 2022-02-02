import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class BoundingVolume {
    
    public float Direita;
    public float Baixo;
    public float Esquerda;
    public float Cima;
    
    public Particula particula;
    
    public BoundingVolume(float l, float t, float r, float b)
    {
        this.Esquerda = l;
        this.Cima = t;
        this.Direita = r;
        this.Baixo = b;
    }
    
    public float getAltura()
    {
        return Baixo - Cima;
    }
    
    public float getLargura()
    {
        return Direita - Esquerda;
    }
    
    public void Desenhar(Graphics g)
    {
        g.setColor(Color.DARK_GRAY);
        g.drawRect((int)this.Esquerda, (int)this.Cima, (int)this.getLargura(), (int)this.getAltura());
    }
    
    public static BoundingVolume Grupo(LinkedList<BoundingVolume> group)
    {
        float esquerda = Plano.WIDTH, cima = Plano.HEIGHT, direita = 0, baixo = 0;
        for (BoundingVolume b : group)
        {
        	esquerda = Math.min(esquerda, b.Esquerda);
            cima = Math.min(cima, b.Cima);
            direita = Math.max(direita, b.Direita);
            baixo = Math.max(baixo, b.Baixo);
        }
        
        return new BoundingVolume(esquerda, cima, direita, baixo);
    }
    
    public boolean Cruza(BoundingVolume outro)
    {
        if (this.Esquerda > outro.Direita || this.Direita < outro.Esquerda ||
                this.Cima > outro.Baixo || this.Baixo < outro.Cima)
            return false;
        return true;
    }
    
    
}

