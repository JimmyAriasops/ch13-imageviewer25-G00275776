import java.awt.Color;
/**
 * Write a description of class RedChannelFilter here.
 *
 * @author Jimmy Arias
 * @version 12-1-25
 */
public class GreenChannelFilter extends Filter
{
    public GreenChannelFilter(){
    
        super("Green Channel");
    }

    @Override
    public void apply(OFImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixel (x,y);
                int green = color.getGreen();
                //Map red intensity to grayscale
                image.setPixel(x,y, new Color(green,green,green));
            }
        }
    }
  
}