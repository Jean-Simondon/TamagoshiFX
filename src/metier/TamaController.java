package metier;

import exception.WrongStateException;
import metier.tamagoshi.Tamagoshi;
import presentation.WindowGame;
import util.MyI18N;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * La classe TamaController permet d'articuler une partie de tamagoshi
 * Elle tourne en boucle sur les mêmes étapes, étapes qui sont représenté par l'attribut enum State
 */
public class TamaController {

    /**
     * Le TAG de la classe, afin de l'utiliser dans les logger
     */
    private static final String TAG = TamaController.class.getName();

    /**
     * Les différents états de la partie énumérer afin de controller du bon passage de l'un à l'autre
     */
    private enum State { OFF, INIT, START_TURN, BEFORE_EAT, EAT, BEFORE_PLAY, PLAY, END_TURN }

    /**
     * l'état courant
     */
    private static State state = State.OFF;

    /**
     * Deux listes de tamagoshis
     * @all contient tous les tamagohis
     * @remaining contient les tamagoshis toujours vivant
     */
    private static List<Tamagoshi> all, remaining;

    /**
     * L'interface du jeu, qui permet de voir l'information, les Tamagoshi ainsi que d'intéragir avec le jeu
     */
    private static WindowGame windowGame;

    /**
     * le cycle permet de compter le nombre de tour de jeu
     */
    private static int cycle = 0;

    /**
     * le score permet de stocker le score numérique de la partie
     */
    private static int score = 0;

    /**
     * Permet au controller d'obtenir une liste de Tamagoshi définit depuis l'interface et de la copier dans all et remaining
     * @param tamas est une liste de Tamagoshi instancier depuis la fenêtre de configuration
     */
    public static void setTamaList(ArrayList<Tamagoshi> tamas)
    {
        Logger.getLogger(TAG).info(() -> "setTamaList");
        try {
            all = new ArrayList<>(tamas);
            remaining = new ArrayList<>(tamas);
        } catch( Exception e ) {
            Logger.getLogger(TAG).severe(() -> "Exception occur:" + e.getMessage());
        }
        Logger.getLogger(TAG).fine(()-> "done setTamaList" );
    }

    /**
     * Permet de donner au controller une référence sur l'interface qui représente la partie
     * @param window fenêtre de jeu
     */
    public static void registerInterface(WindowGame window)
    {
        Logger.getLogger(TAG).info(()-> "registerInterface" );
        windowGame = window;
        Logger.getLogger(TAG).fine(()-> "done registerInterface" );
    }

