package com.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import com.OCT.OCT;
public class ImgDiffPercent {
	 public int pixelDiff(int rgb1, int rgb2) {
	        int r1 = (rgb1 >> 16) & 0xff;
	        int g1 = (rgb1 >>  8) & 0xff;
	        int b1 =  rgb1        & 0xff;
	        int r2 = (rgb2 >> 16) & 0xff;
	        int g2 = (rgb2 >>  8) & 0xff;
	        int b2 =  rgb2        & 0xff;
	        return Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
	    }
		private final Logger logger = LogManager.getLogger(ImgDiffPercent.class);

	public double getDifferencePercent(String img1name, String img2name) throws IOException {
		//System.out.println(img1name);
		//System.out.println(img2name);
		
        BufferedImage img1 = ImageIO.read(new File(img1name));
        BufferedImage img2 = ImageIO.read(new File(img2name));
 
        //System.out.println("read images done");
        int width = img1.getWidth();
        int height = img1.getHeight();
        int width2 = img2.getWidth();
        int height2 = img2.getHeight();
        if (width != width2 || height != height2) {
            throw new IllegalArgumentException(String.format("Images must have the same dimensions: (%d,%d) vs. (%d,%d)", width, height, width2, height2));
        }

        long diff = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                diff += pixelDiff(img1.getRGB(x, y), img2.getRGB(x, y));
            }
        }
        //System.out.println("find diff done");
        long maxDiff = 3L * 255 * width * height;
        double res = 100.0 * diff / maxDiff;
        logger.info("diff b/w images : "+String.valueOf(res));
        return res;
    }
	
	public boolean findcontourpoints(String analysis_id) {
		return true;
		
	}
	
	
	
	
	
//    public static void main(String[] args) throws IOException {
//        String firstimg = "first.png";
//        String secondimg = "second.png";
//        BufferedImage img1 = ImageIO.read(new File(firstimg));
//        BufferedImage img2 = ImageIO.read(new File(secondimg));
//
//        double p = getDifferencePercent(img1, img2);
//        System.out.println("diff percent: " + p);
//    }
	
}

