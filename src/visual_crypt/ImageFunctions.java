

package justin_and_joel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageFunctions {
	
	static final int BLACK = -16777216; // Constant to represent the RGB binary value of black. In binary - 1111111 00000000 00000000 00000000
	static final int WHITE = -1; // Constant to represent the RGB binary value of white. In binary - 1111111 1111111 1111111 1111111
	
	
	public static String GetPathName(){
		
		JFileChooser openFile = new JFileChooser();
		
		 int ret = openFile.showOpenDialog(null);


		 
		
		 if (ret==0) {

			 File file = openFile.getSelectedFile();

			 String filename = file.getAbsolutePath();

			 
			 
			 return filename;
		 }
		 else{
			 
			
			 
			 return null;
		 }
		
	}

	
	public static BufferedImage Display(File file, String title){
		
		BufferedImage display_image = null;
		
		
		try {
			display_image = ImageIO.read(file);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		
		
		JLabel output = new JLabel(new ImageIcon(display_image));
		JFrame sample = new JFrame(title);
		sample.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sample.getContentPane().add(output);
		sample.pack();sample.setLocation(600,50);
		sample.setVisible(true);
		
		return display_image;
	}
	
		public static void Display_Image(BufferedImage img, String title){
		JLabel test = new JLabel(new ImageIcon(img));
		JFrame sample = new JFrame(title);
		sample.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sample.getContentPane().add(test);
		sample.pack();sample.setLocation(600,50);
		sample.setVisible(true);
	}
	
		public static void Save(BufferedImage img, File path){
		try {
			ImageIO.write( img, "png", path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static BufferedImage Create_Cipher(BufferedImage original, BufferedImage key){
		
		BufferedImage cipher_image = new BufferedImage(
		        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
		        BufferedImage.TYPE_BYTE_BINARY);

		
		for( int i = 0; i<cipher_image.getHeight(); i++){
			for(int j = 0; j<cipher_image.getWidth(); j++){
				if(key.getRGB(j, i) == BLACK){
					int temp = Get_and_Flip(original, i, j);
					cipher_image.setRGB(j, i, temp);
				}
				else{
					cipher_image.setRGB(j, i, original.getRGB(j, i));
				}
			}
		}
		return cipher_image;
	}
	
	
	public static BufferedImage Magnify(BufferedImage img){
		
		BufferedImage magnified_image = new BufferedImage(
				img.getWidth()*2, img.getHeight()*2, BufferedImage.TYPE_BYTE_BINARY);
		
		for(int i = 0; i < img.getHeight(); i++){
			for(int j = 0; j < img.getWidth(); j++){
				if(img.getRGB(j, i) == BLACK){
					
					magnified_image.setRGB(j*2, i*2, BLACK);
					magnified_image.setRGB(j*2+1, i*2, WHITE);
					magnified_image.setRGB(j*2, i*2+1, WHITE);
					magnified_image.setRGB(j*2+1, i*2+1, BLACK);
					
				}
				else{
					
					magnified_image.setRGB(j*2, i*2, WHITE);
					magnified_image.setRGB(j*2+1, i*2, BLACK);
					magnified_image.setRGB(j*2, i*2+1, BLACK);
					magnified_image.setRGB(j*2+1, i*2+1, WHITE);
				}
			}
		}
		return magnified_image;
	}
	
	public static BufferedImage Shrink(BufferedImage img) {
		BufferedImage shrunk_image = new BufferedImage (
				img.getWidth()/2, img.getHeight()/2, BufferedImage.TYPE_BYTE_BINARY);
		
		for(int i = 0; i < img.getHeight(); i += 2) {
			for (int j = 0; j < img.getWidth(); j += 2) {
				if (img.getRGB(j, i) == BLACK) {
					shrunk_image.setRGB(j/2,  i/2,  BLACK);
				}
				else {
					shrunk_image.setRGB(j/2, i/2, WHITE);
				}
			}
		}
		
		return shrunk_image;
	}
	
	
	public static int Get_and_Flip(BufferedImage img, int i, int j){
		
		int initial = img.getRGB(j, i);
		
		if(initial == BLACK){
			return WHITE;
		}
		else{
			return BLACK;
		}
	}

	
	public static BufferedImage Decrypt(BufferedImage image1, BufferedImage image2) {
		
		
		if (image1.getHeight() != image2.getHeight() || image1.getWidth() != image2.getWidth()) {
			System.out.println("The size's of your selected images are mismatched");
			return null;
		}
		
	
		BufferedImage output = new BufferedImage(
				image1.getWidth(), image1.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		
		for (int i = 0; i < image1.getHeight(); i += 2) {
			for (int j = 0; j < image1.getWidth(); j += 2) {
				if (image1.getRGB(j, i) == BLACK && image2.getRGB(j+1,  i) == BLACK){
					output.setRGB(j, i, BLACK);
					output.setRGB(j+1, i, BLACK);
					output.setRGB(j, i+1, BLACK);
					output.setRGB(j+1, i+1, BLACK);
				}
				else if (image1.getRGB(j, i) == WHITE && image2.getRGB(j+1,  i) == WHITE){
					output.setRGB(j, i, BLACK);
					output.setRGB(j+1, i, BLACK);
					output.setRGB(j, i+1, BLACK);
					output.setRGB(j+1, i+1, BLACK);
				}
				else {
					output.setRGB(j, i, WHITE);
					output.setRGB(j+1, i, WHITE);
					output.setRGB(j, i+1, WHITE);
					output.setRGB(j+1, i+1, WHITE);
				}
												
			}
		}
		return output;
	}
	
	
	static BufferedImage make_print_friendly(BufferedImage img) {
		
		int scale = 20; // 20 works well for inkjet printers, dependent on dpi
		
		BufferedImage print_image = new BufferedImage(
				img.getWidth()*scale, img.getHeight()*scale, BufferedImage.TYPE_BYTE_BINARY);
		
	
		for(int i = 0; i < img.getHeight(); i++){
			for(int j = 0; j < img.getWidth(); j++){
				
				
				if(img.getRGB(j, i) == BLACK){
					
				
					for( int x = 0; x < scale; x++) {
						for( int y = 0; y < scale; y++){
							print_image.setRGB(j*scale+y,i*scale+x, BLACK);
						}
					}				
				}
				
				
				else{
					
					
					for( int x = 0; x < scale; x++) {
						for( int y = 0; y < scale; y++){
							print_image.setRGB(j*scale+y,i*scale+x, WHITE);
						}
					}
				}
			}
		}
		return print_image;
	}
}

