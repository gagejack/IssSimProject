package mainPackage;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUserWindow {
	
	SimWindow simObj = new SimWindow();
	SelectionWindow select = new SelectionWindow();

	private JFrame loginFrame;
	private JLayeredPane loginLayeredPane;
	
	private static final int LOGIN_WIDTH = 250;
    private static final int LOGIN_HEIGHT = 200;
    
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 200;
    

	
	private JLabel userLabel;
	private JTextField userText;
	
	private JLabel passLabel;
	private JTextField passText;
	
	private JButton loginUserButton;
	JButton backButton;
	private JPanel panel;
	
	
	public void openLoginWindow() throws IOException {
		
		loginFrame = new JFrame();
		initFrame(LOGIN_WIDTH, LOGIN_HEIGHT, "Login Window");
		initLayeredPane(LOGIN_WIDTH, LOGIN_HEIGHT);
		simObj.displayBG(loginLayeredPane, LOGIN_WIDTH, LOGIN_HEIGHT);
		initLayout();
		setupButtonListeners();

		}
	
	
	private void initLayout() {
		panel = new JPanel(new GridBagLayout());
		
		loginUserButton = new JButton("Login");
		backButton = new JButton("Back");
		
		setupUserStuff();
		setupPassStuff();
		    
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5); // Padding
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Layout for username
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 0.3; // Allows resizing
	    panel.add(userLabel, gbc);

	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.weightx = 0.7; // Allows text field to expand
	    gbc.gridwidth = 2; // Text field spans more columns
	    panel.add(userText, gbc);

	    // Layout for password
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.weightx = 0.3;
	    panel.add(passLabel, gbc);

	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    gbc.weightx = 0.7;
	    gbc.gridwidth = 2;
	    panel.add(passText, gbc);

	    // Login Button
	    gbc.gridwidth = 1;
	    gbc.weightx = 0.5;
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    panel.add(loginUserButton, gbc);

	    // Back Button
	    gbc.gridx = 1;
	    panel.add(backButton, gbc);
		
		panel.setOpaque(false);
		panel.setBounds((LOGIN_WIDTH - PANEL_WIDTH) / 2, (LOGIN_HEIGHT - PANEL_HEIGHT) / 2, PANEL_WIDTH, PANEL_HEIGHT);
		loginLayeredPane.add(panel, Integer.valueOf(2));

	}

	private void initLayeredPane(int width, int height) {
		loginLayeredPane = new JLayeredPane();
		loginLayeredPane.setBounds(0, 0, width, height);
		loginFrame.setContentPane(loginLayeredPane);
		loginFrame.setResizable(false);
		loginFrame.setVisible(true);		
	}

	private void initFrame(int width, int height, String title) {
		loginFrame = new JFrame(title);
		loginFrame.setSize(width,height);
		loginFrame.setLocationRelativeTo(null);
	    loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	public void setupUserStuff() {
		userLabel = new JLabel("Username: ");
        userLabel.setForeground(Color.WHITE);
		userText = new JTextField();
	}
	
	public void setupPassStuff() {
		passLabel = new JLabel("Password: ");
        passLabel.setForeground(Color.WHITE);
		passText = new JPasswordField();
	}
	
	public void setupButtonListeners() {
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Object o = ae.getSource();
				
				if(o == loginUserButton) {
					System.out.println("Login Button");
					
					String user = userText.getText();
	                String pass = passText.getText();
	                
	                UserInfo userInfo = new UserInfo();

	              if(userInfo.verifyCreds(user, pass)) {
	                    JOptionPane.showMessageDialog(loginFrame, "User " + user + " logged in successfully!");
                    
	                    try { // Successful login - open main window  
							simObj.openSimWindow();
						} catch (IOException e1) {
							e1.printStackTrace();
						}                 
	                    System.out.println("Opening main window");
	                    loginFrame.dispose();
	                } else {
	                    JOptionPane.showMessageDialog(loginFrame, "User " + user + " failed to login");
	                }
	                
				} else if(o == backButton) {
					loginFrame.dispose();
					select.openSelectionWindow();
				}
			}
		};
		
		loginUserButton.addActionListener(buttonListener);
		backButton.addActionListener(buttonListener);
	}
	
	
}
