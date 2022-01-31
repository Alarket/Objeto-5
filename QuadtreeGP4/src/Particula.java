import java.awt.Color;
import java.awt.Graphics;

public class Particula {
    
    public static float SIZE = 4;
    
    public float posX, posY;
    public boolean Colidindo;

    public void setPos(float posX, float posY)
    {
        if (posX < 0) posX = 0;
	if (posY < 0) posY = 0;
	if (posX > Main.LARGURA) posX = Main.LARGURA;
	if (posY > Main.ALTURA) posY = Main.ALTURA;
        
        this.posX = posX;
        this.posY = posY;
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
    
    
}

