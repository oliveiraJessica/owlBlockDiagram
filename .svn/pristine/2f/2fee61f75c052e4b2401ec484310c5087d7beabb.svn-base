package br.ufrn.lii.owlblockdiagram.utils; 
  
import br.ufrn.lii.owlblockdiagram.model.TreeIcon; 
import java.awt.Color; 
import java.awt.image.BufferedImage; 
import java.awt.image.ColorModel; 
import java.awt.image.WritableRaster; 
import java.util.Random; 
import org.openide.util.ImageUtilities; 
  
/** 
 * 
 * @author Jessica 
 */
public class ImageUtil { 
  
    public static TreeIcon ramdonColorIcon(String path) { 
        Random rand = new Random(); 
  
        int r = rand.nextInt(255); 
        int g = rand.nextInt(255); 
        int b = rand.nextInt(255); 
  
        Color color = new Color(r, g, b); 
  
        return ImageUtil.colorIcon(path, color); 
    } 
  
    public static TreeIcon colorIcon(String path, Color color) { 
        BufferedImage imagem = (BufferedImage) ImageUtilities.loadImage(path); 
        BufferedImage objClone = (BufferedImage) ImageUtil.deepCopy(imagem); 
        int width = objClone.getWidth(); 
        int height = objClone.getHeight(); 
  
        for (int i = 0; i < width; i++) { 
            for (int j = 0; j < height; j++) { 
                int c = color.getRGB(); 
                objClone.setRGB(i, j, c); 
  
            } 
        } 
  
        TreeIcon treeIcon = new TreeIcon(objClone, color); 
  
        return treeIcon; 
    } 
  
    public static BufferedImage deepCopy(BufferedImage oldObj) { 
  
        ColorModel cm = oldObj.getColorModel(); 
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied(); 
        WritableRaster raster = oldObj.copyData(null); 
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null); 
    } 
} 