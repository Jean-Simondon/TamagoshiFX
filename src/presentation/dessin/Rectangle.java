package presentation.dessin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * La classe Rectangle emploi un objet de type rectangle comme intermédiaire
 * afin d'utiliser les méthode de la classe rectangle déjà existante
 *
 */
public class Rectangle extends GraphicObject {

    /**
     * Le rectangle qui va servir d'hôte
     */
    private javafx.scene.shape.Rectangle rect;

    /**
     * Constructeur afin d'instancier le rectangle hôte
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Rectangle(int x, int y, int width, int height)
    {
        this.rect = new javafx.scene.shape.Rectangle(x, y, width, height);
    }

    /**
     * constructeur à partir d'un point et des dimensions du rectangle
     * @param p
     * @param width
     * @param height
     */
    public Rectangle(Point p, int width, int height)
    {
        this(p.x, p.y, width, height);
    }

    /**
     * Constructeur pour donner une couleur au rectangle
     * @param p
     * @param width
     * @param height
     * @param c
     */
    public Rectangle(Point p, int width, int height, Color c)
    {
        super(c);
        this.rect = new javafx.scene.shape.Rectangle(p.x, p.y, width, height);
    }

    /**
     * Constructeur sans paramètre
     */
    public Rectangle()
    {
        this.rect = new javafx.scene.shape.Rectangle();
    }

    /**
     * Dessine le rectangle dans le context d'un graphicContext d'un canvas
     * @param g
     */
    public void drawYou(GraphicsContext g)
    {
        g.setStroke(getColor());
        g.fillRect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight());
    }

    /**
     * Permet de vérifier si la position x y passé en paramètre se situe dans le rectangle
     * @param x
     * @param y
     * @return
     */
    public boolean contains(int x, int y)
    {
        return this.rect.contains(x, y);
    }

    @Override
    public String toString() {
        return "[ Rectangle : " +
                " rect =" + rect +
                "]";
    }
}
