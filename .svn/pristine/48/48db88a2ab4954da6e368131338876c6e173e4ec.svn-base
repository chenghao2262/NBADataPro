package presentation.common;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public abstract class RandomShapedButton extends JLabel implements MouseListener, MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean mouseIn = false;
	
	public RandomShapedButton(){
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public abstract void mouseMoveIn();
	
	public abstract void mouseMoveOut();
	
	public abstract void mouseClicked();
	
	
	private boolean isMovedIn(int x, int y){
		ImageIcon icon = (ImageIcon) this.getIcon();
		Image img = icon.getImage();
//		System.out.println("icon is null? "+(icon == null));
//        System.out.println(img.getWidth(null)+" "+img.getHeight(null));
		
		BufferedImage bufImg = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_INT_ARGB);   
//			bufImg = ImageIO.read(new File("return out.png"));
		Graphics g = bufImg .createGraphics();   
        g.drawImage(img, 0, 0, null);   
        g.dispose();
        
        
        int rgb = bufImg.getRGB(x, y);
        int temp = (rgb >> 24) & 0xff;
        
        int R = (rgb >> 16) & 0x00ff;
        int G = (rgb >> 8) & 0x0000ff;
        int B = (rgb << 24) & 0xff;
        
//        System.out.println("A: "+ temp +" R: "+R+" G: "+G+" B: "+B);
        
        if(temp == 0){
//        	System.out.println("閫忔槑鐨� "+x+" "+y);
        	return false;
        }else{
//        	System.out.println("涓嶉�忔槑鐨�"+x+" "+y+"||"+temp);
        	return true;
        }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		mouseIn = isMovedIn(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mouseIn){
			mouseClicked();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(mouseIn){
			mouseMoveIn();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(mouseIn){
			mouseMoveOut();
		}
	}

}
