package util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * La classe Myl18N encapsule les méthodes afin de manipuler les fichier MessageBundle permettant de contenir traduction des chaines de caractères
 */
public class MyI18N {

    /**
     * message est utile afin de garder une référence sur le Bundle de clef/valeur chaine de caractère traduire en fonction de
     */
    private static ResourceBundle message = ResourceBundle.getBundle(
                                                "language.MessageBundle",
                                                            Locale.getDefault()
                                                            );

    /**
     * setRegion permet de désigner une langue et un pays à utiliser comme locale afin de définir le language utilisé dans l'application
     * @param language
     * @param country
     */
    public static void setRegion(String language, String country)
    {
        message = ResourceBundle.getBundle(
                        "language.MessageBundle",
                         new Locale(language, country)
                  );
    }

    /**
     * value renvoie la valeur en chaine de caractère pour une clef donné
     * @param stringKey
     * @return la chaine de caractère propre à la clef passé en paramètre
     */
    public static String valueOf(String stringKey) {
        try {
            if (message != null) {
                // rajouter exception si la clef n'existe pas
            } else {
                throw new Exception("message bundle haven't been initialized yet");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message.getString(stringKey);
    }

}
