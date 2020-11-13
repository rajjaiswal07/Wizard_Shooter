import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean isRunning=false;
	private Thread thread;

	public Game() {
		
		new Window(1000, 563, "Wizard Game", this);
		start();
	}
	
	private void start() {
		
		isRunning=true;
		thread=new Thread(this);
		thread.start();
		
	}
	
	private void stop() {
		
		isRunning=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		this.requestFocus();
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		long timer=System.currentTimeMillis();
		int frames=0;
		while(isRunning) {
			long now=System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime=now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames =0;
			}
		}
		stop();
		
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		//////////// Draw of Game ///////////////////
		g.setColor(Color.red);
		g.fillRect(0, 0, 1000, 563);
		//////////////////////////////
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
