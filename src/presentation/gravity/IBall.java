package presentation.gravity;

public interface IBall {

	public void changeLocation(int x, int y);
	
	public int getX();
	
	public int getY();
	
	public int getVX();
	
	public int getVY();
	
	public void setVX(int vx);
	
	public void setVY(int vy);
}
