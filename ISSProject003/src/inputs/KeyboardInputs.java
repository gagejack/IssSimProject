package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import mainPackage.SimWindow;

public class KeyboardInputs implements KeyListener{

	SimWindow simWindow;
	
	public KeyboardInputs(SimWindow simWindow) {
		this.simWindow = simWindow;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int key = e.getKeyCode();
	    switch(key) {
	    case KeyEvent.VK_W:
	    	System.out.println("W");
	    	break;
	    case KeyEvent.VK_A:
	    	System.out.println("A");
	    	break;
	    case KeyEvent.VK_S:
	    	System.out.println("S");
	    	break;
	    case KeyEvent.VK_D:
	    	System.out.println("D");
	    	break;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
