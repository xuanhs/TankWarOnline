package ex3;

public class TankThread extends Thread{
	boolean s;
	TankFrame tc;
	public TankThread(TankFrame tc,boolean s){
		this.s=s;
		this.tc=tc;
	}
	public void run(){
		while(true){
			if(s)
			tc.repaint();
			try {
				sleep(150);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

