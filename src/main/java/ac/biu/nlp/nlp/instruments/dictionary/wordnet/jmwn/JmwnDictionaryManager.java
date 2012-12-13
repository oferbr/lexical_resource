package ac.biu.nlp.nlp.instruments.dictionary.wordnet.jmwn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import ac.biu.nlp.nlp.instruments.dictionary.wordnet.WordNetInitializationException;

public class JmwnDictionaryManager {

	String configFileName = "multiwordnet.properties";
	File configFile;
//	File configFile = new File("/hardmnt/destromath0/home/nastase/Project/EXC/WN/JMWN-1.2/conf/multiwordnet.properties");
	
	public JmwnDictionaryManager() {
		findConfigFile();
	}
	
	
	// issues with passing this parameter from the WordnetLexicalResourcesFactory.java
	public JmwnDictionaryManager(String configFile){
		if (configFile.matches(".*properties")) {
			this.configFile = new File(configFile);
		} else {
			findConfigFile();
		}
	}
	
	public JmwnDictionaryManager(File configFile) {
		if (configFile.getName().matches(".*properties")) {
			this.configFile = configFile;
		} else {
			findConfigFile();
		}
	}
	
	public JmwnDictionary newDictionary() throws WordNetInitializationException {
		System.out.println("\nInitializing MultiWordNet from file " + configFile.getName());
		return new JmwnDictionary(this.configFile);
	}

	private void findConfigFile() {
		File thisDir = new File("./");
		File[] files = new File[1];
/*		File[] files = thisDir.listFiles( new FilenameFilter(){
								public boolean accept(File dir, String name) {
									return name.endsWith("multiwordnet.properties");
								}
						});
		if (files.length > 0 ) {
			System.out.println("Configuration file found: " + files[0]);
			this.configFile = files[0];
		} else {
			System.err.println("JmwnDictionaryManager.java: Could not find configuration file multiwordnet.properties");
			System.exit(1);
		}
*/
		try {
			files = FindFile.FindFileRecursively(thisDir, configFileName).toArray(files);
			if (files.length < 1) {
				System.err.println("Configuration file not found");
				System.exit(1);
			}
			this.configFile = files[0];
		} catch (FileNotFoundException e) {
			System.err.println("Configuration file not found");
			System.exit(1);
		}
		
	}
	
	
	private static class FindFile{
		
		static public List<File> FindFileRecursively(File directory, String matchingPattern) throws FileNotFoundException {
			List<File> matchingFiles = new ArrayList<File>();
			if (directory != null && directory.isDirectory()) {
				for (File file : directory.listFiles()) {
					if (file.isFile() && file.getName().matches(matchingPattern)) {
						matchingFiles.add(file);
					} else {
						matchingFiles.addAll(FindFile.FindFileRecursively(file, matchingPattern));
					}
				}
			}
			return matchingFiles;
		}
		
	}
	
	
}
