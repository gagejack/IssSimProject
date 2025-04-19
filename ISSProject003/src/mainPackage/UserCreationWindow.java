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

public class UserCreationWindow {
	
	SimWindow Sim = new SimWindow();
	SelectionWindow Select = new SelectionWindow();
	LoginUserWindow Login= new LoginUserWindow();

	private JFrame createFrame;
	private JLayeredPane createLayeredPane;
	
	private static final int CREATE_WIDTH = 250;
    private static final int CREATE_HEIGHT = 200;
    
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 200;
	
	private JLabel userLabel;
	private JTextField userText;
	
	private JLabel passLabel;
	private JTextField passText;
	
	private JButton createUserButton = new JButton();
	private JButton backButton = new JButton();
	private JPanel panel = new JPanel();
	
	public void openCreateWindow() throws IOException { 

		createFrame = new JFrame();
		initFrame(CREATE_WIDTH, CREATE_HEIGHT, "Create User Window");
		initLayeredPane(CREATE_WIDTH, CREATE_HEIGHT);
		Sim.displayBG(createLayeredPane, CREATE_WIDTH, CREATE_HEIGHT);
		initLayout();
		setupButtonListeners();
		
		
		}
	
	private void initLayeredPane(int width, int height) {
		createLayeredPane = new JLayeredPane();
		createLayeredPane.setBounds(0, 0, width, height);
		createFrame.setContentPane(createLayeredPane);
		createFrame.setResizable(false);
		createFrame.setVisible(true);		
	}

	private void initFrame(int width, int height, String title) {
		createFrame = new JFrame(title);
		createFrame.setSize(width,height);
		createFrame.setLocationRelativeTo(null);
		createFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	
	private void initLayout() {
		panel = new JPanel(new GridBagLayout());
		
		createUserButton = new JButton("Create");
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
	    panel.add(createUserButton, gbc);

	    // Back Button
	    gbc.gridx = 1;
	    panel.add(backButton, gbc);
		
		panel.setOpaque(false);
		panel.setBounds((CREATE_WIDTH - PANEL_WIDTH) / 2, (CREATE_HEIGHT - PANEL_HEIGHT) / 2, PANEL_WIDTH, PANEL_HEIGHT);
		createLayeredPane.add(panel, Integer.valueOf(2));

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
				
				if(o == createUserButton) {
					System.out.println("Create Button");
					String user = userText.getText();
	                String pass = new String(passText.getText());
	                UserInfo userInfo = new UserInfo();
	                if(userInfo.passwordValidate(pass) && userInfo.usernameValidate(user)) {
	                    JOptionPane.showMessageDialog(createFrame, "User " + user + " created successfully!");
	    				userInfo.saveUser(user, pass);
	    				createFrame.dispose();
						try {
							Login.openLoginWindow();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	    				
	                } else if(userInfo.usernameValidate(user) == false) {
	                    JOptionPane.showMessageDialog(createFrame, "Username is already taken");
	                } else if(userInfo.passwordValidate(pass) == false) {
	                    JOptionPane.showMessageDialog(createFrame, "Password must contain a digit, an uppercase, and be 9 characters long");
	                }
	               
	                
				} else if(o == backButton) {
					createFrame.dispose();
					Select.openSelectionWindow();
				}
			}
		};
		
		createUserButton.addActionListener(buttonListener);
		backButton.addActionListener(buttonListener);
	}
}
