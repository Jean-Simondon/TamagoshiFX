package metier.tamagoshi;

import metier.TamaController;

import java.util.logging.Logger;

/**
 * Déclinaison du Tamagoshi qui manque plus que le Tamagoshi classique
 */
public class Eater extends Tamagoshi {

    /**
     * Le nom de la classe en attribut pour les logger
     */
    public static final String TAG = Eater.class.getName();

    /**
     * Constructeur de la classe Eater
     * @param name le nom du Tamagoshi
     */
    public Eater(String name)
    {
        super(name);
        Logger.getLogger(TAG).info(() -> "Eater");
    }

    /**
     * Redéfinission de la méthode consumeEnergy
     * @return renvoie faux si toute l'énergie est consumé
     */
    public boolean consumeEnergy()
    {
        Logger.getLogger(TAG).info(() -> "consumeEnergy");
        this.energy--;
        return super.consumeEnergy();
    }

}
