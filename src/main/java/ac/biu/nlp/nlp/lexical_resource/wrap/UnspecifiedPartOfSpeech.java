package ac.biu.nlp.nlp.lexical_resource.wrap;

import java.util.HashSet;
import java.util.Set;

import eu.excitementproject.eop.core.representation.parsetree.CanonicalPosTag;
import eu.excitementproject.eop.core.representation.parsetree.PartOfSpeech;
import eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException;

//TODO Oct 2012: temporary solution for using BIU's resource as a EOP resource. 
/**
 * This PartOfSpeech merely stores a {@link CanonicalPosTag}, but not a specific pos-tag-string.
 * 
 * 
 * @author Asher Stern
 * @since Mar 31, 2011
 *
 */
public class UnspecifiedPartOfSpeech extends PartOfSpeech
{

	///////////////////// PUBLIC ////////////////////////////
	
	// PUBLIC STATIC
	
	private static final long serialVersionUID = 1968948467307326150L;

	public static final Set<String> CANONICAL_POS_TAGS_STRINGS;
	static
	{
		CANONICAL_POS_TAGS_STRINGS = new HashSet<String>();
		for (CanonicalPosTag pos : CanonicalPosTag.values())
		{
			CANONICAL_POS_TAGS_STRINGS.add(pos.name());
		}
	}
	
	////////////////// PUBLIC CONSTRUCTORS AND METHODS /////////////////////////
	
	public UnspecifiedPartOfSpeech(String posTagString) throws UnsupportedPosTagStringException
	{
		super(posTagString);
	}
	
	public UnspecifiedPartOfSpeech(CanonicalPosTag canonicalPosTag) throws UnsupportedPosTagStringException
	{
		this(canonicalPosTag.name());
	}

	@Override
	public PartOfSpeech createNewPartOfSpeech(String posTagString) throws UnsupportedPosTagStringException
	{
		return new UnspecifiedPartOfSpeech(posTagString);
	}

	@Override
	protected void setCanonicalPosTag()
	{
		this.canonicalPosTag = CanonicalPosTag.OTHER;
		try{this.canonicalPosTag = CanonicalPosTag.valueOf(posTagString);}
		catch(RuntimeException e){this.canonicalPosTag = CanonicalPosTag.OTHER;}
	}

	
	///////////////////// PROTECTED ////////////////////////////
	
	@Override
	protected void validatePosTagString(String posTagString) throws UnsupportedPosTagStringException
	{
		if (this.posTagString!=null)
		{
			if (this.posTagString.length()>0)
			{
				if (!CANONICAL_POS_TAGS_STRINGS.contains(posTagString))
					throw new UnsupportedPosTagStringException("The pos tag \""+posTagString+"\" is not in the set of canonical part of speeches");
			}
		}
	}

}
