import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;


/**
 * @author Carsten
 *
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Console
		Console c = new Console();
		c.init();
		c.cout("starte Konsole ...");
		// Consolen Fenster
		JFrame ConsoleServer = new JFrame("Konsole");
				
		ConsoleServer.setAlwaysOnTop(true);
		ConsoleServer.setSize(500,200);
		c.setFrame(ConsoleServer);
		//c.toggleVisible();
		
		// eigendliches Programm
		
		String url = "http://us.battle.net/d3/en/status";
		//url = "file:///C:/test/test.html";
		
		c.cout("url: " + url);
				
		getStatus status = new getStatus(url, c);
		
		ArrayList<ServerStatus> serverList = status.getServerList();
		System.out.println("test");
		System.out.println(serverList);
		
		GUI gui = new GUI(serverList, status, c);
		


	}



}
