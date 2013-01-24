package fr.univartois.ili.sadoc.sadocweb.repertory;

import java.io.File;
import java.util.logging.Logger;

public class MyScanRepertory {

	public MyScanRepertory() {
	}

	public String[] getListFiles(String directoryPath) {
		String[] files = null;
		File directoryToScan = new File(directoryPath);
		if(directoryToScan.isDirectory()){
			files = directoryToScan.list();
			Logger.getAnonymousLogger().warning("Nombre de fichiers competences a inserer :" + files.length);
			return files;
		}
		files = new String[1];
		files[0] = directoryPath;
		return files;		
	}
}