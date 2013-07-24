import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;


public class Statusbar extends JPanel {
	
	// BEGINN Label Faker
	private JLabel label = new JLabel();
	public void setText(String str){
		if (str.length() < 1) str = " ";
		label.setText(str);
	}
	
	public String getText(){
		return label.getText();
	}
	// END Label Faker
	
	// Construtor
	public Statusbar(String string) {
		// TODO Auto-generated constructor stub
		this.init();
		this.setText(string);
	}
	public Statusbar(){
		this.init();
	}
	
	// Text Allignment
	
	public void setAllignLeft(){
		this.removeAll();
		label.setHorizontalAlignment(JLabel.LEFT);
		this.add(label);
		this.add(Box.createHorizontalGlue());
	}
	public void setAllignRight(){
		this.removeAll();
		label.setHorizontalAlignment(JLabel.RIGHT);
		this.add(Box.createHorizontalGlue());
		this.add(label);
	}
	public void setAllignCenter(){
		this.removeAll();
		label.setHorizontalAlignment(JLabel.CENTER);
		this.add(Box.createHorizontalGlue());
		this.add(label);
		this.add(Box.createHorizontalGlue());
	}
	
	// Initialisierung
	private void init(){
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		this.setAllignRight();
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		this.setText("test Statusbar");
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		this.setText("");
		label.validate();
		this.validate();
		
	}
}
