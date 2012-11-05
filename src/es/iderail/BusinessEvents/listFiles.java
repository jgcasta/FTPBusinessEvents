package es.iderail.BusinessEvents;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * List files in a directory from a remote server
 * 
 * @author jgcasta@gmail.com
 *
 */
public class listFiles {
	
	/**
	 * Test Class
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	int result = 0;





    }    	

    /**
     * 
     * List files in a directory from a remote server
     * 
     * @param ftpserver <b>String</b> IP address of the ftp server
     * @param username <b>String</b> Username
     * @param passwd <b>String</b> Password
     * @param path <b>String</b> Path
     * @param extension <b>String</b> File extension as *.xml
     * @return <b>String[]</b> List with file names, null when no files have found
     */
    public static String[] ListFiles(String ftpserver, String username,
			String passwd,String path, String extension){
        
    	String[] lista = null;
    	
    	FTPClient client = new FTPClient();

        try {
            client.connect(ftpserver);
            client.login(username, passwd);
            client.setFileType(FTP.BINARY_FILE_TYPE);
            // only Files, not directories
            lista = client.listNames(path + "/" + extension);
            client.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }
}