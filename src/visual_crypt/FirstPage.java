
package justin_and_joel;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FirstPage extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage frame = new FirstPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
		
		public FirstPage() {
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			JLabel Title = new JLabel("VisualCryptography");
			Title.setBounds(37, 12, 378, 28);
			contentPane.add(Title);
			
			
			JButton btnEncrypt = new JButton("Encrypt");
			btnEncrypt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					EncryptPage.main(null);
				}
			});
			btnEncrypt.setBounds(100, 110, 240, 25);
			contentPane.add(btnEncrypt);
			
			
			JButton btnDecrypt = new JButton("Decrypt");
			btnDecrypt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DecryptPage.main(null);
				}
			});
			btnDecrypt.setBounds(100, 140, 240, 25);
			contentPane.add(btnDecrypt);
		}
}
