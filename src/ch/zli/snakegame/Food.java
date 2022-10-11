package ch.zli.snakegame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import ch.zli.snakegame.util.Coord;

import javax.imageio.ImageIO;

public class Food {
    private Coord position;
    private BufferedImage img = null;
    private String imgPath = "src/ch/zli/snakegame/643464-my-pussy.jpg";


    /**
     * Foodconstructor
     * @param pos as object Coord, is the position we place the food
     */
    public Food(Coord pos){
        this.position = pos;
        try{
            img = ImageIO.read(new File(imgPath));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Draws the food as a red rectangel
     * @param g is the graphics2D to paint
     * @param square is the width of a square in the panel
     * @param offset is the amount of pixel we have left after all squares
     */
    public void draw(Graphics2D g, int square, int offset){
        int x = position.getX() * square + 2 + (offset / 2);
        int y = position.getY() * square + 2 + (offset / 2);

        if (img != null){
            g.drawImage(img.getScaledInstance(square - 4,square - 4,1), x, y, null);
        }
        else {
            g.setColor(Color.RED);
            g.fillRect(x, y, square - 4, square - 4);
        }
    }

    /**
     * Checks if a position intesects with the food
     * @param pos the position we want to check, as object from the helpclass Coord
     * @return true or fals, true if pos intersects with the food
     */
    public boolean intersectsWith(Coord pos){
        return this.position.equals(pos);
    }

    /**
     * To get the position of the food
     * @return the position as Coord
     */
    public Coord getFoodPos(){
        return position;
    }
}
