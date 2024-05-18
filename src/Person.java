import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Person {
    // images' params
    static int imagesNumber = 48;
    static int height = 50;
    static int width = 50;
    static int maxSpeed = 3;
    // local random
    static Random random = new Random();
    // person's params
    int x;
    int y;
    int speed;
    int destinationFloor;
    BufferedImage image;

    Person(int destination, int x, int y) {
        this.speed = 1 + random.nextInt(maxSpeed);
        this.destinationFloor = destination;
        this.x = x;
        this.y = y;
        String imagePath = new File("").getAbsolutePath() + "\\src\\people\\" + random.nextInt(imagesNumber) + ".png";
        try {
            File f1 = new File(imagePath);
            this.image = ImageIO.read(f1);
        } catch (IOException e) {
            System.out.println("Error while setting an icon:" + e.getMessage());
        }

    }

    void go(int leftBorder, int rightBorder) {
        int newX = this.x + this.speed;
        if (random.nextInt(100) < 6) {
            this.x = leftBorder < newX && newX < (rightBorder - Person.width) ? newX : this.x;
        }
        if (random.nextInt(100) < 2) this.speed *= -1;
    }

    public boolean disappear(int leftBorder, int rightBorder) {
        this.x += speed;
        return this.x < (leftBorder - Person.width / 2) || this.x > (rightBorder - Person.width / 2);
    }

    void paint(Graphics g) {
        // person's icon
        g.drawImage(this.image, this.x, this.y, width, height, null);
    }
}
