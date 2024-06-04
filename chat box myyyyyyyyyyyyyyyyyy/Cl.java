import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;
import java.net.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class Cl
	{
public static void main(String[] args) throws Exception
	{
		try 
		{

System.out.println("Client Side is now Working.........");
			Client client = new Client();
			Thread t = new Thread(client);
			t.start();
			} 
		catch (Exception noServer)
		{
			System.err.println("The server might not be up at this time.");
			System.err.println("Please try again later.");
		}
	}
}

 class Client extends Frame implements ActionListener,Runnable,KeyListener
	 {
 
	 Socket socket;
private static BufferedReader br;
private static BufferedWriter bw;
	TextField text,name;
        Button Send,clear;
        Label label1;
        List list;
        Panel p1,p2,sp21,sp22,jp=null;
	//@Override
	
	public Client(){
	
	
	
CloseMe cm = new CloseMe();
    addWindowListener(cm);









  jp=new Panel();
                p1=new Panel();
                p2=new Panel();
                sp21=new Panel();
                sp22=new Panel();

                jp.setLayout(new GridLayout(2,1));
                p1.setLayout(new GridLayout(1,1));
                p2.setLayout(new GridLayout(2,1));
                sp21.setLayout(new FlowLayout());
                sp22.setLayout(new FlowLayout());


                Send = new Button("Send");
				
                clear = new Button("Clear"); 
                Send.addActionListener(this);
                clear.addActionListener(this);
                list = new List(50);
                text = new TextField(43);
                name = new TextField(10);
                label1=new Label("Enter Your Name");
                name.addKeyListener(this);
                text.addKeyListener(this);
                p1.add(list);

                sp21.add(text);
                sp21.add( Send);
                sp22.add(label1);
                sp22.add(name);
                sp22.add(clear);
                p2.add(sp21);
                p2.add(sp22);

                jp.add(p1);
                jp.add(p2);

                this.add(jp);
                setBackground(Color.orange);
                setSize(420,300);
                //setLocation(400,0);
                setLocationRelativeTo(null);
				setVisible(true);
                setResizable(false);
                setTitle("Client");
                     
				name.requestFocus();

	}


public void run()
	{
try{socket.setSoTimeout(1);}catch(Exception e){}




try
		{
socket = new Socket("127.0.0.1",786);	
br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
			
			
			
			
			
		while (true)
		{
			try{
                        list.add(br.readLine());
			}catch (Exception h){}
                                 if(list.getItemCount()==7)
                                 list.remove(0);

		}

			}
catch (Exception e) 
			{
System.err.println(e);
}
	        
}

public void actionPerformed ( ActionEvent e)
	{
                 if (e.getSource().equals(Send))
					 try{
					 
					 bw.write(name.getText()+">>"+text.getText());
                                
								 bw.newLine();bw.flush();
								 
				  list.add(name.getText()+">>"+text.getText());
                                 text.setText("");
								 
								 }catch(Exception exc){}
				
				 
				 //System.exit(0);
                else if (e.getSource().equals(clear))
                        { name.setText(" ");
                         name.setEditable(true);
                        }
                 
        }
        public void keyPressed(KeyEvent ke)
        {        if(text.equals(ke.getSource()))
                     {
                        if(ke.getKeyCode()==KeyEvent.VK_ENTER)
                            {
                              try{
                                 bw.write(name.getText()+">>"+text.getText());
                                 bw.newLine();bw.flush();
        
                                 }catch(Exception m){}
                                 list.add(name.getText()+">>"+text.getText());
                                 text.setText("");
                             }
                       }
                  if(name.equals(ke.getSource()))
                     {
                        if(ke.getKeyCode()==KeyEvent.VK_ENTER)
                             {
                                name.setEditable(false);
                                text.requestFocus();
                               }
                      }

        }
        public void keyReleased(KeyEvent ke) { //something
         }
        public void keyTyped(KeyEvent ke) {} //something
                                                                                                                                   
	}


class CloseMe extends WindowAdapter
{
  public void windowClosing(WindowEvent e)
  {
    System.exit(0);
  }
}


	

