import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class ConveyorMachine extends JFrame{
	
	private Conveyor conveyor; //conveyor manager class/system
	
	private JPanel ctrlPanel = new JPanel();
	private JButton jbtNewPuck = new JButton("New Puck");
	private JButton jbtClrPuck = new JButton("Clear Pucks");
	private JButton jbtIncrementConveyorSpeed = new JButton("Increase Speed");
	private JButton jbtDecrementConveyorSpeed = new JButton("Decrease Speed");
	ConveyorMachine(){
		conveyor = new Conveyor(); //initialize conveyor system
		
		ctrlPanel.setLayout(new GridLayout(2,2));
		ctrlPanel.add(jbtNewPuck);
		ctrlPanel.add(jbtClrPuck);
		ctrlPanel.add(jbtIncrementConveyorSpeed);
		ctrlPanel.add(jbtDecrementConveyorSpeed);
		//ctrlPanel.add(jbtReverseDirection);
		add(conveyor, BorderLayout.CENTER);
		add(ctrlPanel,BorderLayout.SOUTH);
		
		//attach buttons
		jbtIncrementConveyorSpeed.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conveyor.incrementSpeed();		
			}
		});
		
		jbtDecrementConveyorSpeed.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conveyor.decrementSpeed();		
			}	
		});
		
		jbtNewPuck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conveyor.addPuck();				
			}
		});
		
		jbtClrPuck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				conveyor.clearPucks();				
			}
		});
	}


	
	public static void main(String[] args) {
		JFrame frame = new ConveyorMachine();
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
