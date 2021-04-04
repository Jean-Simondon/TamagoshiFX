package presentation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import metier.tamagoshi.Player;
import metier.tamagoshi.Eater;
import metier.tamagoshi.Tamagoshi;
import util.MyI18N;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Fenêtre de configuration de la partie
 * Permet d'ajouter des Tamagoshi à
 */
public class WindowConfiguration {

    /**
     * Liste de Tamagoshi rempli au fur et à mesure de l'ajout de nouveau Tamagoshi
     */
    private ArrayList<Tamagoshi> tamagoshis = new ArrayList<>();

    /**
     * Constructeur de la fenêtre WindowConfiguration
     */
    public WindowConfiguration()
    {
        Stage s = new Stage();
        s.setTitle(MyI18N.valueOf("game-configuration"));

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);


        VBox right_column = new VBox();
        right_column.setAlignment(Pos.CENTER);
        right_column.setSpacing(20);
        right_column.getChildren().add(new Text(MyI18N.valueOf("tamagoshi") + " :"));


        VBox left_column = new VBox();
        left_column.setAlignment(Pos.CENTER);
        left_column.setSpacing(20);


        TextField inputNameTamagoshi = new TextField("");
        inputNameTamagoshi.setMinWidth(120);
        left_column.getChildren().add(inputNameTamagoshi);

        Text tamaInscruction = new Text(MyI18N.valueOf("add-as-much-tamagoshi-as-the-difficulty-level"));
        left_column.getChildren().add(tamaInscruction);


        Button btnAddNewTama = new Button(MyI18N.valueOf("add-a-tamagoshi"));
        btnAddNewTama.setLayoutX(20);
        btnAddNewTama.setLayoutY(30);
        btnAddNewTama.setOnAction((event) -> {
            if( !inputNameTamagoshi.getText().equals("") ) {
                Tamagoshi t;
                if( Math.random() < 0.5 ) {
                    t = new Player(inputNameTamagoshi.getText());
                } else {
                    t = new Eater(inputNameTamagoshi.getText());
                }
                tamagoshis.add(t);
                right_column.getChildren().add(new Text(inputNameTamagoshi.getText()));
                inputNameTamagoshi.setText("");
            }
        });
        left_column.getChildren().add(btnAddNewTama);


        AtomicReference<Boolean> errorIsDisplayd = new AtomicReference<>(false);
        Button newGame = new Button(MyI18N.valueOf("launch-game"));
        newGame.setOnAction((event) -> {
            if( tamagoshis.size() > 0 ) {
                WindowGame windowGame = new WindowGame(tamagoshis);
                btnAddNewTama.setDisable(true);
                newGame.setDisable(true);
            } else if (!errorIsDisplayd.get()) {
                Text tamaError = new Text(MyI18N.valueOf("a-tamagoshi-is-necessary-to-play") + " !");
                left_column.getChildren().add(tamaError);
                errorIsDisplayd.set(true);
            }
        });
        left_column.getChildren().add(newGame);


        root.getChildren().addAll(left_column, right_column);
        root.setMargin(left_column, new Insets(20, 20, 20,20));
        root.setMargin(right_column, new Insets(20, 20, 20,20));


        s.setScene(new Scene(root, 400, 400));
        s.show();
    }

    @Override
    public String toString() {
        return "[ WindowConfiguration : " +
                " Tamagoshis=" + tamagoshis +
                "]";
    }
}
