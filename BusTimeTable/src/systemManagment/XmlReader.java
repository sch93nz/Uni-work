/**
 * 
 */
package systemManagment;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author User
 *
 */
public class XmlReader {
/**
 *  reads in the starting info so when was last update and what the version was also the user options
 * @param fis
 */
	public void getInfo(FileInputStream fis){
		
		try {
			byte[] data = new byte[fis.available()+20];
			 
		} catch (IOException e) {

		}

	}

}
