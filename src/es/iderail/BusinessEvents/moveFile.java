package es.iderail.BusinessEvents;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;

/**
 * Move a file from a directory to another from a remote server
 * 
 * @author jgcasta@gmail.com
 *
 */
public class moveFile {
	
	/**
	 * 
	 * Test class
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	int result = 0;
    	
    	

    }
    /**
     * 
     * Move a file from one directory to another, 
     * 
     * @param ftpserver <b>String</b> IP address of the ftp server
     * @param username <b>String</b> Username
     * @param passwd <b>String</b> Password
     * @param sourcefile <b>String</b> Filename and path of the file will be move from
     * @param destinationfile <b>String</b> Filename and path of the file will be move to
     * @return
     */
    public static int MoveFile(String ftpserver, String username,
    								String passwd,String sourcefile,String destinationfile){
        FTPClient client = new FTPClient();
        FileInputStream fis = null;
        int out = 0;
        boolean result = false;

        try {
            client.connect(ftpserver);
            client.login(username, passwd);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            
            result =client.rename(sourcefile, destinationfile);
            if (!result){
            	out = 1; // error moving the file
            }
            client.logout();

        } catch (IOException e) {
            e.printStackTrace();
            out = 2; // cannot connect
            
        } finally {
            try {
                client.disconnect();
                
            } catch (IOException e) {
                e.printStackTrace();
                out = 3; // cannot disconnect
            }
        }
        return out;
    }
}