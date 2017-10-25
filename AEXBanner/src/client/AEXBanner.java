package client;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.rmi.RemoteException;


/**
 * Created by quintaartsen on 04-10-17.
 */
public class AEXBanner extends Application {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 100;
    private static final int NANO_TICKS = 20000000;
    private static final int SPEED = 5;

    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;

    @Override
    public void start(Stage primaryStage) {
        try {
            controller = new BannerController(this);
        } catch (RemoteException ex){
            System.out.println(ex.getMessage());
        }

        Font font = new Font("Arial", HEIGHT);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.BLACK);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();

        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS) {
                    if(textPosition < -1 * textLength){
                        textPosition = WIDTH;
                    }
                    textPosition -= SPEED;
                    text.relocate(textPosition, 0);
                    prevUpdate = now;
                }
            }

            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = WIDTH;
                text.relocate(textPosition, 0);
                setExchange("Nothing to display");
                super.start();
            }
        };
        animationTimer.start();
    }

    public void setExchange(String exchange) {
        text.setText(exchange);
        textLength = text.getLayoutBounds().getWidth();
    }

    @Override
    public void stop() {
        animationTimer.stop();
        controller.stop();
    }
}
