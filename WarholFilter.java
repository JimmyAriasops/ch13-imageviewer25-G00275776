import java.awt.Color;
/**
 * Warhol Filter
 *
 * @author Jimmy Arias
 * @version 12-1-25
 */
public class WarholFilter extends Filter
{
    public WarholFilter() {
        super("Warhol Filter");
    }

    /**
     * Constructor for objects of class WarholFilter
     * 
     * @Override
     */
    
    public void apply (OFImage image) {
        //1/4 size image
        int w = image.getWidth() /2;
        int h = image.getHeight() /2;
        OFImage quarter = new OFImage (w,h);
        
        //Scaling down by sampling every other pixel
        for (int y= 0; y < h; y++) {
            for(int x = 0; x < w; x++) {
                quarter.setPixel(x,y,image.getPixel(x * 2, y * 2));
            }
            
        }
        
        
        //Create tinted versions
        OFImage red = tintCopy(quarter, "red");
        OFImage green = tintCopy(quarter, "green");
        OFImage blue = tintCopy(quarter, "blue");
        
        //2x2 grid composite
        OFImage composite = new OFImage(w * 2, h * 2);
        copyInto(composite, quarter, 0, 0); //top-left
        copyInto(composite, red, w, 0); //top-right
        copyInto(composite, green, 0, h); //bottom-left
        copyInto(composite, blue, w, h); //bottom-right
        
        //Replace original with composite
        for(int y= 0; y <composite.getHeight(); y++) {
            for(int x = 0; x <composite.getWidth(); x++) {
                image.setPixel(x,y, composite.getPixel(x,y));
            }
        }
    }
    //Tint copy helper
    private OFImage tintCopy(OFImage src, String channel) {
        OFImage copy = new OFImage(src);
        for (int y=0; y <copy.getHeight(); y++) {
            for(int x=0; x <copy.getWidth(); x++) {
                Color c = copy.getPixel(x,y);
                int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
                switch(channel) {
                    case "red": copy.setPixel(x,y, new Color(r,g/2,b/2)); break;
                    case "green": copy.setPixel(x,y, new Color(r/2,g,b/2)); break;
                    case "blue": copy.setPixel(x,y, new Color(r/2,g/2,b)); break;
                }
            }
        }
        return copy;
    }

    //Copy helper
    private void copyInto(OFImage target, OFImage src, int offsetX, int offsetY) {
        for(int y=0; y <src.getHeight(); y++) {
            for(int x = 0; x <src.getWidth(); x++){
                target.setPixel(x + offsetX, y + offsetY, src.getPixel(x,y));
            }
        }
    }
}