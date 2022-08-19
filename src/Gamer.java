//import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Gamer {

	String name;
	int password;
	int score1;
	
	
	public Gamer() throws Exception{
		
		boolean cnt = true;
		
		while(cnt){
			String name = JOptionPane.showInputDialog("Enter name\n");
			String password = JOptionPane.showInputDialog("Enter password\n");
			this.password = Integer.parseInt(password);
            try{
            	this.name=name;                
                if(name.matches(".*\\d.*")) {
                	cnt = true;
                    throw new Exception();
                    }
                else{
                	cnt= false;}
            }
            catch (Exception e){
            	JOptionPane.showMessageDialog(null, " Name cannot contain any numbers!","Error", JOptionPane.ERROR_MESSAGE);
            }
}
	}
	public int getPassword(){
		return password;
	}	
	
	public String getName(){
		return name;
	}


}

