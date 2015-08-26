package threads.pkg2.exercise3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class BackupThread extends Thread{
    private volatile List<String> list;
    String userDir;
    
    public BackupThread(List<String> list) {
        this.userDir = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
        this.list = list;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                String content = "";
                
                for (String item : list) {
                    content += item + "\n";
                }
                
                FileWriter writer = new FileWriter("backup.txt", false); 
                try (PrintWriter out = new PrintWriter(writer)) {
                    out.println(content);
                }
                
                System.out.println("File saved!");
                
                sleep(15000);
            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(BackupThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
