import java.awt.Color;
/**
 * Tinting the image with the corresponding color
 *
 * @author Jimmy Arias
 * @version 12-1-25
 */
public class BlueTintFilter extends Filter
{
    
    public BlueTintFilter() {
        super("Blue Tint Filter");
        
    }

    @Override
    public void apply (OFImage image) {
        for(int y = 0; y <image.getHeight(); y++){
            for (int x = 0; x <image.getWidth(); x++) {
                Color color = image.getPixel(x, y);
                int red = color.getRed();
                int green = color. getGreen();
                int blue = color.getBlue();
                
                //Emphasize blue, reduce green and blue
                Color newColor = new Color(red /2, green /2, blue);
                image.setPixel(x,y, newColor);
            }
        }
    }
}