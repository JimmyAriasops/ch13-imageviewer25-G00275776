import java.awt.Color;
/**
 * Warhol Filter
 *
 * @author Jimmy Arias
 * @version 12-1-25
 */
public class FlippedWarholFilter extends Filter
{
    public FlippedWarholFilter() {
        super("Flipped Warhol Filter");
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
        
        
        //Create tinted versions+ mirrored versions
        OFImage red = tintAndMirror(quarter, "red", true, false);
        OFImage green = tintAndMirror(quarter, "green", false, true);
        OFImage blue = tintAndMirror(quarter, "blue", true, true);
        
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
    //Tint + mirror copy helper
    private OFImage tintAndMirror(OFImage src, String channel, boolean mirrorX, boolean mirrorY) {
        int w = src.getWidth();
        int h = src.getHeight();
        OFImage copy = new OFImage(w,h);
        
        for (int y=0; y < h ; y++) {
            for(int x=0; x < w ; x++) {
                Color c = src.getPixel(x,y);
                int r = c.getRed(), g = c.getGreen(), b = c.getBlue();
                
                //Compute tinted color
                Color tinted;
                switch(channel) {
                    case "red": tinted = new Color(r,g/2,b/2); break;
                    case "green": tinted = new Color(r/2,g,b/2); break;
                    case "blue": tinted = new Color(r/2,g/2,b); break;
                    default: tinted = c;
                }
                int tx = mirrorX ? (w - 1 - x) : x;
                int ty = mirrorY ? (h - 1 - y) : y;
                copy.setPixel(tx, ty, tinted);
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