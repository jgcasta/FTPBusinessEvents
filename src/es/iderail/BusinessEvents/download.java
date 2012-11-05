package es.iderail.BusinessEvents;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * Download a file from a remote server
 * 
 * @author jgcasta@gmail.com
 *
 */
public class download {
	
	/**
	 * Test class
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
    	int result = 0;
    	

    }
    
    /**
     * 
     * Download a file from a remote server
     * 
     * @param ftpserver <b>String</b> IP address of the ftp server
     * @param username <b>String</b> Username
     * @param passwd <b>String</b> Password
     * @param sourcefile <b>String</b> Filename and path of the source remote file with path 
     * @param destinationfile <b>String</b> Filename and path of the local destination file with path
     * @return <b>int</b> 	0 - correct
	 * 						1 - cannot download file
	 * 						2 - cannot disconnect
     */
    public static int DownloadFile(String ftpserver, String username,
			String passwd,String sourcefile,String destinationfile){
        FTPClient client = new FTPClient();
        FileOutputStream fos = null;
        int out = 0;

        try {
            client.connect(ftpserver);
            client.login(username, passwd);
            
            // connection must be binary
            client.setFileType(FTP.BINARY_FILE_TYPE);

            // The remote filename to be downloaded.
            String filename = destinationfile;
            fos = new FileOutputStream(filename);

            // Download file from FTP server
            client.retrieveFile( sourcefile, fos);
        
        } catch (IOException e) {
            e.printStackTrace();
            out = 1;
        } 
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                out = 2;
            }
        }
        return out;

    }
}