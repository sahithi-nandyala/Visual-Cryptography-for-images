
package justin_and_joel;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.io.File;

public class DecryptPage extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptPage frame = new DecryptPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public DecryptPage() {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{49, 56, 56, 56, 56, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		JLabel lblDecrypt = new JLabel("Decryption");
		lblDecrypt.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDecrypt.setBorder(loweredetched);
		lblDecrypt.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDecrypt = new GridBagConstraints();
		gbc_lblDecrypt.fill = GridBagConstraints.BOTH;
		gbc_lblDecrypt.insets = new Insets(0, 0, 5, 0);
		gbc_lblDecrypt.gridx = 0;
		gbc_lblDecrypt.gridy = 0;
		contentPane.add(lblDecrypt, gbc_lblDecrypt);
		
		
		JButton btnImage1 = new JButton("Image 1");
		GridBagConstraints gbc_btnImage1 = new GridBagConstraints();
		gbc_btnImage1.fill = GridBagConstraints.BOTH;
		gbc_btnImage1.insets = new Insets(0, 50, 5, 50);
		gbc_btnImage1.gridx = 0;
		gbc_btnImage1.gridy = 1;
		contentPane.add(btnImage1, gbc_btnImage1);
		btnImage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Main.image1_path = ImageFunctions.GetPathName();
				
			
				try{
					Main.image1_file = new File(Main.image1_path);
				} catch (NullPointerException e) {
					System.out.println("An invalid file path was returned");
					return;
				}
				
				
				Main.image1 = ImageFunctions.Display(Main.image1_file, "Image 1");

			}
		});
		
		
		JButton btnImage2 = new JButton("Image 2");
		GridBagConstraints gbc_btnImage2 = new GridBagConstraints();
		gbc_btnImage2.fill = GridBagConstraints.BOTH;
		gbc_btnImage2.insets = new Insets(0, 50, 5, 50);
		gbc_btnImage2.gridx = 0;
		gbc_btnImage2.gridy = 2;
		contentPane.add(btnImage2, gbc_btnImage2);
		btnImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Main.image2_path = ImageFunctions.GetPathName();
				
			
				try{
					Main.image2_file = new File(Main.image2_path);
				} catch (NullPointerException e) {
					System.out.println("An invalid file path was returned");
					return;
				}
				
				
				Main.image2 = ImageFunctions.Display(Main.image2_file, "Image 2");
			}
		});
		
		
		JButton btnSaveImage = new JButton("Save Image");
		GridBagConstraints gbc_btnSaveImage = new GridBagConstraints();
		gbc_btnSaveImage.fill = GridBagConstraints.BOTH;
		gbc_btnSaveImage.insets = new Insets(0, 50, 5, 50);
		gbc_btnSaveImage.gridx = 0;
		gbc_btnSaveImage.gridy = 3;
		contentPane.add(btnSaveImage, gbc_btnSaveImage);
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Main.image_decrypt_path = ImageFunctions.GetPathName();
				Main.normal_size_decrypted_path = Main.image_decrypt_path;
				
				
				Main.image_decrypt_path += ".png";
				Main.normal_size_decrypted_path += "_normal_size.png";
				
			
				try{
					Main.image_decrypt_file = new File(Main.image_decrypt_path);
					Main.normal_size_decrypted_file = new File(Main.normal_size_decrypted_path);
				} catch (NullPointerException e) {
					System.out.println("An invalid file path was returned");
					return;
				}
				
			}
		});
		
	
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Main.decrypt_image = ImageFunctions.Decrypt(Main.image1, Main.image2);
				ImageFunctions.Display_Image(Main.decrypt_image, "Decrypted Image");
				
			
				ImageFunctions.Save(Main.decrypt_image, Main.image_decrypt_file);

				
				Main.normal_size_decrypted_image = ImageFunctions.Shrink(Main.decrypt_image);
				ImageFunctions.Display_Image(Main.normal_size_decrypted_image, "Regular Sized Decrypted Image");				
				ImageFunctions.Save(Main.normal_size_decrypted_image, Main.normal_size_decrypted_file);
			}
		});
		GridBagConstraints gbc_btnDecrypt = new GridBagConstraints();
		gbc_btnDecrypt.fill = GridBagConstraints.BOTH;
		gbc_btnDecrypt.insets = new Insets(0, 50, 0, 50);
		gbc_btnDecrypt.gridx = 0;
		gbc_btnDecrypt.gridy = 4;
		contentPane.add(btnDecrypt, gbc_btnDecrypt);
	}
}
