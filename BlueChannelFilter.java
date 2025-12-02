import java.awt.Color;
/**
 * Write a description of class RedChannelFilter here.
 *
 * @author Jimmy Arias
 * @version 12-1-25
 */
public class BlueChannelFilter extends Filter
{
    public BlueChannelFilter(){
    
        super("Blue Channel");
    }

    @Override
    public void apply(OFImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixel (x,y);
                int blue = color.getBlue();
                //Map red intensity to grayscale
                image.setPixel(x,y, new Color(blue, blue, blue));
            }
        }
    }
  
}