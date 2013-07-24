import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener{

	getStatus status;
	JPanel serverPanel;
	JCheckBox cSound, cRefresh;
	Console c;
	Statusbar sBar;
	
	public boolean isRefreshCheck(){
		return cRefresh.getModel().isSelected();
	}
	JTextField timerText;
	
	AudioClip alarm = Applet.newAudioClip(this.getClass().getResource("/res/Alarm.wav"));
	
	public GUI(ArrayList<ServerStatus> serverList, getStatus tStatus, Console tC){
		status = tStatus;
		c = tC;
		this.setLayout(new BorderLayout());
		this.setTitle("Diablo III - Server Status");
		
		
		//menu Panel
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(5,1));
		// Refresh button
		JButton bRefresh = new JButton("Refresh");
		bRefresh.setActionCommand("refreshList");
		bRefresh.addActionListener(this);
		menuPanel.add(bRefresh); 
		
		// Sound Checkbox
		cSound = new JCheckBox("Sound");
		menuPanel.add(cSound);
		
		// auto-refresh
		JPanel timerPanel = new JPanel();
		cRefresh = new JCheckBox();
		cRefresh.setActionCommand("arClick");
		cRefresh.addActionListener(this);
		timerText = new JTextField(3);
		timerText.setHorizontalAlignment(JTextField.RIGHT);
		timerText.setText("120");
		JLabel timerLabel = new JLabel("sec");
		
		timerPanel.add(cRefresh);
		timerPanel.add(timerText);
		timerPanel.add(timerLabel);
		
		menuPanel.add(timerPanel);
		
		// Console Button
		JButton bConsole = new JButton("Console");
		bConsole.setActionCommand("console");
		bConsole.addActionListener(this);
		menuPanel.add(bConsole);
		
		this.setSize(932, 360);
		
		boolean showServerList = true;
		
		if (showServerList)
		{
			serverPanel = createServerStatusPanel(serverList);
		}
		
		sBar = new Statusbar("starte...");
		
		
		this.add(menuPanel, BorderLayout.WEST);
		if (showServerList)
		{
			this.add(serverPanel, BorderLayout.CENTER);
		}
		this.add(sBar, BorderLayout.PAGE_END);
		
		
		// optimiert die groesse des Fensters
		this.pack();
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		c.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					String command = c.cinText();
					String tCom = "/sBar ";
					if (command.startsWith(tCom)){
						sBar.setText(command.replaceFirst(tCom, ""));
					}
					tCom = "/refresh";
					if (command.startsWith(tCom)){
						
					}
					tCom = "/changes";
					if (command.startsWith(tCom)){
						c.cout(status.getChangedServerList().toString());
					}
					tCom = "/new";
					if (command.startsWith(tCom)){
						c.cout(status.getServerList().toString());
					}
					tCom = "/old";
					if (command.startsWith(tCom)){
						c.cout(status.getServerListOld().toString());
					}
					tCom = "/sound";
					if (command.startsWith(tCom)){
						alarm.play();
					}
					
				}				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		sBar.clear();
	}

	private JPanel createServerStatusPanel(ArrayList<ServerStatus> serverList){
		JPanel mainPanel = new JPanel();
		String realm = "";
		JPanel panel = null;
		for (ServerStatus server: serverList){
			if(!realm.equals(server.realm)){
				if(panel != null) mainPanel.add(panel);
				realm = server.realm;
				panel = new JPanel(new GridLayout(10,1));
				Label rLabel = new Label(server.realm);
				rLabel.setAlignment(Label.CENTER);
				panel.add(rLabel);
			}
			JButton sButton = new JButton(server.name);
			sButton.setOpaque(true);
			if(server.statusID != 1) sButton.setEnabled(false);
			if(server.changed){
				if(server.statusID != 1){
					sButton.setBackground(Color.RED);
				} else {
					sButton.setBackground(Color.GREEN);
				}
			} else {
				sButton.setBackground(null);
			}
			try {
				panel.add(sButton);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		// letzte Panel muss  hinzugefuegt werden
		if(panel != null) mainPanel.add(panel);
		
		return mainPanel;
	}

	public void actionPerformed(ActionEvent e){
		String action = e.getActionCommand();
		if (action.equals("refreshList")){
			System.out.println("refreshing...");
			refreshGUIServer();
		} else if(action.equals("arClick")){
			System.out.println("arClick");
			if(cRefresh.getModel().isSelected()){
				timerText.setEditable(false);
				startTimer();
			} else {
				timerText.setEditable(true);
			}
			
		} else if(action.equals("console")){
			c.toggleVisible();
		}
	}
	
	public void startTimer(){
		Integer defaultTime = 120;
		Timer timer = new Timer();
		String timeString = timerText.getText();
		int time = 5;
		try {
			time = Integer.parseInt(timeString);
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this,
		    "Bitte eine vern���������nftige Zahl angeben!");
			timerText.setEditable(true);
			cRefresh.setSelected(false);
			timerText.setText(defaultTime.toString());
			return;
		}
		if(time < 5){
			JOptionPane.showMessageDialog(this,
		    "Bitte mehr als 5 sekunden angeben!");
			timerText.setEditable(true);
			cRefresh.setSelected(false);
			timerText.setText(defaultTime.toString());
			return;
		}
		
		time *= 1000;
		timer.schedule(new RefreshTask(this), time);
	}
	
	public void refreshGUIServer(){
		sBar.setText("refreshing...");
		remove(serverPanel);			
		
		serverPanel = createServerStatusPanel(status.refresh());
		playSound();
		sBar.setText("DONE");
		
		add(serverPanel);
		
		validate();
		
		//sBar.clear();
	}
	
	private void playSound(){
		if (cSound.getModel().isSelected()) {
			if(status.getChangedServerList().size() > 0)
			alarm.play();
		}
	}
	
	
}
