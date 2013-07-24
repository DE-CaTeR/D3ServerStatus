import java.util.TimerTask;


public class RefreshTask extends TimerTask {
	GUI gui;
	
	public RefreshTask(GUI tGui) {
		// TODO Auto-generated constructor stub
		gui = tGui;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(!gui.isRefreshCheck()) return;
		gui.refreshGUIServer();
		gui.startTimer();
		
	}
}
