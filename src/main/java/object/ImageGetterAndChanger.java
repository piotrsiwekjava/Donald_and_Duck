package object;

import settings.Pathes_and_Links;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageGetterAndChanger {
    private static ImageGetterAndChanger ImChInstance = new ImageGetterAndChanger();
    private ImageGetterAndChanger(){}
    public static ImageGetterAndChanger getInstance(){
        return ImChInstance;
    }
    public BufferedImage getImage(String fileName){
        String imagePath = Pathes_and_Links.pathImageFile+fileName+".png";
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return image;
    }
    public BufferedImage getTransImg(String fileName){
        return getTransparent(getImage(fileName));
    }
    private BufferedImage getTransparent (BufferedImage im ){

        Image imageWithTransparency = makeColorTransparent(im, Color.WHITE, 2);
        BufferedImage transparentImage = imageToBufferedImage(imageWithTransparency);
        return transparentImage;
    }



    private BufferedImage imageToBufferedImage(final Image image) {
        final BufferedImage bufferedImage =
                new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }
    public BufferedImage mirrorImage (BufferedImage image){
        BufferedImage mirrored = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)mirrored.getGraphics();
        AffineTransform transform = new AffineTransform();
        transform.setToScale(-1, 1);
        transform.translate(-image.getWidth(), 0);
        graphics.setTransform(transform);
        graphics.drawImage(image, 0, 0, null);
        return mirrored;
    }
    public BufferedImage rotate(int angle, ObjectImage objectImage){
        BufferedImage image = objectImage.getImage();
        BufferedImage rotated = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D)rotated.getGraphics();

        double theta = (Math.PI*2)/360 * angle;
        graphics.rotate(theta);
        graphics.drawImage(image, 0, 0, null);

        return rotated;
    }


    private Image makeColorTransparent(final BufferedImage im, final Color color, int tolerance) {
        int temp = 0;

        if (tolerance < 0 || tolerance > 100) {

            System.err.println("The tolerance is a percentage, so the value has to be between 0 and 100.");
            temp = 0;
        } else {
            temp = tolerance * (0xFF000000 | 0xFF000000) / 100;
        }
        final int toleranceRGB = Math.abs(temp);

        final ImageFilter filter = new RGBImageFilter() {

            public int markerRGBFrom = (color.getRGB() | 0xFF000000) - toleranceRGB;

            public int markerRGBTo = (color.getRGB() | 0xFF000000) + toleranceRGB;

            public final int filterRGB(final int x, final int y, final int rgb) {
                if ((rgb | 0xFF000000) >= markerRGBFrom && (rgb | 0xFF000000) <= markerRGBTo) {
                    return 0x00FFFFFF & rgb;
                } else {
                    return rgb;
                }
            }
        };
        final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
