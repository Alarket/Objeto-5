import java.awt.Color;
import java.awt.Graphics;

public class Particula {
    
    public static float SIZE = 4;
    
    public float posX, posY;
    public boolean Colidindo;
    public BoundingVolume bv;

    public void setPos(float posX, float posY)
    {
        if (posX < 0) posX = 0;
	if (posY < 0) posY = 0;
	if (posX > Main.LARGURA) posX = Main.LARGURA;
	if (posY > Main.ALTURA) posY = Main.ALTURA;
        
        this.posX = posX;
        this.posY = posY;
        this.bv.Esquerda = posX - SIZE;
        this.bv.Cima = posY - SIZE;
        this.bv.Direita = posX + SIZE;
        this.bv.Baixo = posY + SIZE;
    }
    
    public Particula(float x, float y)
    {
        this.posX = x;
        this.posY = y;
        this.bv = new BoundingVolume(x - SIZE, y - SIZE, x + SIZE, y + SIZE);
        this.bv.particula = this;
    }
    
    
    public void Colisao()
    {
        this.Colidindo = true;
    }
    
    private void Mover(float dirX, float dirY)
    {
        setPos(this.posX + dirX, this.posY + dirY);
    }
    
    public void Update(long elapsedTime)
    {
        this.Colidindo = false;
        
        float x = (float)( Math.random() - 0.5f ) * elapsedTime;
        float y = (float)( Math.random() - 0.5f ) * elapsedTime;
        Mover(x, y);
    }
    
    public void Desenhar(Graphics g)
    {
        Color c;
        if (this.Colidindo)
            c = Color.red;
        else
            c = Color.blue;
        
        g.setColor(c);
        g.fillRect((int)this.bv.Esquerda, (int)this.bv.Cima, (int)this.bv.getLargura(), (int)this.bv.getAltura());
    }
    
}

