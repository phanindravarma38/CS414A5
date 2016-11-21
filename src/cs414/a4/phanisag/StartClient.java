package cs414.a4.phanisag;

import java.rmi.RMISecurityManager;

import cs414.a4.phanisag.gui.InUserGUI;
import cs414.a4.phanisag.gui.OutUserGUI;
import cs414.a4.phanisag.utils.MyRMISecurityManager;

public class StartClient {

	public static RMISecurityManager securityManager;

	public static void main(String[] args) throws ClassNotFoundException {
		securityManager = new MyRMISecurityManager();
		System.setSecurityManager(securityManager);

		ClassLoader.getSystemClassLoader().loadClass("cs414.a4.phanisag.gui.InUserGUI");
		ClassLoader.getSystemClassLoader().loadClass("cs414.a4.phanisag.gui.OutUserGUI");
		
		new InUserGUI();
		new OutUserGUI();
		
		InUserGUI.main(args);

		InUserGUI.main(args);

		OutUserGUI.main(args);

		OutUserGUI.main(args);
	}
}
