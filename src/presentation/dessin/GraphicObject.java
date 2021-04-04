package presentation.dessin;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Classe abstraite afin de factoriser les méthodes proprent aux figures utiliser par javaFX
 */
public abstract class GraphicObject
{

    /**
     * La couleur de la figure
     */
    private Color color;

    /**
     * Définit si la figure est visible ou non et doit être dessinée
     */
    protected boolean visible;

    /**
     * Constructeur avec couleur par défaut noire
     */
    public GraphicObject()
    {
        this(Color.BLACK);
    }

    /**
     * Constructeur avec couleur passé en paramètre
     */
    public GraphicObject(Color color)
    {
        this.color = color;
        this.visible = true;
    }

    /**
     * Renvoi la couleur de la figure
     * @return
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Définit la couleur de la figure
     * @param color
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /**
     * Demande à la figure de se dessiner en employant le GraphicsContext passé en paramètre
     * @param g
     */
    public abstract void drawYou(GraphicsContext g);

    /**
     * Définit si la position a b est comprise dans la figure
     * @param a
     * @param b
     * @return
     */
    public abstract boolean contains(int a, int b);

    /**
     * Définit un boolean afin de savoir si la figure doit être dessiner ou non
     * @param bool
     */
    public void setVisibility(boolean bool)
    {
        this.visible = bool;
    }

    /**
     * Renvoi vrai si la figure est visible
     * @return
     */
    public boolean isVisible()
    {
        return this.visible;
    }

}
