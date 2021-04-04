package presentation;

import exception.WrongStateException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import metier.TamaController;
import metier.tamagoshi.Tamagoshi;
import presentation.dessin.Circle;
import util.MyI18N;

import java.awt.*;

/**
 * La Classe Tamavatar sert à apporter une interface graphique à la classe Tamagoshi
 */
public class Tamavatar extends HBox {

    /**
     * un canvas et afin de représenter le visage du Tamagoshi
     */
    private Canvas face;

    /**
     * Le graphic context du canvas face
     */
    private GraphicsContext gc;

    /**
     * Le tamagoshi qui utilise l'instance de cette interface
     */
    private Tamagoshi parent;

    /**
     * Les éléments graphique du visage
     */
    private Circle head;
    private Circle RightEye;
    private Circle leftYeye;
    private Circle mouth;

    /**
     * Le nom du tamagoshi
     */
    private Text name;

    /**
     * La sortie en écriture de l'avatar
     */
    private TextFlow aFewWord;

    /**
     * Le bouton pour appeler la méthode eat du TamaController
     */
    private Button btnEat;

    /**
     * Le bouton pour appeler la méthode play du TamaController
     */
    private Button btnPlay;

    /**
     * Constructeur du tamavatar,
     * @param tamagoshi
     */
    public Tamavatar(Tamagoshi tamagoshi)
    {
        this.parent = tamagoshi;
        this.name = new Text(this.parent.getName());


        head = new Circle(new Point(75, 75), 40, Color.BLACK);
        RightEye = new Circle(new Point(58, 60), 11, Color.BLACK);
        leftYeye = new Circle(new Point(92, 60), 11, Color.BLACK);
        mouth = new Circle(new Point(75, 99), 17, Color.BLACK);
        this.face = new Canvas(150, 150);
        gc = this.face.getGraphicsContext2D();


        aFewWord = new TextFlow();
        aFewWord.setPrefSize(100, 30);
        aFewWord.setTextAlignment(TextAlignment.CENTER);


        btnPlay = new Button(MyI18N.valueOf("play"));
        btnPlay.setDisable(true);
        btnPlay.setOnAction((event) -> {
            try {
                TamaController.play(this.parent);
            } catch (WrongStateException e) {
                System.err.println("WrongStateException: " + e.getMessage());
                e.printStackTrace();
            }
        });


        btnEat = new Button(MyI18N.valueOf("eat"));
        btnEat.setDisable(true);
        btnEat.setOnAction((event) -> {
            try {
                TamaController.eat(this.parent);
            } catch (WrongStateException e) {
                System.err.println("WrongStateException: " + e.getMessage());
                e.printStackTrace();
            }
        });


        VBox left_column = new VBox();
        left_column.setAlignment(Pos.CENTER);
        left_column.getChildren().addAll(face);


        VBox middle_column = new VBox();
        middle_column.setAlignment(Pos.CENTER);
        middle_column.setSpacing(10);
        middle_column.getChildren().addAll(name, aFewWord);


        VBox right_column = new VBox();
        right_column.setAlignment(Pos.CENTER);
        right_column.setSpacing(10);
        right_column.getChildren().addAll(btnPlay, btnEat);


        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(left_column, middle_column, right_column);
        this.setMargin(left_column, new Insets(10, 0, 10,10));
        this.setMargin(middle_column, new Insets(10, 0, 10,0));
        this.setMargin(right_column, new Insets(10, 10, 10,0));


        speakFewSpeakWell(MyI18N.valueOf("hi") + " !");
        repaint();
    }

    /**
     * Dessine les éléments graphique du visage dans le graphic context
     */
    private void repaint()
    {
        head.drawYou(gc);
        RightEye.drawYou(gc);
        leftYeye.drawYou(gc);
        mouth.drawYou(gc);
    }

    /**
     * Ecrit un message dans la sortie en écriture de l'interface
     * @param message
     */
    public void speakFewSpeakWell(String message)
    {
        aFewWord.getChildren().clear();
        aFewWord.getChildren().add(new Text(message));
    }

    /**
     * Permet de bloquer ou débloquer le bouton Eat
     * @param bool
     */
    public void setBtnEat(Boolean bool)
    {
        this.btnEat.setDisable(bool);
    }

    /**
     * Permet de bloquer ou débloquer le bouton play
     * @param bool
     */
    public void setBtnPlay(Boolean bool)
    {
        this.btnPlay.setDisable(bool);
    }

    @Override
    public String toString() {
        return " [ Tamavatar : " +
                "face = " + face +
                ", graphic context = " + gc +
                ", Tamagoshi parent = " + parent +
                ", head = " + head +
                ", RightEye = " + RightEye +
                ", leftYeye = " + leftYeye +
                ", mouth = " + mouth +
                ", name = " + name +
                ", word = " + aFewWord +
                ", btnEat = " + btnEat +
                ", btnPlay = " + btnPlay +
                "]";
    }
}
