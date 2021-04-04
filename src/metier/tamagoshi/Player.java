package metier.tamagoshi;

import java.util.logging.Logger;

/**
 * Déclinaison du Tamagoshi qui consomme plus de fun que le Tamagoshi classique
 */
public class Player extends Tamagoshi {

    /**
     * Le nom de la classe en attribut pour les logger
     */
    public static final String TAG = Player.class.getName();

    /**
     * Constructeur de Player
     * @param name le nom du Tamagoshi
     */
    public Player(String name)
    {
        super(name);
        Logger.getLogger(TAG).info(() -> "Player");
    }

    /**
     * Redéfinission de la méthode consumeFun
     * @return renvoie faux si toute le fun est consumé
     */
    public boolean consumeFun()
    {
        Logger.getLogger(TAG).info(() -> "consumeFun");
        this.fun--;
        return super.consumeFun();
    }

}
