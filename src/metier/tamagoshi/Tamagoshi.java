package metier.tamagoshi;

import presentation.Tamavatar;
import util.MyI18N;

import java.util.Random;
import java.util.logging.Logger;

/**
 * La classe Tamagoshi est l'entité Tamagoshi, afin de traiter les données de chacun
 */
public class Tamagoshi implements Cloneable {

    public static final String TAG = Tamagoshi.class.getName();

    /**
     * age: l'âge du tamagoshi
     * maxEnergy: le maximum d'énergie qu'un Tamagoshi peut avoir
     * maxFun: le maximum de fun qu'un Tamagshi peut avoir
     */
    private int age, maxEnergy, maxFun;

    /**
     * fun: le fun de départ
     * energy: l'énergie de départ
     */
    protected int fun, energy;

    /**
     * Nom du Tamagoshi
     */
    private String name;

    /**
     * durée de vie d'un Tamagoshi
     */
    private static int lifeTime = 10;

    /**
     * utilitaire afin de tirer des nombres alératoire
     */
    private Random random;

    /**
     * l'Avatar d'un tamagosh pour Java FX
     */
    private Tamavatar tamavatar;

    /**
     * Contructeur
     * @param name le nom passé en paramètre
     */
    public Tamagoshi(String name)
    {
        Logger.getLogger(TAG).info(() -> "Tamagoshi");
        this.name = name;
        this.age = 0;
        this.random = new Random();
        this.maxEnergy = random.nextInt(5) + 5; // resultat : de 0/1/2/3/4 + 5
        this.maxFun = random.nextInt(5) + 5;  // resultat : de 0/1/2/3/4 + 5
        this.energy = random.nextInt(5) + 3; // resultat : de 0/1/2/3/4 + 3
        this.fun = random.nextInt(5) + 3; // resultat : de 0/1/2/3/4 + 3
        this.tamavatar = new Tamavatar(this);
        Logger.getLogger(TAG).fine(() -> "done Tamagoshi");
    }

    /**
     * Appelé quand on a besoin de connaitre l'état du Tamagoshi
     * @return renvoi une chaine de caractère pour interface ou terminal
     */
    public boolean speak()
    {
        Logger.getLogger(TAG).info(() -> "speak");
        String message = "";
        boolean result = true;
        if( this.energy <= 4 ) {
            message += MyI18N.valueOf("i-am-starving");
            result = false;
        }
        if( this.fun <= 4) {
            if( message.isEmpty()) {
                message = MyI18N.valueOf("i-feel-bored");
                result = false;
            } else {
                message += " " + MyI18N.valueOf("and-i-feel-bored");
            }
        }
        if( message.isEmpty()) {
            message = MyI18N.valueOf("everythings-fine");
        }
        this.tamavatar.speakFewSpeakWell(this.name + " : \"" + message + "\"");
        Logger.getLogger(TAG).fine(() -> "done speak");
        return result;
    }

    /**
     * Permet au Tamagoshi de regagner un peu d'energy
     * @return renvoi vrai si le Tamagshi avait besoin de manger, faux sinon
     */
    public boolean eat()
    {
        Logger.getLogger(TAG).info(() -> "eat");
        if( this.energy < this.maxEnergy) {
            this.energy += this.random.nextInt(3) + 1;
            this.tamavatar.speakFewSpeakWell(this.name + " : \" " + MyI18N.valueOf("great-i-like") + "\"");
            Logger.getLogger(TAG).fine(() -> "done eat");
            return true;
        } else {
            this.tamavatar.speakFewSpeakWell(this.name + " : \"" + MyI18N.valueOf("i-am-not-a-fatty") + "\"");
            Logger.getLogger(TAG).fine(() -> "done eat");
            return false;
        }
    }

    /**
     * Permet au Tamagoshi de regagner un peu de fun
     * @return Renvoi vrai si le Tamagoshi avait besoin de regagner du fun, faux sinon
     */
    public boolean play()
    {
        Logger.getLogger(TAG).info(() -> "play");
        if( this.fun < maxFun) {
            this.fun += this.random.nextInt(3) + 1;
            this.tamavatar.speakFewSpeakWell(this.name + " : \""+ MyI18N.valueOf("i-have-fun") +"\"");
            Logger.getLogger(TAG).fine(() -> "done play");
            return true;
        } else {
            this.tamavatar.speakFewSpeakWell(this.name + " : \"" + MyI18N.valueOf("dont-want-to-play") + "\"");
            Logger.getLogger(TAG).fine(() -> "done play");
            return false;
        }
    }

