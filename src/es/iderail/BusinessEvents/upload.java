package es.iderail.BusinessEvents;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Upload a file to a remote server
 * 
 * @author jgcasta@gmail.com
 *
 */
public class upload {
	
	/**
	 * Test Class
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	int result = 0;


    }
    
    /**
     * Upload a file to a remote server
     * 
     * @param ftpserver <b>String</b> IP address of the ftp server
     * @param username <b>String</b> Username
     * @param passwd <b>String</b> Password
     * @param sourcefile <b>String</b> Filename and path of the source local file 
     * @param destinationfile <b>String</b> Filename and path of the remote destination file with path
     * @return <b>int</b> 	0 - correct
	 * 						1 - upload file doesn't exist
	 * 						2 - cannot disconnect
     */
    public static int UploadFile(String ftpserver, String username,
    								String passwd,String sourcefile,String destinationfile){
        FTPClient client = new FTPClient();
        FileInputStream fis = null;
        int out = 0;

        try {
            client.connect(ftpserver);
            client.login(username, passwd);
            
            // connection must be binary
            client.setFileType(FTP.BINARY_FILE_TYPE);

            // InputStream to store the file
            String filename = sourcefile;
            fis = new FileInputStream(filename);

            // upload the file
            client.storeFile(destinationfile, fis);
            
            client.logout();
            out = 0;
            
        } catch (IOException e) {
            e.printStackTrace();
            out = 1; // upload file doesn't exists
            
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                client.disconnect();
                
            } catch (IOException e) {
                e.printStackTrace();
                out = 2; // cannot disconnect
            }
        }
        return out;
    }
}