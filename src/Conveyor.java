import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.Timer;

public class Conveyor extends JPanel implements ActionListener,
		MouseMotionListener {

	private Timer timer;

	private int conv_x = 0; //conveyor starting x
	private int conv_y = 200; //conveyor starting y
	private int conv_wid = 200;
	//private int pos_x = 30; //x coord of conveyor belt bar
	//private int pos_y = 200; //y coord of conveyor belt bar
	private int conveyorBarWid = 15; //width of conveyor belt bar
	private int conveyorBarLen = 70; //length of conveyor belt bar
	private int conveyorBarGap = 30;  //gap between conveyor belt bar start to next bar start
	
	//Sensors
	private int sensorX = 0;
	private int sensorY = 100;
	private int numSensors = 3;
	private int sensorGap = 90;
	private int sensorWidth = 30;
	private int sensorHeight = 45;


	private int numBars;
	private int gRailHeight = 10; // guide rail height
	private int dx = 5; //change in conveyor belt position (counts as pixel off-set in the beginning)
	private int speed = 0; // speed of rails (conveyor) for movement
	private Color gRailsColor = Color.red; // guide rails colors for conveyor
	private Color barsColor = Color.black; // conveyor bars color

	ArrayList<Puck> pucks = new ArrayList<Puck>();
	//ArrayList<Sensor> sensors = new ArrayList<Sensor>;

	public Conveyor() {

		timer = new Timer(20, this); //to animate conveyor motions
		addMouseMotionListener(this); //for dragging pucks
		//pucks.add(new Puck());
		//pucks.add(new Puck(5, 5, 20));
		timer.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		conv_wid = getWidth(); //make conveyor span entire screen
		sensorX = (int) (getWidth()/2 - (numSensors*(sensorWidth+sensorGap))/2 + sensorWidth*1.5); //center sensors on the screen
	
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		//drawConveyor(g2d,0,pos_y,getWidth(),gRailHeight); //draw conveyor at specified location
		drawConveyor(g2d,conv_x,conv_y,conv_wid,gRailHeight); //draw conveyor at specified location
		

		Sensor proxSensor = new Sensor(sensorX,sensorY,sensorWidth,sensorHeight,sensorGap,numSensors,Color.GREEN);
		Sensor photoSensor = new Sensor(getWidth()-90,275,30,30,0,1,Color.BLUE);
		photoSensor.paint(g);
		proxSensor.paint(g);
		
		//draw all pucks
		for (Puck puck : pucks) {
			g2d.setColor(puck.getColor());
			g2d.fill(new Ellipse2D.Double(puck.getPuck_x(), puck.getPuck_y(),2 * puck.getPuck_radius(), 2 * puck.getPuck_radius()));
			//g2d.drawLine(puck.getPuck_x(), puck.getPuck_y(), puck.getPuck_x(), puck.getPuck_y()+2*puck.getPuck_radius());
		}

	}
	


	public void drawConveyor(Graphics2D g2d, int orig_x, int orig_y, int areaWidth, int areaHeight) {
		numBars = (areaWidth/(conveyorBarGap - conveyorBarWid))/2 + 1;
		g2d.setColor(gRailsColor);
		g2d.fillRect(orig_x, (int) (orig_y + ((double) conveyorBarLen * .10)), areaWidth,areaHeight);
		g2d.fillRect(orig_x, (int) (orig_y + ((double) conveyorBarLen * .80)), areaWidth,areaHeight);

		g2d.setColor(barsColor);
		for (int i = 0; i < numBars; i++) {
			g2d.fillRect((dx) + conveyorBarGap*i + orig_x, conv_y, conveyorBarWid, conveyorBarLen);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if bar reaches next bar, reset bar positin to create animation effect
		if (Math.abs(dx) > (conveyorBarGap-conveyorBarWid)*1.33) {
			dx = 0;
		} else {
			dx += speed;
		}
		
		//if puck is on conveyer, increment puck x coord at speed of conveyor
		Rectangle2D conveyorArea = new Rectangle2D.Double(conv_x, conv_y,conv_wid,conveyorBarLen);
		for(Puck p : pucks){
			
			if(
			conveyorArea.intersects(new Rectangle2D.Double(p.getPuck_x(),
					p.getPuck_y(),
					2*p.getPuck_radius(),
					2*p.getPuck_radius()))
					&&
					//don't allow puck to leave screen
					p.getPuck_x()+2*p.getPuck_radius() <= getWidth()
					&&
					p.getPuck_x() >= 0
					){
				p.setPuck_x(p.getPuck_x() + speed);
			}
			
		}
		
		//Detect if pucks move under the sensors
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		for( Puck p : pucks){
			float diameter = p.getPuck_radius()*2;
			float mdx = e.getX()- p.getPuck_x(); 
			float mdy = e.getX()- p.getPuck_x();
			if( (mdx < diameter) && (mdy < diameter) &&(mdx > 0) && (mdy > 0)){
			//	System.out.println("mdx: " + (e.getX() - p.getPuck_x() + " 2R: " + diameter));
			//	System.out.println("mdy: " + (e.getY() - p.getPuck_y() + " 2R: " + diameter)); 
				p.setPuck_x(e.getX()-p.getPuck_radius());
				p.setPuck_y(e.getY()-p.getPuck_radius());
				repaint();
				break; // only one mouse to move one puck, no more pucks can possibly be moved..
			}
		}
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void incrementSpeed(){
		speed += 1;
	}
	public void decrementSpeed(){
		speed -= 1;
	}
	
	public void clearPucks(){
		pucks.clear();
	}
	public void addPuck(){
		pucks.add(new Puck());
	}

}
