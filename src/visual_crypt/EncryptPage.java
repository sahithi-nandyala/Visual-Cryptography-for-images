
package justin_and_joel;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class EncryptPage extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	static final int BLACK = -16777216;  
	static final int WHITE = -1; 
	private boolean imageFlag;  
	private boolean textFlag;  
	
	JRadioButton rdbtnImage;
	JRadioButton rdbtnText;
	JButton btnOriginal;
	JButton btnModified;
	JTextArea textArea;
	JButton btnEncrypt;
	JButton btnClear;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptPage frame = new EncryptPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EncryptPage() {
		
		imageFlag = false;
		textFlag = false;
		
		  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel lblTitle = new JLabel("Create Encrypted Images");
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTitle.setBorder(loweredetched);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(69, 12, 304, 23);
		contentPane.add(lblTitle);
		
		
		final JLabel Original_Path_Name = new JLabel("No Path Selected");
		Original_Path_Name.setBounds(147, 64, 291, 30);
		contentPane.add(Original_Path_Name);
		
		
		final JLabel Save_Path_Name = new JLabel("No Save Path Selected");
		Save_Path_Name.setBounds(147, 97, 291, 30);
		contentPane.add(Save_Path_Name);
		
	
		final JCheckBox chckbxIncludePrintFriendly = new JCheckBox("Add Print Friendly Copy");
		chckbxIncludePrintFriendly.setBounds(232, 256, 206, 23);
		contentPane.add(chckbxIncludePrintFriendly);
		
		
		rdbtnImage = new JRadioButton("Image");
		rdbtnImage.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				imageFlag = true;
				textFlag = false;
				
			
				rdbtnText.setSelected(false);
			}
		});
		rdbtnImage.setBounds(8, 35, 85, 23);
		contentPane.add(rdbtnImage);
		
	
		rdbtnText = new JRadioButton("Text");	
		rdbtnText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				imageFlag = false;
				textFlag = true;
				
				rdbtnImage.setSelected(false);
			}
		});
		rdbtnText.setBounds(91, 35, 79, 23);
		contentPane.add(rdbtnText);
		
		
		btnOriginal = new JButton("Original");
		btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(imageFlag == false && textFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				
				else{
					
					
					Main.path = ImageFunctions.GetPathName();
					
					
					try{
						Main.file = new File(Main.path);
						Main.originalImage = ImageFunctions.Display(Main.file, "Original");
					} catch (NullPointerException e) {
						Original_Path_Name.setText("Error opening image file");
						return;
					}
		
					
					Original_Path_Name.setText(Main.path);
				}
			}
		});
		btnOriginal.setBounds(12, 66, 117, 25);
		contentPane.add(btnOriginal);
		
		
		btnModified = new JButton("Modified");
		btnModified.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(imageFlag == false && Main.path == null && rdbtnText.isSelected() == false){
					System.out.println("Select image radio button/select an original image");
					return;
				}
				
				else{
					
				
					Main.save_path = ImageFunctions.GetPathName();
					
				
					if (Main.save_path == null) {
						Save_Path_Name.setText("Error selecting save destination");
						return;
					}
					
					
					Main.save_key_path = Main.save_path + "_key.png";
					Main.key_file = new File(Main.save_key_path);
					System.out.println("Save key: " + Main.save_key_path);
										
				
					Main.save_cipher_path = Main.save_path + "_cipher.png";
					Main.cipher_file = new File(Main.save_cipher_path);
					System.out.println("Save cipher: " + Main.save_cipher_path);
					
					
					Save_Path_Name.setText(Main.save_path + ".png");
									
					
				}
			}
		});
		btnModified.setBounds(12, 100, 117, 25);
		contentPane.add(btnModified);
		
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(12, 137, 70, 15);
		contentPane.add(lblMessage);
		
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 24));
		textArea.setLineWrap(true);
		textArea.setBounds(12, 164, 426, 79);
		contentPane.add(textArea);
		
	
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String text = textArea.getText();
				
				
				if(imageFlag == false && text.equals("")){
					System.out.println("Enter some text");
					return;
				}
				
				
				if(imageFlag == false && textFlag == true){
					
					
					
				
					BufferedImage text_image = new BufferedImage(textArea.getWidth(), textArea.getHeight(), BufferedImage.TYPE_BYTE_BINARY );
					Graphics2D graphic = text_image.createGraphics();
					textArea.printAll(graphic);
					graphic.dispose();
					
					
					
					Main.originalImage = text_image;
				}
				
				
				Main.save_key_magnified_path = Main.save_path + "_key_magnified.png";
				Main.save_cipher_magnified_path = Main.save_path + "_cipher_magnified.png";
				Main.key_magnified_file = new File(Main.save_key_magnified_path);
				Main.cipher_magnified_file = new File(Main.save_cipher_magnified_path);
				
				
				BufferedImage black_white = new BufferedImage(
				        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
				        BufferedImage.TYPE_BYTE_BINARY);
				Graphics2D graphics = black_white.createGraphics();
				graphics.drawImage(Main.originalImage, 0, 0, null);

				
				Main.bw_file = new File(Main.save_path + ".png");
				ImageFunctions.Save(black_white, Main.bw_file);
				ImageFunctions.Display(Main.bw_file, "Original B/W");
				
				
				BufferedImage key_image = new BufferedImage(
				        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
				        BufferedImage.TYPE_BYTE_BINARY);
				
				
				Random rand = new Random();
				try {
					SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
					
					for(int i = 0; i < key_image.getHeight(); i++){
						for(int j = 0; j < key_image.getWidth(); j++){
							
							int result = secureRandomGenerator.nextInt(100);
							if(result < 50){
								key_image.setRGB(j, i, WHITE);
							}
							else{
								key_image.setRGB(j, i, BLACK);
							}
						}
					}
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
									
				ImageFunctions.Display_Image(key_image, "Key");
				ImageFunctions.Save(key_image, Main.key_file);
				
			
				BufferedImage magnified_key_image = ImageFunctions.Magnify(key_image);
				ImageFunctions.Save(magnified_key_image, Main.key_magnified_file);
				ImageFunctions.Display_Image(magnified_key_image, "Magnified key");
				
				
				Main.cipher_image = ImageFunctions.Create_Cipher(black_white, key_image);
				BufferedImage magnified_cipher_image = ImageFunctions.Magnify(Main.cipher_image);
				ImageFunctions.Save(magnified_cipher_image, Main.cipher_magnified_file);
				ImageFunctions.Display_Image(magnified_cipher_image, "Magnified Cipher");
				
				if (chckbxIncludePrintFriendly.isSelected()) {
										
					BufferedImage print_ready_test = ImageFunctions.make_print_friendly(black_white);
					ImageFunctions.Display_Image(print_ready_test, "Print Ready");
					
					BufferedImage print_ready_key = ImageFunctions.make_print_friendly(magnified_key_image);
					String print_ready_key_path = Main.save_path + "_key_print_ready.png";
					File print_ready_key_file = new File(print_ready_key_path);
					ImageFunctions.Save(print_ready_key, print_ready_key_file);
					
					BufferedImage print_ready_cipher = ImageFunctions.make_print_friendly(magnified_cipher_image);
					String print_ready_cipher_path = Main.save_path + "_cipher_print_ready.png";
					File print_ready_cipher_file = new File(print_ready_cipher_path);
					ImageFunctions.Save(print_ready_cipher, print_ready_cipher_file);
					}
			}
		});
		btnEncrypt.setBounds(12, 255, 97, 25);
		contentPane.add(btnEncrypt);
		
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(121, 255, 79, 25);
		contentPane.add(btnClear);
		

		

		

		
		
		
	}
}