    /**
     * À chaque tour, le Tamagoshi consomme un peu de son énergie
     * @return renvoie faux si toute l'énergie est consumé
     */
    public boolean consumeEnergy()
    {
        Logger.getLogger(TAG).info(() -> "consumeEnergy");
        this.energy--;
        if(this.energy <= 0) {
            this.tamavatar.speakFewSpeakWell(this.name + " : \"" + MyI18N.valueOf("he-is-dead-jim") + "......\"");
            Logger.getLogger(TAG).info(() -> "done consumeEnergy and die");
            return true;
        }
        Logger.getLogger(TAG).info(() -> "doneconsumeEnergy");
        return false;
    }

    /**
     * A chaque tour, le Tamagoshi consomme un peu de fun
     * @return renvoi faux si le Tamagoshi a consommé tout son fun
     */
    public boolean consumeFun()
    {
        Logger.getLogger(TAG).info(() -> "consumeFun");
        this.fun--;
        if(this.fun <= 0) {
            this.tamavatar.speakFewSpeakWell(this.name + " : \"" + MyI18N.valueOf("i-am-to-tired-i-die") + "......\"");
            Logger.getLogger(TAG).info(() -> "done consumeFun and die");
            return false;
        }
        Logger.getLogger(TAG).info(() -> "done consumeFun");
        return true;
    }

    /**
     * À chaque tour, le Tamagoshi vieillit un peu
     * @return renvoi faux si le Tamagoshi a consommé son âge limite
     */
    public boolean growOld()
    {
        Logger.getLogger(TAG).info(() -> "growOld");
        this.age++;
        if( this.age >= lifeTime ) {
            this.tamavatar.speakFewSpeakWell(this.name + " : \"" +  MyI18N.valueOf("i-am-too-old-argl") + "......\"");
            Logger.getLogger(TAG).info(() -> "done growOld and die");
            return true;
        } else {
            Logger.getLogger(TAG).info(() -> "done growOld");
            return false;
        }
    }

    /**
     * Est-ce que le Tamagoshi est en vie
     * @return renvoi vrai si le Tamagoshi est toujours en vie
     */
    public boolean isAlive()
    {
        Logger.getLogger(TAG).info(() -> "isAlive");
        return this.fun > 0 && this.energy > 0 && this.age < lifeTime;
    }

    /**
     * @return renvoi le nom du Tamagosi
     */
    public String getName()
    {
        Logger.getLogger(TAG).info(() -> "getName");
        return this.name;
    }

    @Override
    public String toString()
    {
        Logger.getLogger(TAG).info(() -> "toString");
        return "[ " + this.name + " | age : " + this.age + " | energy : " + this.energy + " | maxEnergy : " + this.maxEnergy + " ]";
    }

    /**
     * @return Renvoi l'âge du Tamagoshi
     */
    public int getAge()
    {
        Logger.getLogger(TAG).info(() -> "getAge");
        return age;
    }

    /**
     * @return Renvoi la durée de vie maximale d'un Tamagoshi
     */
    public static int getLifeTime()
    {
        Logger.getLogger(TAG).info(() -> "getLifeTime");
        return lifeTime;
    }

    /**
     * @return Renvoi l'avatar du Tamagoshi
     */
    public Tamavatar getTamavatar()
    {
        Logger.getLogger(TAG).info(() -> "getTamavatar");
        return tamavatar;
    }

    /**
     * Autorise le Tamagoshi à manger
     */
    public void enableEat() {
        if( isAlive() ) {
            Logger.getLogger(TAG).info(() -> "enableEat");
            this.tamavatar.setBtnEat(false);
        }
    }

    /**
     * Empêche le Tamagoshi de manger
     */
    public void disableEat()
    {
        Logger.getLogger(TAG).info(() -> "disableEat");
        this.tamavatar.setBtnEat(true);
    }

    /**
     * Autorise le Tamagoshi à jouer
     */
    public void enablePlay() {
        Logger.getLogger(TAG).info(() -> "enablePlay");
        if( isAlive() ) {
            this.tamavatar.setBtnPlay(false);
        }
    }

    /**
     * Empêche le Tamagoshi de jouer
     */
    public void disablePlay()
    {
        Logger.getLogger(TAG).info(() -> "disablePlay");
        this.tamavatar.setBtnPlay(true);
    }

}