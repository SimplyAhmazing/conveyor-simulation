import java.awt.Color;


public class Puck {
	
	int puck_x;
	int puck_y;
	int puck_radius;
	
	private static Color color;
	
	public Puck(){
		puck_x = 50;
		puck_y = 50;
		puck_radius = 30;
		color = Color.ORANGE;
	}
	
	public Puck(int x, int y, int r){
		puck_x = x;
		puck_y = y;
		puck_radius = r;
		color = Color.ORANGE;
	}

	public int getPuck_x() {
		return puck_x;
	}

	public void setPuck_x(int puck_x) {
		this.puck_x = puck_x;
	}

	public int getPuck_y() {
		return puck_y;
	}

	public void setPuck_y(int puck_y) {
		this.puck_y = puck_y;
	}

	public int getPuck_radius() {
		return puck_radius;
	}

	public void setPuck_radius(int puck_radius) {
		this.puck_radius = puck_radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		Puck.color = color;
	}
	
}
