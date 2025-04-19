package mainPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SelectionWindow {

    private SimWindow simObj = new SimWindow();
    
    private static final int SELECT_WIDTH = 300;
    private static final int SELECT_HEIGHT = 250;
    
    private static final int PANEL_WIDTH = 150;
    private static final int PANEL_HEIGHT = 100;

    private JLabel loginTitleLabel;
    private JButton createUserButton;
    private JButton loginButton;
    private JButton cancelButton;

    private JFrame selectFrame;
    private JLayeredPane layeredPane;
    private JPanel buttonPanel;

    public void openSelectionWindow() {
        
    	selectFrame = new JFrame("Selection Window");
    	initFrame(SELECT_WIDTH, SELECT_HEIGHT, "Selection Window");
    	initLayeredPane(PANEL_WIDTH, PANEL_HEIGHT);
    	
        selectFrame.setContentPane(layeredPane);
        simObj.displayBG(layeredPane, SELECT_WIDTH, SELECT_HEIGHT);

        initTitle();
        initLayout();
        layeredPane.add(buttonPanel, Integer.valueOf(2));

        setupButtonListeners();
        selectFrame.setVisible(true);
    }

    private void initTitle() {
    	loginTitleLabel = new JLabel("ISS SIM");
        loginTitleLabel.setForeground(Color.WHITE);
        loginTitleLabel.setBounds(125, 10, 80, 25);
        layeredPane.add(loginTitleLabel, Integer.valueOf(2));
    }
    
    public void initLayeredPane(int width, int height) {
    	layeredPane = new JLayeredPane();
    	layeredPane.setBounds(0, 0, width, height);
    }
    
    public void initFrame(int width, int height, String title) {
    	selectFrame = new JFrame();
    	selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	selectFrame.setSize(width, height);
    	selectFrame.setLocationRelativeTo(null);
    	selectFrame.setResizable(false);
    	selectFrame.setIconImage(simObj.rocketIcon.getImage());
    }
    
    
    private void initLayout() {
    	buttonPanel = new JPanel();
        buttonPanel.setBounds((SELECT_WIDTH - PANEL_WIDTH) / 2, (SELECT_HEIGHT - PANEL_HEIGHT) / 2, PANEL_WIDTH, PANEL_HEIGHT);

    	createUserButton = new JButton("Create User");
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        
        GroupLayout gLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(gLayout);
        buttonPanel.setOpaque(false);

        gLayout.setAutoCreateGaps(true);
        gLayout.setAutoCreateContainerGaps(true);

        gLayout.setHorizontalGroup(
            gLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(createUserButton)
                .addComponent(loginButton)
                .addComponent(cancelButton)
        );

        gLayout.setVerticalGroup(
            gLayout.createSequentialGroup()
                .addComponent(createUserButton)
                .addComponent(loginButton)
                .addComponent(cancelButton)
        );
    }

    private void setupButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                
                if (o == createUserButton) {
                    handleCreateUser();
                } else if (o == loginButton) {
                    handleLoginUser();
                } else if (o == cancelButton) {
                    handleCancel();
                }
            }
        };

        createUserButton.addActionListener(buttonListener);
        loginButton.addActionListener(buttonListener);
        cancelButton.addActionListener(buttonListener);
    }

    private void handleCreateUser() {
        System.out.println("Create Button");
        UserCreationWindow userCreation = new UserCreationWindow();
        try {
            userCreation.openCreateWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectFrame.dispose();
    }

    private void handleLoginUser() {
        System.out.println("Login Button");
        SimWindow sim = new SimWindow(); // FOR TESTING PURPOSES TO BYPASS LOGIN
//        try {
//			sim.openSimWindow();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        LoginUserWindow login = new LoginUserWindow();
        try {
            login.openLoginWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
        selectFrame.dispose();
    }

    private void handleCancel() {
        System.out.println("Cancel Button");
        selectFrame.dispose();
    }
}

