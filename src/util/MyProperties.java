package util;

import java.io.*;
import java.util.Properties;

/**
 * La classe MyProperties encapsule toutes les fonctionnalité liés au fichier properties et à la persistance de données
 */
public class MyProperties {

    public static String propertiesFileLocation = "MyProperties.properties";
    public static Properties myProp = new Properties();

    /**
     * Stocke un paire de clef valeur dans le fichier MyProperties.properties
     * @param key
     * @param value
     */
    public static void store(String key, String value)
    {
        myProp.setProperty(key, value);
        try ( OutputStream out = new FileOutputStream(propertiesFileLocation) ) {
            myProp.store(out, "fichier de configuration du jeu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de récupérer une propriété stocker de manière persistante
     * @param key la cle de la valeur
     * @return renvoi la valeur associé à la clef
     */
    public static String unstore(String key)
    {
        try ( InputStream in = new FileInputStream(propertiesFileLocation) ) {
            myProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myProp.getProperty(key);
    }


}
