import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;




public class MouseInput implements MouseListener{

	static ArrayList<Gamer> viewArray = new ArrayList<Gamer>();
    static ArrayList<Gamer> gamerArray = new ArrayList<Gamer>();
	static int ix;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    public void Write(String path ,String name , int score) throws IOException
    {	
    	String sayi2 = Integer.toString(score);
        PrintWriter out = new PrintWriter(new FileWriter(path, true));
        out.append(name + "  " + sayi2 + "\n");
        out.close();
    }
	public void Control(){ 
	int passwordC;
	boolean foundx= false;
	String nameC = JOptionPane.showInputDialog("Enter name\n");
	String xd = JOptionPane.showInputDialog("Enter Password\n");
	passwordC = Integer.parseInt(xd);
	
		for (int i=0; i<viewArray.size(); i++) {
			if(viewArray.get(i) instanceof Gamer) {	
				Gamer bum = (Gamer) viewArray.get(i);
				if( bum.getPassword()==passwordC && bum.name.equals(nameC)) {
					foundx= true;
					ix=i;
					
					View.State=View.STATE.GAME;
					View.count1=0;
					View.count2=0;
					View.count3=0;
					View.count4=0;
					View.checkpoint1x=800;
					View.checkpoint2x=1400;
					View.checkpoint3x=2000;
					View.checkpoint4x=2400;
					View.checkpoint1=800;
					View.checkpoint2=1400;
					View.checkpoint3=2000;
					View.checkpoint4=2400;
				}
				
			}
		}
		if(!foundx) {
			JOptionPane.showMessageDialog(null, "Wrong id or password try again.","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX();
		int my = e.getY();
		//public Rectangle playButton= new Rectangle(View.WIDTH/2+360,250,100,50);
		//public Rectangle loginButton= new Rectangle(View.WIDTH/2+360,350,100,50);
		//public Rectangle quitButton= new Rectangle(View.WIDTH/2+360,450,100,50);
		//public Rectangle highButton= new Rectangle(View.WIDTH/2+360,150,100,50);
		
		
		//play 
		if(mx>= View.WIDTH/2+360 && mx <= View.WIDTH/2+460) {
			if(my>=250&& my<= 300) {
				Control();
				
							
			}
		}
		//highscore
		if(mx>= View.WIDTH/2+360 && mx <= View.WIDTH/2+460) {
			if(my>=175&& my<= 215) {
				for (int i=0; i<viewArray.size(); i++) {
					if(viewArray.get(i) instanceof Gamer) {	
						Gamer bum = (Gamer) viewArray.get(i);
				JOptionPane.showMessageDialog(null, bum.name+"  "+bum.score1);
				try {
					Write("scores.txt",bum.name,bum.score1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					}
				}
			}
			
		}
		//quit
				if(mx>= View.WIDTH/2+360 && mx <= View.WIDTH/2+460) {
					if(my>=450&& my<= 500) {
						System.exit(1);
					}
				}
				if(mx>= View.WIDTH/2+740 && mx <= View.WIDTH/2+790) {
					if(my>=0&& my<= 100) {
						System.exit(1);
						
					}}
				
		//login
				if(mx>= View.WIDTH/2+360 && mx <= View.WIDTH/2+460) {
					if(my>=350&& my<= 400) {
						try {
							viewArray.add(new Gamer());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
		//restart 
				
				if(mx>= View.WIDTH/2+660 && mx <= View.WIDTH/2+760) {
					if(my>=0&& my<= 100) {
						View.State=View.STATE.RESTART;
					}}
		
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
