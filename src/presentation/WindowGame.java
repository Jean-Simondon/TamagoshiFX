package presentation;

import exception.WrongStateException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import metier.TamaController;
import metier.tamagoshi.Tamagoshi;
import util.MyI18N;

import java.util.ArrayList;

/**
 * La classe WindowGame est utile comme interface pour le déroulement de la partie
 */
public class WindowGame {

    /**
     * Liste de Tamavatar
     */
    private VBox colonneTamagoshi;

    /**
     * TextFlow afin d'écrire les différents information de la partie
     */
    private TextFlow colonneInfo;

    public WindowGame(ArrayList<Tamagoshi> tamagoshis)
    {
        Stage s = new Stage();
        s.setTitle(MyI18N.valueOf("game-window"));


        colonneTamagoshi = new VBox();
        colonneTamagoshi.setAlignment(Pos.CENTER);
        ScrollPane scrollableTamagoshi = new ScrollPane(colonneTamagoshi);


        colonneInfo = new TextFlow();
        colonneInfo.setTextAlignment(TextAlignment.CENTER);
        colonneInfo.setPrefSize(500, 800);
        colonneInfo.setLineSpacing(5.0);
        colonneInfo.getChildren().add(new Text(MyI18N.valueOf("game") + " :\n"));
        ScrollPane scrollableInfo = new ScrollPane(colonneInfo);


        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(scrollableInfo, scrollableTamagoshi);


        s.setScene(new Scene(root, 850, 800));
        s.show();


        TamaController.registerInterface(this);
        TamaController.setTamaList(tamagoshis);
        try {
            TamaController.initGame();
            TamaController.startTurn();
        } catch (WrongStateException e) {
            System.err.println("WrongStateException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Permet d'ajouter un Tamavatar à la liste de tamavatar
     * @param tamaV
     */
    public void addTamavatarToColumn(Tamavatar tamaV) {
        this.colonneTamagoshi.getChildren().add(tamaV);
    }

    /**
     * Permet d'écrire dans colonneInfo
     * @param arg
     */
    public void writeNewInformation(String arg) {
        colonneInfo.getChildren().add(new Text(arg + "\n"));
    }

    @Override
    public String toString() {
        return "[ WindowGame = " +
                "colonneTamagoshi = " + colonneTamagoshi +
                ", colonneInfo = " + colonneInfo +
                "]";
    }
}