    /**
     * Mise en place de la partie
     * Pour chaque Tamagoshi, on va injecter son avatar dans l'interface
     * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    public static void initGame() throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "initGame" );
        if( !state.equals(State.OFF)) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans initGame()");
        } else {
            state = State.INIT;
        }
        for (Tamagoshi t : remaining) {
            windowGame.addTamavatarToColumn(t.getTamavatar());
        }
        Logger.getLogger(TAG).fine(() -> "done initGame");
    }

    /**
     * La partie se découpe en cycle et startTurn permet de commencer un nouveau cycle
     * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    public static void startTurn() throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "startTurn" );
        if( !state.equals(State.INIT) && !state.equals(State.END_TURN) ) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans startTurn()");
        } else {
            state = State.START_TURN;
        }
        windowGame.writeNewInformation("-------- Cycle n° " + ( cycle++ ) + " ----------");
        remaining.forEach(Tamagoshi::speak);
        Logger.getLogger(TAG).fine(() -> "done startTurn");
        beforeEat();
    }

    /**
     * Après le début d'un nouveau tour et avant que les Tamagoshis ne puisse manger
     * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    private static void beforeEat() throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "beforeEat" );
        if( !state.equals(State.START_TURN)) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans beforeEat()");
        } else {
            state = State.BEFORE_EAT;
        }
        remaining.forEach(Tamagoshi::enableEat);
        windowGame.writeNewInformation("-------- " + MyI18N.valueOf("who-want-to-eat") + " ----------");
        Logger.getLogger(TAG).fine("done beforeEat");
    }

    /**
     * Attend qu'un évènement sur un bouton de Tamagoshi n'envoie this(Tamagoshi) au controller pour le faire manger
     * @param t le Tamagoshi sur lequel on a cliqué sur le bouton mange
     * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    public static void eat(Tamagoshi t) throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "eat" );
        if( !state.equals(State.BEFORE_EAT)) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans eat()");
        } else {
            state = State.EAT;
        }
        t.eat();
        Logger.getLogger(TAG).fine(() -> "done eat");
        beforePlay();
    }

    /**
     * Après manger et avant que les Tamagoshis ne puisse jouer
     * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    private static void beforePlay() throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "beforePlay" );
        if( !state.equals(State.EAT)) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans beforePlay()");
        } else {
            state = State.BEFORE_PLAY;
        }
        remaining.forEach(Tamagoshi::disableEat);
        remaining.forEach(Tamagoshi::enablePlay);
        windowGame.writeNewInformation("-------- " + MyI18N.valueOf("who-want-to-play") + " ----------");
        Logger.getLogger(TAG).fine(() -> "done beforePlay");
    }

    /**
     * Attend qu'un évènement sur un bouton de Tamagoshi n'envoie this(Tamagoshi) au controller pour le faire jouer
     * @param t le Tamagoshi sur lequel on a cliqué sur le bouton mange
     * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    public static void play(Tamagoshi t) throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "play" );
        if( !state.equals(State.BEFORE_PLAY)) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans play()");
        } else {
            state = State.PLAY;
        }
        t.play();
        remaining.forEach(Tamagoshi::disablePlay);
        Logger.getLogger(TAG).fine(() -> "done play");
        endTurn();
    }

    /**
      * Fin d'un cycle
      * Décision si l'on doit arrêter la partie ou commencer un nouveau cycle
      * @throws WrongStateException jète une exception si on ne respecte pas l'ordre des étapes du jeu
     */
    private static void endTurn() throws WrongStateException
    {
        Logger.getLogger(TAG).info(()-> "endTurn" );
        if( !state.equals(State.PLAY)) {
            Logger.getLogger(TAG).severe(() -> "Exception occur");
            throw new WrongStateException("Problème de provenance dans endTurn()");
        } else {
            state = State.END_TURN;
        }

        remaining.removeIf( t -> ! ( t.consumeEnergy() || t.consumeFun() || t.growOld()) );

        if( is_not_finished() ) {
            Logger.getLogger(TAG).fine(() -> "done endTurn and start new turn");
            startTurn();
        } else {
            windowGame.writeNewInformation("------------ " + MyI18N.valueOf("game-over") + " -------------");
            Logger.getLogger(TAG).fine(() -> "done endTurn and end game");
            result();
        }
    }

    /**
     * Vérifie que la partie est terminé
     * @return renvoie vrai s'il faut arrêter la partie
     */
    private static boolean is_not_finished()
    {
        Logger.getLogger(TAG).info(()-> "is_not_finished" );
        return !remaining.isEmpty() && cycle < 10;
    }

    /**
     * Calcule le score de la partie en fonction de l'âge des Tamagoshi restés vivant en fin de partie
     * @return renvoie le score de la partie
     */
    private static float score()
    {
        Logger.getLogger(TAG).info(()-> "score" );
        float sumAgeFinal = 0;
        for (Tamagoshi toto : all){
            sumAgeFinal += toto.getAge();
        }
        Logger.getLogger(TAG).fine(() -> "done score");
        return (sumAgeFinal / (Tamagoshi.getLifeTime() * all.size())) * 100;
    }

    /**
     * Affiche le résultat d'une partie
     */
    private static void result()
    {
        Logger.getLogger(TAG).info(()-> "result" );
        windowGame.writeNewInformation("------------- BILAN ------------");

        for(Tamagoshi Titi: all) {
            if( Titi.isAlive() ) {
                windowGame.writeNewInformation(Titi.getName() + " " + MyI18N.valueOf("who-was-a") + " " + Titi.getClass().getSimpleName() + " " + MyI18N.valueOf("survived-and-thank-you") + " :)");
            } else {
                windowGame.writeNewInformation(Titi.getName() + " " + MyI18N.valueOf("who-was-a") + " " + Titi.getClass().getSimpleName() + " " + MyI18N.valueOf("didn't-survived-and-don't-thank-you") + " :(");
            }
        }
        windowGame.writeNewInformation(MyI18N.valueOf("difficulty-level") + " = " + all.size() + " " + MyI18N.valueOf("score-granted") + " : " + score() + "%");
        Logger.getLogger(TAG).fine(() -> "done result");
    }

    @Override
    public String toString() {
        Logger.getLogger(TAG).info(()-> "toString" );
        return "[ TamaController :\n " +
                "liste de tamagoshi complète : " + all + "\n" +
                "liste de tamgaoshi vivant : " + remaining + "\n" +
                "Cycle numéro : " + cycle + "\n" +
                "Interface = " + windowGame + "\n" +
                " ]";
    }
}