import java.awt.Color;
/**
 * Write a description of class RedChannelFilter here.
 *
 * @author Jimmy Arias
 * @version 12-1-25
 */
public class RedChannelFilter extends Filter
{
    public RedChannelFilter(){
    
        super("Red Channel");
    }

    @Override
    public void apply(OFImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixel (x,y);
                int red = color.getRed();
                
                image.setPixel(x,y, new Color(red,red,red));
            }
        }
    }
  
}