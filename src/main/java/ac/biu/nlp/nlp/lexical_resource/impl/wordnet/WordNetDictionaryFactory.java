/**
 * 
 */
package ac.biu.nlp.nlp.lexical_resource.impl.wordnet;

import java.io.File;

import ac.biu.nlp.nlp.instruments.dictionary.wordnet.Dictionary;
import ac.biu.nlp.nlp.instruments.dictionary.wordnet.WordNetInitializationException;
import ac.biu.nlp.nlp.instruments.dictionary.wordnet.WordnetDictionaryImplementationType;
import ac.biu.nlp.nlp.instruments.dictionary.wordnet.ext_jwnl.ExtJwnlDictionary;
import ac.biu.nlp.nlp.instruments.dictionary.wordnet.jmwn.JmwnDictionaryManager;
import ac.biu.nlp.nlp.instruments.dictionary.wordnet.jwi.JwiDictionary;

/**
 * @author Amnon Lotan
 *
 * @since 7 Dec 2011
 */
public class WordNetDictionaryFactory {

	/**
	 * @param wnDictionaryDir
	 * @param wordnetDictionaryImplementation 
	 * @return
	 * @throws WordNetInitializationException 
	 */
	public static Dictionary newDictionary(File wnDictionaryDir, WordnetDictionaryImplementationType wordnetDictionaryImplementation) throws WordNetInitializationException
	{
		if (wordnetDictionaryImplementation == null)
			// default option
			return new ExtJwnlDictionary(wnDictionaryDir);
		else
			switch (wordnetDictionaryImplementation)
			{
			case JWI:
				return new JwiDictionary(wnDictionaryDir);
			case EXT_JWNL:
				return new ExtJwnlDictionary(wnDictionaryDir);
			case JMWN:
				// wnDictionaryDir should be here the jMultiWordNet config file
				return new JmwnDictionaryManager(wnDictionaryDir).newDictionary();
			default:
				throw new WordNetInitializationException("Unsupported WordnetDictionaryImplementationType: " + wordnetDictionaryImplementation + 
						". Add code here to supprt it.");
			
			}
	}

}
