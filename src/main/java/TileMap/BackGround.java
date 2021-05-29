package TileMap;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class BackGround {

    private BufferedImage image;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public BackGround(String s ,double ms){
        try {
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            moveScale = ms;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {

        if(image == null){
            System.exit(0);
        }
        g.drawImage(image, (int)x, (int)y, null);

        if(x < 0) {
            g.drawImage(
                    image,
                    (int)x + GamePanel.WIDTH,
                    (int)y,
                    null
            );
        }
        if(x > 0) {
            g.drawImage(
                    image,
                    (int)x - GamePanel.WIDTH,
                    (int)y,
                    null
            );
        }
    }
}
