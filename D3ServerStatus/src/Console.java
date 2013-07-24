


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console implements KeyListener{
	private JTextArea output;
	private JTextField input;
	private JScrollPane scrollOutput;
	private JFrame frame;
	
	
	
	public JScrollPane getScrollOutput() {
		return scrollOutput;
	}

	public void setScrollOutput(JScrollPane scrollOutput) {
		this.scrollOutput = scrollOutput;
	}

	public void init(){
		output = new JTextArea(3,1);
		input = new JTextField();
		scrollOutput = new JScrollPane(output);
		scrollOutput
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setFrame(JFrame tFrame) {
		frame = tFrame;
		frame.add(this.createConsole());
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				e.getWindow().setVisible(false);
			}
		});
		//frame.add(this.getScrollOutput());
		
	}
	
	public JTextArea getOutput() {
		return output;
	}

	public void setOutput(JTextArea output) {
		this.output = output;
	}
	
	public JTextField getInput(){
		return input;
	}
	
	public void setInput(JTextField input){
		this.input = input;
	}

	public void cout(String string) {
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
		
		output.append("[" + time.format(new Date()) + "]: " + string + "\n");
		autoScroll();
	}
	
	public String cinText(){
		return input.getText();
	}
	
	public void cinClear(){
		input.setText(null);
	}
	
	public int toggleVisible(){
		if(frame.isVisible()){
			frame.setVisible(false);
			return 0;
		} else {
			frame.setVisible(true);
			return 1;
		}
	}
	
	private void autoScroll() {
		//int max = scrollOutput.getVerticalScrollBar().getMaximum();
		//scrollOutput.getVerticalScrollBar().setValue(max);
		output.setCaretPosition(output.getText().length() - 1);
	}
	
	private Component createConsole() {
		JPanel consoleGUI = new JPanel();
		// console = new Console();
		// console.setOutput(new JTextArea(3, 1));
		JTextArea output = this.getOutput();
		JTextField input = this.getInput();
		JScrollPane scrollOutput = this.getScrollOutput();

		output.setEditable(false);

		input.addKeyListener(this);

		consoleGUI.setLayout(new BorderLayout());
		consoleGUI.add(scrollOutput, BorderLayout.CENTER);
		consoleGUI.add(input, BorderLayout.PAGE_END);

		return consoleGUI;
	}
	
	// BEGIN KeyListener
	public void addKeyListener(KeyListener kLis){
		input.removeKeyListener(this);
		input.addKeyListener(kLis);
		input.addKeyListener(this);
	}
	
	public void removeKeyListener(KeyListener kLis){
		input.removeKeyListener(kLis);
	}
	// END KeyListener

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			String eingabe = this.cinText();
			if (eingabe.length() < 1) return;
			if(eingabe.equals("bla")){
				this.cout("ohhhhaaaaa - was geht ab!!!");
			} else {
				this.cout(eingabe);
			}
			
			this.cinClear();
			
//			if (multiplayer) {
//				client.sendMessage("CHAT§" + com.getClientNR(), this.cinText());
//			} else {
//				c.cout("[" + Nickname[0] + "]: "+ this.cinText());
//			}
//			this.cinClear();
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
	
}
