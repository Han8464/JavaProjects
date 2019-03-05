package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {

    @FXML
    private ImageView imageView;

    @FXML
    private static int click_x = 0;
    private static int click_y = 0;


    public void init() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    Image image = new Image(new FileInputStream("C:\\Users\\Han\\Downloads\\res\\res\\characters\\naruto.png"));
                    double y = image.getHeight() / 4;
                    double x = image.getWidth() / 4;
                    imageView.setImage(image);
                    imageView.setViewport(new Rectangle2D(click_x * x,click_y * y,x,y));
                    if(click_x == 3){
                        click_y ++;
                        click_x = 0;
                    }else{
                        click_x ++;
                    }
                    if(click_y == 4)
                        click_y = 0;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 500);
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(1000)));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Han\\Downloads\\res\\res\\characters\\naruto.png"));
            imageView.setImage(image);
            imageView.setViewport(new Rectangle2D(0, 0, image.getWidth() / 4, image.getHeight() / 4));
            click_x ++;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
