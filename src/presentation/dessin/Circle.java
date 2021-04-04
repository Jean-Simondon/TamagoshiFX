package presentation.dessin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * La classe Circle permet de représenter un cercle en définissant un point et un rayon
 * Elle hérite de GraphicObject afin d'être utilisable par JavaFX
 */
public class Circle extends GraphicObject {

    /**
     * Définit le centre du cercle
     */
    private Point center;

    /**
     * Définit le rayon du cercle
     */
    private int radius;

    /**
     * Construction à portir d'un point et d'un rayon
     * @param center
     * @param radius
     */
    public Circle(Point center, int radius)
    {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Construction afin de donner une couleur au cercle
     * @param center
     * @param radius
     * @param color
     */
    public Circle(Point center, int radius, Color color)
    {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Renvoi le centre du cercke
     * @return
     */
    public Point getCenter()
    {
        return this.center;
    }

    /**
     * Renvoi le rayon du cercle
     * @return
     */
    public int getRadius()
    {
        return this.radius;
    }

    /**
     * Dessine le cercle dans le context d'un graphicContext d'un canvas
     * @param g
     */
    public void drawYou(GraphicsContext g)
    {
        g.setStroke(getColor());
        g.strokeOval(center.getX() - radius, center.getY() - radius, radius *2, radius *2);
    }

    /**
     * Permet de vérifier si la position x y passé en paramètre se situe dans le cercle
     * @param x
     * @param y
     * @return
     */
    public boolean contains(int x, int y)
    {
//        (x,y) ssi : ((x-X)²+(y-Y)²) <= R² // la formule en question
        double X = getCenter().getX();
        double Y = getCenter().getY();
        int R = this.getRadius();
        return (Math.pow((x - X), 2) + Math.pow((y - Y), 2)) <= Math.pow(R, 2);
    }

    @Override
    public String toString() {
        return "[ Circle : " +
                "center = " + center +
                ", radius = " + radius +
                "]";
    }
}
