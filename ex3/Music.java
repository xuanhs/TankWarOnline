package ex3;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;

public class Music {
	private Toolkit tool = Toolkit.getDefaultToolkit();
	File m=new File("D:\\copy2\\img\\blast.wav");//移动
	File f=new File("D:\\copy2\\img\\fire.wav");//开火
	File h=new File("D:\\copy2\\img\\hit.wav");//打击
	File s=new File("D:\\copy2\\img\\start.wav");//开始音乐
	File a=new File("D:\\copy2\\img\\add.wav");//复活
	File bom=new File("D:\\copy2\\img\\boom.wav");
	AudioClip boom=null;
	AudioClip move=null;
	AudioClip fire=null;
	AudioClip hit=null;
	AudioClip start=null;
	AudioClip add=null;
	public Music(){
		
		try {
			move=Applet.newAudioClip(m.toURL());
			fire=Applet.newAudioClip(f.toURL());
			hit=Applet.newAudioClip(h.toURL());
			start=Applet.newAudioClip(s.toURL());
			add=Applet.newAudioClip(a.toURL());
			boom=Applet.newAudioClip(bom.toURL());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void ranboom(){
		boom.play();
	}
	public void ranmove(){
		move.play();
	
	}
	public void ranfire(){
		fire.play();
		
	}
	public void ranhit(){
		hit.play();
		
	}
	public void ranadd(){
		add.play();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rannew (){
		start.play();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String args[]){
//		Music m=new Music();
//	}
}
