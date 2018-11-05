package utils.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Graphifier {
	public double scalex;
	public double scaley;
	public BufferedImage img;
	private ArrayList<Point> points;
	public Point max;
	public BufferedImage bi;
	
	public Graphifier(Point max) { // max is the largest value on the graph
		try {
			bi = ImageIO.read(new File("ColorGraph.png"));
		} catch (IOException e) {e.printStackTrace();}
		this.max = max;
		img = new BufferedImage(1050, 550, BufferedImage.TYPE_INT_RGB);
		scalex = 1000/max.x;
		scaley = 500/max.y;
		points = new ArrayList<Point>();
	}
	
	public void addPoint(Point p) {
		points.add(new Point((int)(p.x*scalex), (int) (p.y*scaley)));
	}
	
	public Color getColor(int x) {
		x/=1.99;
		return new Color(bi.getRGB(x, 2));
	}
	
	public BufferedImage draw() { // fontsize 13 for 50x50 square, 3.5 chars only
		Graphics g = img.getGraphics();
		g.translate(0, img.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(40, -img.getHeight()+50, 10, img.getHeight()-100);
		int counter = 0;
		for(int x = 50; x <= img.getHeight()-50; x+=100) {
			g.fillRect(30, -x, 10, 5);
			g.drawString(((max.x/5)*counter++)+"", 0, -x);
		}
		g.fillRect(50, -50, img.getWidth()-100, 10);
		counter = 0;
		for(int x = 50; x < img.getWidth()+200; x+= 100) {
			g.fillRect(x, -40, 5, 10);
			g.drawString((max.y/5*counter++)+"", x, -20);
		}
		g.translate(50, -50);
		g.setColor(Color.BLUE);
		for(int x = 1; x < points.size(); x++) {
			Point p1 = points.get(x-1);
			Point p2 = points.get(x);
			Linier l = new Linier(p1,p2);
			for(int x1 = p1.x; x1 <= p2.x; x1++) {
				g.setColor(getColor(x1));
				if(x1+1 < p2.x)
					g.drawLine(x1, -l.solve(x1), x1+1, -l.solve(x1+1));
				//g.drawRect(x1, -l.solve(x1), 2, 2);
			}
		}
		return img;
	}
}
class Linier {
	public double m;
	public double b;
	public Linier(Point p1, Point p2) {
		m = ((double)(p2.y-p1.y))/((double)(p2.x-p1.x));
		b = (-m*((double)p1.x))+p1.y;
	}
	
	public int solve(int x) {
		return (int) (m*x+b);
	}
}

