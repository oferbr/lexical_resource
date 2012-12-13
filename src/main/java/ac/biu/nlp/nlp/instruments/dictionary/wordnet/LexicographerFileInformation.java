package ac.biu.nlp.nlp.instruments.dictionary.wordnet;

/**
 * 
 * @author Asher Stern
 * @since Sep 27, 2012
 *
 */
public final class LexicographerFileInformation
{
	public static LexicographerFileInformation get(int fileId) throws WordNetException
	{
		try
		{
			return informations[fileId];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			throw new WordNetException("Wrong lexicographer file id: "+fileId);
		}
	}
	
	public String getId()
	{
		return id;
	}
	public String getFilename()
	{
		return filename;
	}
	public String getContents()
	{
		return contents;
	}
	
	
	



	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contents == null) ? 0 : contents.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LexicographerFileInformation other = (LexicographerFileInformation) obj;
		if (contents == null)
		{
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (filename == null)
		{
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	///////////////////// PRIVATE ///////////////////// 
	
	private LexicographerFileInformation(String id, String filename,
			String contents)
	{
		super();
		this.id = id;
		this.filename = filename;
		this.contents = contents;
	}



	private static final LexicographerFileInformation[] informations;
	static
	{
		informations = new LexicographerFileInformation[]
				{
				new LexicographerFileInformation("00","adj.all","all adjective clusters"),
				new LexicographerFileInformation("01","adj.pert","relational adjectives (pertainyms)"),
				new LexicographerFileInformation("02","adv.all","all adverbs"),
				new LexicographerFileInformation("03","noun.Tops","unique beginner for nouns"),
				new LexicographerFileInformation("04","noun.act","nouns denoting acts or actions"),
				new LexicographerFileInformation("05","noun.animal","nouns denoting animals"),
				new LexicographerFileInformation("06","noun.artifact","nouns denoting man-made objects"),
				new LexicographerFileInformation("07","noun.attribute","nouns denoting attributes of people and objects"),
				new LexicographerFileInformation("08","noun.body","nouns denoting body parts"),
				new LexicographerFileInformation("09","noun.cognition","nouns denoting cognitive processes and contents"),
				new LexicographerFileInformation("10","noun.communication","nouns denoting communicative processes and contents"),
				new LexicographerFileInformation("11","noun.event","nouns denoting natural events"),
				new LexicographerFileInformation("12","noun.feeling","nouns denoting feelings and emotions"),
				new LexicographerFileInformation("13","noun.food","nouns denoting foods and drinks"),
				new LexicographerFileInformation("14","noun.group","nouns denoting groupings of people or objects"),
				new LexicographerFileInformation("15","noun.location","nouns denoting spatial position"),
				new LexicographerFileInformation("16","noun.motive","nouns denoting goals"),
				new LexicographerFileInformation("17","noun.object","nouns denoting natural objects (not man-made)"),
				new LexicographerFileInformation("18","noun.person","nouns denoting people"),
				new LexicographerFileInformation("19","noun.phenomenon","nouns denoting natural phenomena"),
				new LexicographerFileInformation("20","noun.plant","nouns denoting plants"),
				new LexicographerFileInformation("21","noun.possession","nouns denoting possession and transfer of possession"),
				new LexicographerFileInformation("22","noun.process","nouns denoting natural processes"),
				new LexicographerFileInformation("23","noun.quantity","nouns denoting quantities and units of measure"),
				new LexicographerFileInformation("24","noun.relation","nouns denoting relations between people or things or ideas"),
				new LexicographerFileInformation("25","noun.shape","nouns denoting two and three dimensional shapes"),
				new LexicographerFileInformation("26","noun.state","nouns denoting stable states of affairs"),
				new LexicographerFileInformation("27","noun.substance","nouns denoting substances"),
				new LexicographerFileInformation("28","noun.time","nouns denoting time and temporal relations"),
				new LexicographerFileInformation("29","verb.body","verbs of grooming, dressing and bodily care"),
				new LexicographerFileInformation("30","verb.change","verbs of size, temperature change, intensifying, etc."),
				new LexicographerFileInformation("31","verb.cognition","verbs of thinking, judging, analyzing, doubting"),
				new LexicographerFileInformation("32","verb.communication","verbs of telling, asking, ordering, singing"),
				new LexicographerFileInformation("33","verb.competition","verbs of fighting, athletic activities"),
				new LexicographerFileInformation("34","verb.consumption","verbs of eating and drinking"),
				new LexicographerFileInformation("35","verb.contact","verbs of touching, hitting, tying, digging"),
				new LexicographerFileInformation("36","verb.creation","verbs of sewing, baking, painting, performing"),
				new LexicographerFileInformation("37","verb.emotion","verbs of feeling"),
				new LexicographerFileInformation("38","verb.motion","verbs of walking, flying, swimming"),
				new LexicographerFileInformation("39","verb.perception","verbs of seeing, hearing, feeling"),
				new LexicographerFileInformation("40","verb.possession","verbs of buying, selling, owning"),
				new LexicographerFileInformation("41","verb.social","verbs of political and social activities and events"),
				new LexicographerFileInformation("42","verb.stative","verbs of being, having, spatial relations"),
				new LexicographerFileInformation("43","verb.weather","verbs of raining, snowing, thawing, thundering"),
				new LexicographerFileInformation("44","adj.ppl","participial adjectives"),
				};
	}



	private final String id;
	private final String filename;
	private final String contents;
}
