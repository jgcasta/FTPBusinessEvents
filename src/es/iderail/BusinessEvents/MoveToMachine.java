package es.iderail.BusinessEvents;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * 
 * Move a file from a remote server to another
 * 
 * @author jgcasta@gmail.com
 *
 */
public class MoveToMachine {

	/**
	 * Test Class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int algo = 0;
		


	}
	
	/**
	 * 
	 * Move a file from a ftp server to another
	 * 
	 * @param ftpserverO <b>String</b> remote source host
	 * @param ftpserverD <b>String</b> remote destination host
	 * @param usernameO <b>String</b> source host username
	 * @param passwdO <b>String</b> source host passwd
	 * @param usernameD <b>String</b> remote destination username
	 * @param passwdD <b>String</b> remote destination passwd
	 * @param sourcefile <b>String</b> source filename with path
	 * @param destinationfile <b>String</b> destination filename with path
	 * @return <b>int</b> 	0 - correct
	 * 						1 - cannot connect to source host
	 * 						2 - cannot connect to destination host
	 * 						3 - cannot move the file to the server destination
	 */
	public static int moveToMachine(String ftpserverO, 
			String ftpserverD, 
			String usernameO,
			String passwdO, 
			String usernameD,
			String passwdD,
			String sourcefile,
			String destinationfile){
		
		int out = 0;
		boolean result = false;
		
        FTPClient client = new FTPClient();
        FTPClient clientD = new FTPClient();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        
        InputStream is = null;
        
        try {
            client.connect(ftpserverO);
            client.login(usernameO, passwdO);
            client.setFileType(FTP.BINARY_FILE_TYPE);
        }catch (Exception e1){
        	e1.printStackTrace();
        	out = 1;
        }
        
        try {
            clientD.connect(ftpserverD);
            clientD.login(usernameD, passwdD);
            clientD.setFileType(FTP.BINARY_FILE_TYPE);
        	
        }catch (Exception e2){
        	e2.printStackTrace();
        	out = 2;
        }
        
        try {


            is = client.retrieveFileStream(sourcefile);
            
            result = clientD.storeFile(destinationfile, is);
            is.close();
            if (!result){
            	out = 3; // cannot move the file to the server destination
            }
            
            client.disconnect();
            clientD.disconnect();
            out = 0;
            
            
        }catch (Exception e){
        	e.printStackTrace();
        }
		
		
		return out;
	}

}
