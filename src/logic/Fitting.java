package logic;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fitting {
    protected Direction[] directions;
    protected boolean brimful;
    protected Image image;
    protected double x;
    protected double y;
    protected double imageSize = 60;

    public Fitting(int iloscOdgalezien) {
        this.directions = new Direction[iloscOdgalezien];
        this.brimful = false;
    }

    public void rotate(Canvas canvas) {
        for (Direction k : this.directions) {
            if (k.getSide() == Direction.Side.north)
                k.setSide(Direction.Side.east);
            else if (k.getSide() == Direction.Side.east)
                k.setSide(Direction.Side.south);
            else if (k.getSide() == Direction.Side.south)
                k.setSide(Direction.Side.west);
            else if (k.getSide() == Direction.Side.west)
                k.setSide(Direction.Side.north);
        }
        ImageView imageView = new ImageView(image);
        imageView.setRotate(90);
        image = imageView.snapshot(null, null);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, x, y, imageSize, imageSize);
    }

    public void draw(Canvas canvas, double x, double y) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.x = x - x % imageSize;
        this.y = y - y % imageSize;
        gc.drawImage(image, this.x, this.y, imageSize, imageSize);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getImageSize() {
        return imageSize;
    }

    public Direction[] getDirections() {
        return directions;
    }
}
