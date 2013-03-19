/**
 * 
 */
package systemManagment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author User
 *
 */
public class XmlReader {
/**
 *  reads in the starting info so when was last update and what the version was also the user options
 * @param fis
 * @return 
 */
	public String[] getInfo(FileInputStream fis){
		
		try {
			byte[] data = new byte[fis.available()+20];
			 fis.read(data);
			 String info = new String(data);
			 String autoUpdate = info.substring(0, 1);
			 String hasData = info.substring(1,info.indexOf(':'));
			 String version = info.substring(info.indexOf(':'));
			 
			 return new String[]{autoUpdate, hasData, version};
			 
		} catch (IOException e) {

		}
		return new String[]{"T","F","000"};

	}

public void createSettings(FileOutputStream FoS) {
	try {
		FoS.write("TF:0000".getBytes());
		FoS.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}

}
