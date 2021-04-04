package presentation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.MyI18N;
import util.MyProperties;

public class WindowLaunch extends Application {

    /**
     * Mets en place la toute première fen^^tre du jeu et ses boutons de lancement de partie ou quitter
     * @param primaryStage
     */
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Menu");


        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);


        Button btnNewGameFrench = new Button("Nouvelle partie");
        Button btnNewGameEnglish = new Button("New Game");


        btnNewGameFrench.setLayoutX(20);
        btnNewGameFrench.setLayoutY(30);
        btnNewGameFrench.setOnAction((event) -> {
            MyProperties.store("language", "fr");
            MyProperties.store("country", "FR");
            MyI18N.setRegion(MyProperties.unstore("language"), MyProperties.unstore("country"));
            //            MyI18N.setRegion("fr", "FR");
            WindowConfiguration windowConfiguration = new WindowConfiguration();
            btnNewGameFrench.setDisable(true);
            btnNewGameEnglish.setDisable(true);
        });
        root.getChildren().add(btnNewGameFrench);

        btnNewGameEnglish.setLayoutX(20);
        btnNewGameEnglish.setLayoutY(30);
        btnNewGameEnglish.setOnAction((event) -> {
            MyProperties.store("language", "en");
            MyProperties.store("country", "US");
//            MyI18N.setRegion("en", "US");
            MyI18N.setRegion(MyProperties.unstore("language"), MyProperties.unstore("country"));
            WindowConfiguration windowConfiguration = new WindowConfiguration();
            btnNewGameFrench.setDisable(true);
            btnNewGameEnglish.setDisable(true);
        });
        root.getChildren().add(btnNewGameEnglish);


        Button btnQuit = new Button("Quitter");
        btnQuit.setLayoutX(160);
        btnQuit.setLayoutY(30);
        btnQuit.setOnAction((event) -> {
            Platform.exit();
        });
        root.getChildren().add(btnQuit);


        HBox.setMargin(btnNewGameFrench, new Insets(20, 20, 20,20));
        HBox.setMargin(btnNewGameEnglish, new Insets(20, 20, 20,20));
        HBox.setMargin(btnQuit, new Insets(20, 20, 20,20));


        primaryStage.setScene(new Scene(root, 450, 80));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public String toString() {
        return "[ WindowLaunch ]";
    }
}
