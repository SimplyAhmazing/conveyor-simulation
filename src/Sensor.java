import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Sensor {

	private int x;
	private int y;
	private int height;
	private int width;
	private int gap;
	private int x_mid[];
	private int numSensors;
	private Color color;
	
	public Sensor(){
		x= 0;
		y = 0;
		height = 10;
		width = 10;
		gap = 10;
		numSensors = 3;
		x_mid = new int[3];
		color = Color.GREEN;
	}
	
	public Sensor(int x, int y, int width, int height, int gap, int numSensors, Color color){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.gap = gap;
		this.color = color;
		this.numSensors = numSensors;
		x_mid = new int[numSensors];
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(color);
		//loop-draw sensor objects
		for(int i = 0; i < numSensors; i++){
			g2d.fill(new Rectangle2D.Double((width+gap)*i + x,y,width,height));
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getGap() {
		return gap;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public int[] getX_mid() {
		return x_mid;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
