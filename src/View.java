import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;
//import java.util.ArrayList;
import java.util.TimerTask;


@SuppressWarnings("serial")
public class View extends javax.swing.JFrame implements ActionListener,KeyListener,MouseListener {
	int ikiyuz=-200;
	public Rectangle highButton= new Rectangle(View.WIDTH/2+360,150,100,50);
	public Rectangle playButton= new Rectangle(View.WIDTH/2+360,250,100,50);
	public Rectangle loginButton= new Rectangle(View.WIDTH/2+360,350,100,50);
	public Rectangle quitButton= new Rectangle(View.WIDTH/2+360,450,100,50);
	public Rectangle opp1= new Rectangle(View.WIDTH/2+270,150,100,50);
	public Rectangle opp2= new Rectangle(View.WIDTH/2-35,150,100,50);
	public Rectangle opp3= new Rectangle(View.WIDTH/2-350,150,75,125);
	public Rectangle opp4= new Rectangle(View.WIDTH/2+130,150,100,50);
	private BufferStrategy bs;
    private Graphics2D g;
    private BufferedImage background;
    private BufferedImage background2;
    private BufferedImage tree;
    private BufferedImage tree2;
    private BufferedImage motorcycle;
    private BufferedImage motorcycle1;
    private BufferedImage motorcycle2;
    private BufferedImage motorcycle3;
    private BufferedImage motorcycle4;
   // private static ArrayList <Rectangle> opp;
    public Rectangle carrec= new Rectangle(ikiyuz,150,100,50);
    static int score=1;
    int rand4;
    int rand1;
    int rand2;
    int rand3;
    int width=800;
    int height=600;
    static int dur=0;
    int viraj=0;
    static int count1;
    static int count2;
    static int count3;
    static int count4;
    static int track=0;
    int countx=60;
    int ax=0;
	int restart=0;
	
	
	//TIME SAYACI
    Timer timer1 = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	count1++;
        	
        }
      });
    Timer timer2 = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	count2++;
        }
      });
    Timer timer3 = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	count3++;
        }
      });
    Timer timer4 = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	count4++;
        }
      });
    
    public static enum STATE{
    	MENU,
    	GAME,
    	RESTART,
    	PAUSE,
    	OVER
    };
     public static STATE State = STATE.MENU;
    
    public View() {
    	
        initComponents();
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        createBufferStrategy(3);
        bs = getBufferStrategy();
        g = (Graphics2D) bs.getDrawGraphics();
        //opp=new ArrayList <Rectangle>();
        try {
            background=ImageIO.read(new File("background.png"));
            background2=ImageIO.read(new File("background4.png"));
            tree=ImageIO.read(new File("tree.png"));
            tree2=ImageIO.read(new File("tree2.png"));
            motorcycle=ImageIO.read(new File("motor.png"));
            motorcycle1=ImageIO.read(new File("motor.png"));
            motorcycle2=ImageIO.read(new File("motor.png"));
            motorcycle3=ImageIO.read(new File("motor.png"));
            motorcycle4=ImageIO.read(new File("motor.png"));
          }
        catch (Exception e) { }
        
        
        Thread mainLoop = new Thread(new MainLoop());
        mainLoop.start();
        addKeyListener(this);
        this.addMouseListener(new MouseInput());
        }
    	private void initComponents() {
    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        pack();
    }   
    
    
    public static void main(String args[]) {
    	//MUZIK CALMA AMA SIGMIYOR COADSYSE
    	String filepath = "music2.wav";
        musicStuff musicObject = new musicStuff();
    	//musicObject.playMusic(filepath);
    	java.awt.EventQueue.invokeLater(new Runnable(){
           public void run() {
                new View().setVisible(true);
                }
           });
      }



    private class MainLoop implements Runnable {

        @Override
        public void run() {
            while (true) {
                g = (Graphics2D) bs.getDrawGraphics();
                //OYUN BASLAMA
                if(State==STATE.GAME) {
                	update();
                	draw(g);
                	bs.show();
                	g.dispose();
               	
                	}
                //OYUN BITME
                else if (State==STATE.OVER) {
                	drawFinish(g);
               	 	bs.show();
                    g.dispose();
                }
                //MENU EKRANI
                else if(State==STATE.MENU) {
                	
                	drawMenu(g);
                	 bs.show();
                     g.dispose();
                 }
                //OYUNU BASTAN BASLATMA 
                else if (State==STATE.RESTART) {
                	try {
						motorcycle=ImageIO.read(new File("motor.png"));
						 motorcycle1=ImageIO.read(new File("motor.png"));
				           motorcycle2=ImageIO.read(new File("motor.png"));
				           motorcycle3=ImageIO.read(new File("motor.png"));
				           motorcycle4=ImageIO.read(new File("motor.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	score=1;
                	opp1.y=150;
                	opp2.y=150;
                	opp3.y=150;
                	opp4.y=150;
                	checkpoint1=800;
                    checkpoint2=1400;
                    checkpoint3=2000;
                    checkpoint4=2400;
                    track=0;
                    checkpoint1x=800;
                    checkpoint2x=1400;
                    checkpoint3x=2000;
                    checkpoint4x=2400;
                     track1=0;
                     speed=0;
                    a = 0;
                     yolviraj = 0;
                    ha = 0;
                    backgroundx=0;
                    move=20;
                     ikiyuz=-200;
                    value= (int) speed;
                    count1=0;
                    count2=0;
                    count3=0;
                    count4=0;
                    View.State=View.STATE.GAME;
                }
                else if (State==STATE.PAUSE) {
                	dur=1;
                }
            }
        }
    }
    static int checkpoint1=800;
    static int checkpoint2=1400;
    static int checkpoint3=2000;
    static int checkpoint4=2400;

    static double checkpoint1x=300;
    static double checkpoint2x=1400;
    static  double checkpoint3x=2000;
    static  double checkpoint4x=2400;
    double track1;
    double speed=0;
    double a = 0;
    double yolviraj = 0;
    double ha = 0;
    double backgroundx=0;
    int move=20;
   
    int value= (int) speed;
    public void update() {
    	/*{INTERSECT DENEME
    	Area area1 = new Area(carrec.getBounds());
        Area area2 = new Area(opp1.getBounds());
        Area area3 = new Area(opp2.getBounds());
        Area area4 = new Area(opp3.getBounds());
        Area area5 = new Area(opp4.getBounds());
        if (area1.intersects(area2.getBounds2D()) || area1.intersects(area3.getBounds2D()))
        {
        	java.util.Timer ta6 = new  java.util.Timer(); 
            ta6.schedule(new TimerTask()
      		{

					@Override
					public void run() {View.State=View.STATE.RESTART;
						// TODO Auto-generated method stub
						}
      		},1500);
            
            try {
					motorcycle=ImageIO.read(new File("crash.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} }
        else if (area1.intersects(area4.getBounds2D()) || area1.intersects(area5.getBounds2D()))
        {
        	java.util.Timer ta7 = new  java.util.Timer(); 
            ta7.schedule(new TimerTask()
      		{

					@Override
					public void run() {View.State=View.STATE.RESTART;
						// TODO Auto-generated method stub
						}
      		},1500);
            
            try {
					motorcycle=ImageIO.read(new File("crash.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} } }*/
    
        
        
    	
    	int max = 10; 
        int min = 1; 
        int range = max - min + 1; 
         
        
        //OYUNU BITIRME
        if(checkpoint4==0 || (checkpoint1>0 && count1>60) || (checkpoint2>0 && count2>60) || (checkpoint3>0 && count3>60) || (checkpoint4>0 && count4>60)) {
    			if(MouseInput.viewArray.get(MouseInput.ix) instanceof Gamer) {	
    				Gamer bum = (Gamer) MouseInput.viewArray.get(MouseInput.ix);
    				System.out.println(bum.name);
    				System.out.println(bum.password);
    				bum.score1=score;
    				System.out.println(bum.score1);
    			}
        	score=1;
        	opp1.y=150;
        	opp2.y=150;
        	opp3.y=150;
        	opp4.y=150;
        	checkpoint1=300;
            checkpoint2=400;
            checkpoint3=500;
            checkpoint4=600;
            track=0;
            checkpoint1x=300;
            checkpoint2x=400;
            checkpoint3x=500;
            checkpoint4x=600;
             track1=0;
             speed=0;
            a = 0;
             yolviraj = 0;
            ha = 0;
            backgroundx=0;
            move=20;
             ikiyuz=-200;
            value= (int) speed;
            count1=0;
            count2=0;
            count3=0;
            count4=0;
            rand2=0;
            rand3=0;
            rand4=0;
            rand1=0;
            a=0; 
        	View.State=View.STATE.MENU;
        }
      
    	
        a += 0.15d;
        if (a>=1) {
        	
        	//RAKIP ILERI GERI AYARLAMA RANDOM
        	  // generate random numbers within 1 to 10 
            //timer1
              java.util.Timer ta1 = new  java.util.Timer(); 
              ta1.scheduleAtFixedRate(new TimerTask()
      		{
      			public void run() {
      				rand2 = (int)(Math.random() * range) + min;  
      	            if(rand2>=6.1) {
      	        		opp2.y=opp2.y-1;
      	        	}
      	            else{
      	            	opp2.y=opp2.y+1;
      	            	} 
      			}
      		}, 0, 800000);
              
              //timer2
              java.util.Timer ta2 = new  java.util.Timer(); 
              ta2.scheduleAtFixedRate(new TimerTask()
      		{
      			public void run() {
      				rand1 = (int)(Math.random() * range) + min;  
      	            if(rand1>=7) {
      	        		opp1.y=opp1.y-1;
      	        	}
      	            else{
      	            	opp1.y=opp1.y+1;
      	            	} 
      			}
      		}, 0, 800000);
              
          	//timer3
              java.util.Timer ta3 = new  java.util.Timer(); 
              ta3.scheduleAtFixedRate(new TimerTask()
      		{
      			public void run() {
      				rand3 = (int)(Math.random() * range) + min;  
      	            if(rand3>=7) {
      	        		opp3.y=opp3.y-1;
      	        	}
      	            else{
      	            	opp3.y=opp3.y+1;
      	            	} 
      			}
      		}, 0, 800000);
              
              
            //timer4
              java.util.Timer ta4 = new  java.util.Timer(); 
              ta4.scheduleAtFixedRate(new TimerTask()
      		{
      			public void run() {
      				rand4 = (int)(Math.random() * range) + min;  
      	            if(rand4>=7) {
      	        		opp4.y=opp4.y-1;
      	        	}
      	            else{
      	            	opp4.y=opp4.y+1;
      	            	} 
      			}
      		}, 0, 800000);
          	a=0;
          	
          	//CHECKPOINT BASLATMA
            track= (int) track1;
            checkpoint1 = (int) checkpoint1x;
            checkpoint2 = (int) checkpoint2x;
            checkpoint3 = (int) checkpoint3x;
            checkpoint4 = (int) checkpoint4x;
            if(checkpoint1>=0) {
            timer1.start();}
            else if(checkpoint2>=0) {
                timer2.start();}
            else if(checkpoint3>=0) {
                timer3.start();}
            else {
                timer4.start();}
            
            //opp1.y=opp3.y+1;
            //opp2.y=opp3.y+1;
            //opp3.y=opp3.y+1;
            //opp4.y=opp3.y+1;
            
            
            //HIZ AYARLAMA 
            if(speed>250) {
            	score=score+track/2;
            	speed=250;
            	track1=track1+1.4;
            	checkpoint1x=checkpoint1x-1.4;
            	checkpoint2x=checkpoint2x-1.4;
            	checkpoint3x=checkpoint3x-1.4;
            	checkpoint4x=checkpoint4x-1.4;
            }
            else if (speed<40) {
            	score=score+track/4;
            	speed=speed+0.1;
            	track1=track1+0.5;
            	checkpoint1x=checkpoint1x-0.5;
            	checkpoint2x=checkpoint2x-0.5;
            	checkpoint3x=checkpoint3x-0.5;
            	checkpoint4x=checkpoint4x-0.5;
            }
            else if (speed<100) {
            	score=score+track/3;
            	speed=speed+0.3;
            	track1=track1+0.6;
            	checkpoint1x=checkpoint1x-0.6;
            	checkpoint2x=checkpoint2x-0.6;
            	checkpoint3x=checkpoint3x-0.6;
            	checkpoint4x=checkpoint4x-0.6;
            }
            else if (speed>100 && speed<200) {
            	score=score+track/3;
            	speed=speed+0.2;
            	track1=track1+0.8;
            	checkpoint1x=checkpoint1x-0.8;
            	checkpoint2x=checkpoint2x-0.8;
            	checkpoint3x=checkpoint3x-0.8;
            	checkpoint4x=checkpoint4x-0.8;
            }
            else if (speed>200 ) {
            	score=score+track/3;
            	speed=speed+0.1;
            	track1=track1+1.0;
            	checkpoint1x=checkpoint1x-1.0;
            	checkpoint2x=checkpoint2x-1.0;
            	checkpoint3x=checkpoint3x-1.0;
            	checkpoint4x=checkpoint4x-1.0;
            }
        }
        
        backgroundx=backgroundx+(yolviraj * 0.03);
        
        ha = 0.5d;
        if (ha >= 0) {
            ha = 0;
        }
    }
    	
    	//crash
		public void actionPerformed(ActionEvent e) {
		//	Rectangle rect;
		//	for(int i=0; i<opp.size(); i++) {
		//		rect=opp.get(i);
		//		rect.y+=speed;
		//	}
    }
			

		
		
	//BITIS EKRANI
    public void drawFinish(Graphics2D g) {
    	g.drawImage(background2, (int) -backgroundx, 0, this);
    	Font fnt0= new Font ("arial",Font.BOLD,50);
    	g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Game is Over", View.WIDTH/2+220, 100);
    }
    //MENUDRAW
    public void drawMenu(Graphics2D g) {
    	g.drawImage(background2, (int) -backgroundx, 0, this);
    	Font fnt0= new Font ("arial",Font.BOLD,50);
    	g.setFont(fnt0);
		g.setColor(Color.BLACK);
		g.drawString("RACING GAME", View.WIDTH/2+220, 100);
		g.draw(playButton);
		//g.fillRect(View.WIDTH/2+360,250,100,50);
		g.draw(highButton);
		//g.fillRect(View.WIDTH/2+360,150,100,50);
		g.draw(loginButton);
		//g.fillRect(View.WIDTH/2+360,350,100,50);
		g.draw(quitButton);
		//g.fillRect(View.WIDTH/2+360,450,100,50);
		Font font1= new Font("arial",Font.BOLD, 30);
		g.setFont(font1);
		g.setColor(Color.BLACK);
		g.drawString("Play", playButton.x+20, playButton.y+30);
		g.drawString("Scores", highButton.x+3, highButton.y+30);
		g.drawString("Login", loginButton.x+18, loginButton.y+30);
		g.drawString("Quit", quitButton.x+20, quitButton.y+30);
    }
    
    //DRAW
    public void draw(Graphics2D g) {
        g.scale(2, 2.2);
        g.drawImage(background, (int) -backgroundx, 0, this);
        
        g.scale(0.5, 1/2.2);
        g.translate(getWidth() / 2, getHeight() / 2);
        
       
        //YOL RENDER
        Point onceki = null;
        int previousCurve = 0;
        for (int j = 159; j > 0; j--) {
            int x = (int) (1200 / (j - a));
            int y = (int) ((500 - (j - a) * ha) / (j - a));
            int curva = (int) ((j - a) * yolviraj);
            if (onceki == null) {
                onceki = new Point(x, y);
            }
            else {
                Polygon poly = new Polygon();
                poly.addPoint(-onceki.x + previousCurve, onceki.y);
                poly.addPoint(onceki.x + previousCurve, onceki.y);
                poly.addPoint(x + curva, y);
                poly.addPoint(-x + curva, y);
                
                if ((j+track) % 2 == 0) {
                    g.setColor(new Color(0, 110, 0));
                }
                else {
                    g.setColor(new Color(0, 120, 0));
                }
                g.fillRect(-getWidth()/2, onceki.y, getWidth(), y-onceki.y);
               // g.draw(opp1);
                //g.draw(opp2);
               // g.draw(opp3);
               // g.draw(opp4);
                
                if ((j+track) % 2 == 0) {
                    g.setColor(new Color(140, 140, 140));
                }
                else {
                    g.setColor(new Color(160, 160, 160));
                }
                g.fillPolygon(poly);
                onceki.setLocation(x, y);
                

              //AGAC RENDER
                if ((j+track) % 13 == 0) {
                	//int motorc=(int) (300 / (j - a)); 
                    int trrex = (int) (1300 / (j - a)); 
                    int treeWidth = (int) (tree.getWidth() / (j-a) * 12);
                    int treeHeight = (int) (tree.getHeight() / (j-a) * 12);
                    g.drawImage(tree, trrex + curva, (int) onceki.y - treeHeight, treeWidth, treeHeight, this);
                    g.drawImage(tree, - trrex - treeWidth + curva, (int) onceki.y - treeHeight, treeWidth, treeHeight, this);
                }
                //AGAC RENDER
                if ((j+track) % 15 == 1) {
                    int trrex = (int) (4000 / (j - a)); 
                    int treeWidth = (int) (tree.getWidth() / (j-a) * 6);
                    int treeHeight = (int) (tree.getHeight() / (j-a) * 6);
                    g.drawImage(tree2, trrex + curva, (int) onceki.y - treeHeight, treeWidth, treeHeight, this);
                    g.drawImage(tree2, - trrex - treeWidth + curva, (int) onceki.y - treeHeight, treeWidth, treeHeight, this);
                }
            }
            previousCurve = curva;
        }
      //public Rectangle opp3= new Rectangle(View.WIDTH/2-350,150,75,125);
        //public Rectangle carrec= new Rectangle(ikiyuz,150,100,50);
        //  g.fillRect(ikiyuz,150,100,50);
        //public Rectangle opp1= new Rectangle(View.WIDTH/2+270,150,100,50);
        //public Rectangle opp2= new Rectangle(View.WIDTH/2-35,150,100,50);
        //public Rectangle opp3= new Rectangle(View.WIDTH/2-350,150,75,125);
        //public Rectangle opp4= new Rectangle(View.WIDTH/2+130,150,100,50);
        // g.fillRect(View.WIDTH/2+270,150,100,50);
        //g.fillRect(View.WIDTH/2-35,150,100,50);
        //g.fillRect(View.WIDTH/2-350,150,75,125);
        //g.fillRect(View.WIDTH/2+130,150,100,50);
        
        int rand = 0; 
        int max = 100; 
        int min = 1; 
        int yuzelli=150;
        int range = max - min + 1; 
        for (int i = 0; i < 1000; i++) { 
            rand = (int)(Math.random() * range) + min; }
        if(rand>80) {
        	yuzelli=yuzelli+15;
        }
        else if(rand<=80) {
        	yuzelli=yuzelli+15;
        }
        
        
        
        //YAZILAR VE MOTORSIKLET BASTIRMA
       g.drawImage(motorcycle2, opp3.x,opp3.y,motorcycle.getWidth()*4,motorcycle.getHeight()*4, this);
       g.drawImage(motorcycle1, opp1.x,opp1.y,motorcycle.getWidth()*4,motorcycle.getHeight()*4, this);
       g.drawImage(motorcycle3, opp2.x,opp2.y,motorcycle.getWidth()*4,motorcycle.getHeight()*4, this);
       g.drawImage(motorcycle4, opp4.x,opp4.y,motorcycle.getWidth()*4,motorcycle.getHeight()*4, this);
       g.drawImage(motorcycle, ikiyuz, 150+(int)(Math.random()*3)-1, motorcycle.getWidth()*4, motorcycle.getHeight()*4, this);
       g.setColor(Color.BLUE);
       g.drawString("Track: "+track, 50-getWidth()/2, 50-getHeight()/2);
       g.setColor(Color.RED);
       int value= (int) speed;
       g.drawString("Speed: "+value, 150-getWidth()/2, 50-getHeight()/2);
       g.setColor(Color.BLACK);
       g.drawString("Score: "+score, 550-getWidth()/2, 50-getHeight()/2);
       if(checkpoint1>=0) {
    	   g.drawString("Checkpoint: "+checkpoint1, 350-getWidth()/2, 50-getHeight()/2);}
       else if(checkpoint2>=0) {
    	   g.drawString("Checkpoint: "+checkpoint2, 350-getWidth()/2, 50-getHeight()/2);}
       else if(checkpoint3>=0) {
    	   g.drawString("Checkpoint: "+checkpoint3, 350-getWidth()/2, 50-getHeight()/2);}
       else {
    	   g.drawString("Checkpoint: "+checkpoint4, 300-getWidth()/2, 50-getHeight()/2);}
       if(checkpoint1>=0 && checkpoint2>=0 && checkpoint3>=0 ) {
    	   g.drawString("Time1: "+count1, 250-getWidth()/2, 50-getHeight()/2);
    	   count2=0;
    	   }
       else if(checkpoint2>=0 && checkpoint3>=0 && checkpoint1<0) {
           g.drawString("Time2: "+count2, 250-getWidth()/2, 50-getHeight()/2);
           count3=0;
           }
       else if(checkpoint2<=0 && checkpoint3>=0 && checkpoint1<0) {
           g.drawString("Time3: "+count3, 250-getWidth()/2, 50-getHeight()/2);
           count4=0;
           }
       else {
           g.drawString("Time4: "+count4, 250-getWidth()/2, 50-getHeight()/2);
           }
           g.drawString("Quit", 750-getWidth()/2, 50-getHeight()/2);
           g.drawString("Restart", 650-getWidth()/2, 50-getHeight()/2);
    }

    
    
    
    //SAG SOL ILERI GERI
    public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key) {
		
		    //SOL
			case KeyEvent.VK_LEFT:
				yolviraj = -5;
				if(motorcycle.getWidth()-move<width/2-90 && ikiyuz>-451) {
					ikiyuz=ikiyuz-20;
					try {
						motorcycle=ImageIO.read(new File("left.png"));
						motorcycle2=ImageIO.read(new File("left.png"));
						motorcycle1=ImageIO.read(new File("left.png"));
						motorcycle3=ImageIO.read(new File("left.png"));
						motorcycle4=ImageIO.read(new File("left.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(ikiyuz<-429) {
						java.util.Timer ta6 = new  java.util.Timer(); 
			              ta6.schedule(new TimerTask()
			        		{
			            	  	@Override
								public void run() {View.State=View.STATE.RESTART;
									// TODO Auto-generated method stub
									}
			        		},1500);
			               try {
								motorcycle=ImageIO.read(new File("crash.png"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
				break;
				
				
			//SAG
			case KeyEvent.VK_RIGHT:
				yolviraj = 5;
				if(motorcycle.getWidth()-move<width/2+30 && ikiyuz<291) {
					ikiyuz=ikiyuz+20;
					viraj=1;
					try {
						motorcycle=ImageIO.read(new File("road.png"));
						motorcycle2=ImageIO.read(new File("road.png"));
						motorcycle3=ImageIO.read(new File("road.png"));
						motorcycle1=ImageIO.read(new File("road.png"));
						motorcycle4=ImageIO.read(new File("road.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(ikiyuz>279) {
				java.util.Timer ta5 = new  java.util.Timer(); 
	              ta5.schedule(new TimerTask()
	        		{

						@Override
						public void run() {View.State=View.STATE.RESTART;
							// TODO Auto-generated method stub
							}
	        		},1500);
	              
	              try {
						motorcycle=ImageIO.read(new File("crash.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				break;
				
				
			//ASAGI
			case KeyEvent.VK_DOWN:

				if(speed>30) {
				speed=speed-0.5;
            	track1=track1-0.2;
            	checkpoint1x=checkpoint1x+0.5;
            	checkpoint2x=checkpoint2x+0.5;
            	checkpoint3x=checkpoint3x+0.5;
            	checkpoint4x=checkpoint4x+0.5;}
				else if(speed==30) {
					speed=30;
				}
				break;
				
			//YUKARI
			case KeyEvent.VK_UP:
				
				yolviraj = 0;
			try {
				motorcycle=ImageIO.read(new File("motor.png"));
				motorcycle2=ImageIO.read(new File("motor.png"));
				motorcycle1=ImageIO.read(new File("motor.png"));
				motorcycle3=ImageIO.read(new File("motor.png"));
				motorcycle4=ImageIO.read(new File("motor.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				break;
			default:
					break;
		}
	}
    
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
