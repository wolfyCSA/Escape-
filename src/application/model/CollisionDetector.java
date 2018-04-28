package application.model;

import application.Main;

/**
 * CollisionDetector class controls and updates the Collison thread
 * 
 * @author IceKold736, caseycannon423, indomichael, wolfyCSA, Mpoznecki
 *
 */
public class CollisionDetector implements Runnable {
	public Thread collisionDetector;
	public boolean running = false;
	
	public CollisionDetector() {}
	
	//THREAD STARTS IN MAINMENUCONTROLLER
	public synchronized void start(){
		if (running)
			return;
		running = true;
		collisionDetector = new Thread(this);
		collisionDetector.setDaemon(true);
		collisionDetector.start();
	}
	
	public synchronized void stop(){
		if (!running)
			return;
		running = false;
		try {
			collisionDetector.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * runs the collison thread
	 */
	public void run() {
		
		while (running)
		{
			for (EnemyShip e : Main.enemies.toArray(new EnemyShip[Main.playerBullets.size()])) {
				e.update1();
				//System.out.println("move");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		stop();
	}

}
