package graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.App;
import main.Init;

public class FirstPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6462374124454369550L;
	private JTextField jcomp1;
    private JLabel jcomp2;
    private JButton jcomp3;

    public FirstPanel() {
        //construct components
        jcomp1 = new JTextField (5);
        jcomp2 = new JLabel ("Enter bot token");
        jcomp3 = new JButton ("start up!");

        //adjust size and set layout
        setPreferredSize (new Dimension (192, 59));
        setLayout (null);
        //Add Listeners
        jcomp3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					App.consolPanel();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        Init.Initialize(jcomp1.getText());
			}
        });
        
        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (0, 0, 195, 35);
        jcomp2.setBounds (0, 35, 95, 25);
        jcomp3.setBounds (95, 35, 100, 25);
    }
    
}