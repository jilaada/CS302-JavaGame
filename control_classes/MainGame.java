package control_classes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model_classes.CollisionStruct;
import model_classes.gameObject;
import view_classes.RenderView;

public class MainGame extends Application {

    @Override
    public void start(Stage theStage)
    {
        Group root = new Group();
        Scene scene = new Scene(root, 1024, 768, Color.BLACK);
        theStage.setScene( scene );
        theStage.setTitle("Game window");

        // Set up object control
        ObjectControl ControlUnit = new ObjectControl();

        // IO handle declaration
        IOHandle HandleIO = new IOHandle(scene);

        // Set up a new game
        GameSetUp SetUpGame = new GameSetUp();

        // Add Players via GameSetUp
        //SetUpGame.SetUpPlayers();
        SetUpGame.SetUpBall(10, 5);
        SetUpGame.SetUpPaddles(15, 80);

        // Set up rendering of objects
        RenderView render = new RenderView(SetUpGame.getPlayer1(), SetUpGame.getPlayer2(), SetUpGame.getPlayer3(), SetUpGame.getPlayer4(), SetUpGame.getBall());
        render.SetUpRender();

        // Render the paddles and balls
        root.getChildren().add(render.getBallRender());
        root.getChildren().add(render.getP1Render());
        root.getChildren().add(render.getP2Render());
        root.getChildren().add(render.getP3Render());
        root.getChildren().add(render.getP4Render());

        Collision collisionDetection = new Collision();
        gameObject ballObj = new gameObject(render.getBallRender(), SetUpGame.getBall());
        gameObject paddleObj1 = new gameObject(render.getP1Render(), SetUpGame.getPlayer1().getPlayerPaddle());
        gameObject paddleObj2 = new gameObject(render.getP2Render(), SetUpGame.getPlayer2().getPlayerPaddle());
        gameObject paddleObj3 = new gameObject(render.getP3Render(), SetUpGame.getPlayer3().getPlayerPaddle());
        gameObject paddleObj4 = new gameObject(render.getP4Render(), SetUpGame.getPlayer4().getPlayerPaddle());

        final long startNanoTime = System.nanoTime();


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Run the input handle
                //TODO: this repetitive action can be set in a different function game().tick()?
                HandleIO.keyPressed();

                if (HandleIO.hasMovedLeftP1()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer1().getPlayerPaddle(), 0)) {
                        // Is not horizontal
                        render.getP1Render().setHeight(15);
                        render.getP1Render().setWidth(80);
                    } else {
                        render.getP1Render().setHeight(80);
                        render.getP1Render().setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP1()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer1().getPlayerPaddle(), 1)) {
                        // Is not horizontal
                        render.getP1Render().setHeight(15);
                        render.getP1Render().setWidth(80);
                    } else {
                        render.getP1Render().setHeight(80);
                        render.getP1Render().setWidth(15);
                    }
                }

                if (HandleIO.hasMovedLeftP2()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer2().getPlayerPaddle(), 0)) {
                        // Is not horizontal
                        render.getP2Render().setHeight(15);
                        render.getP2Render().setWidth(80);
                    } else {
                        render.getP2Render().setHeight(80);
                        render.getP2Render().setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP2()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer2().getPlayerPaddle(), 1)) {
                        // Is not horizontal
                        render.getP2Render().setHeight(15);
                        render.getP2Render().setWidth(80);
                    } else {
                        render.getP2Render().setHeight(80);
                        render.getP2Render().setWidth(15);
                    }
                }

                if (HandleIO.hasMovedLeftP3()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer3().getPlayerPaddle(), 0)) {
                        // Is not horizontal
                        render.getP3Render().setHeight(15);
                        render.getP3Render().setWidth(80);
                    } else {
                        render.getP3Render().setHeight(80);
                        render.getP3Render().setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP3()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer3().getPlayerPaddle(), 1)) {
                        // Is not horizontal
                        render.getP3Render().setHeight(15);
                        render.getP3Render().setWidth(80);
                    } else {
                        render.getP3Render().setHeight(80);
                        render.getP3Render().setWidth(15);
                    }
                }

                if (HandleIO.hasMovedLeftP4()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer4().getPlayerPaddle(), 0)) {
                        // Is not horizontal
                        render.getP4Render().setHeight(15);
                        render.getP4Render().setWidth(80);
                    } else {
                        render.getP4Render().setHeight(80);
                        render.getP4Render().setWidth(15);
                    }
                } else if (HandleIO.hasMovedRightP4()) {
                    if (ControlUnit.movePaddle(SetUpGame.getPlayer4().getPlayerPaddle(), 1)) {
                        // Is not horizontal
                        render.getP4Render().setHeight(15);
                        render.getP4Render().setWidth(80);
                    } else {
                        render.getP4Render().setHeight(80);
                        render.getP4Render().setWidth(15);
                    }
                }

                HandleIO.resetPaddle();

                while (HandleIO.isPaused()) {
                    HandleIO.keyPressed();
                }

                CollisionStruct move = collisionDetection.checkCollisions(ballObj, paddleObj1);
                ControlUnit.moveBall(SetUpGame.getBall(), move);
                render.tickRender();
                System.out.print(SetUpGame.getBall().getCurrentPos().getX() + ", " + SetUpGame.getBall().getCurrentPos().getY() + " ");
            }
        }.start();

        theStage.show();
    }

}