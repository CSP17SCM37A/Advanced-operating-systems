package resilience;
import java.io.*;  
import java.net.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Scanner;

class Hashtableop
{
    private int  maxSize;       
    private String[] keys;   
    public Hashtableop(int capacity) 
    {
        
        maxSize = capacity;
        keys = new String[maxSize];
        
    }  
 
    private int hash(String key) 
    {
        return key.hashCode() % maxSize;
    }    
 
    public int find(String key) 
    {                
        int tmp = hash(key);
               return tmp;
    }   
    
}
 
class PeerClient {
    public static void main(String args[])
        {   
                  
            try 
              {

                 Scanner scan = new Scanner(System.in);
                 Hashtableop Serverslct = new Hashtableop(8); 
                 System.out.println("Enter key to connect server:");
                 int n=Serverslct.find(scan.next());
                 if (n < 0)
                  n = -n;
                 System.out.println("connecting to the server "+n);
                 int k=0;
                
                 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Config.txt")));         
                 String Peerdetls; 
       		 String line;
       		 while ((line = br.readLine()) != null) 
                 {
                    
                  
                  if(n==k)
                  {
                    Peerdetls=line;
                    String words[] = Peerdetls.split(" ");
                   //  String firstTwo = words[0] + "  " + words[1]; 
                    System.out.println(words[0]);
                    System.out.println(words[1]);
                    
                    int port = Integer.parseInt(words[1]);
                     Socket s=new Socket(words[0],port);
                     System.out.println("Peer1 Intitialized");
                     DataInputStream  inp = new DataInputStream(s.getInputStream());
                     DataOutputStream oup = new DataOutputStream(s.getOutputStream());

                    char ch;
      		  
		     do    
        		{
           		  System.out.println("\nHash Table Operations\n");
            		  System.out.println("1. PUT ");
            		  System.out.println("2. GET");
            		  System.out.println("3. DELETE");            
                 
           		 int choice = scan.nextInt(); 
                         String choice1 = Integer.toString(choice);
                         oup.writeBytes(choice1);
                         oup.writeByte('\n');
           
            		 switch (choice)
            		 {
            		  case 1 : 
                		   System.out.println("Enter key and value");
                		  oup.writeBytes(scan.next());
                                  oup.writeByte('\n');
                                  oup.writeBytes( scan.next());
                                  oup.writeByte('\n');
                                   String ip21 = inp.readLine();
                                    System.out.println(ip21);
                                   break;                          
                                  
                          case 2 : 
                                    System.out.println("Enter key");
                                  oup.writeBytes(scan.next());
                                  oup.writeByte('\n');
                                    String ip6 = inp.readLine();
                                    System.out.println("Value = "+ip6 );
                                    break;  
                          case 3 :                 
                                    System.out.println("Enter key");
                                     oup.writeBytes(scan.next());
                                     oup.writeByte('\n');
                                     String ip22 = inp.readLine();
                                    System.out.println(ip22);
                		    break;                                  
                       
           		  default : 
                		   System.out.println("Wrong Entry  ");
                		   break;   
                           }
            
                       
            System.out.println("Do you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);   
             String str = Character.toString(ch);
             oup.writeBytes(str);
             oup.writeByte('\n');                     
        } while (ch == 'Y'|| ch == 'y'); 





                  }
                  k++;  
                 }

                 
                 
                br.close();
              

               
                
                } 
 
            catch (Exception e)
             {
            System.err.println("Error: " + e.getMessage());
             }
    }
}

