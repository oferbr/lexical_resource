package ac.biu.nlp.nlp.lexical_resource.wrap;

import java.util.HashMap;
import java.util.Map;

import eu.excitementproject.eop.core.representation.parsetree.CanonicalPosTag;

//TODO Oct 2012: temporary solution for using BIU's resource as a EOP resource. 
/***
 * Provides a biderectional mapping between the set of CanonicalPosTag of BIU and EOP. 
 * @author Ofer Bronstein
 *
 */
public class PosMapping {
	
	/*** Mapping between BIU and EOP POS-tags ***/
	private static final Map<ac.biu.nlp.nlp.representation.CanonicalPosTag, CanonicalPosTag> BIU_TO_EOP =
			new HashMap<ac.biu.nlp.nlp.representation.CanonicalPosTag, CanonicalPosTag>();
	private static final Map<CanonicalPosTag, ac.biu.nlp.nlp.representation.CanonicalPosTag> EOP_TO_BIU =
			new HashMap<CanonicalPosTag, ac.biu.nlp.nlp.representation.CanonicalPosTag>();
	
	static
	{
		// Fill BIU to EOP
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.NOUN,			CanonicalPosTag.N);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.VERB,			CanonicalPosTag.V);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.ADJECTIVE,		CanonicalPosTag.ADJ);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.ADVERB,		CanonicalPosTag.ADV);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.PREPOSITION,	CanonicalPosTag.PP);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.PRONOUN,		CanonicalPosTag.PR);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.PUNCTUATION,	CanonicalPosTag.PUNC);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.DETERMINER,	CanonicalPosTag.ART);
		BIU_TO_EOP.put(ac.biu.nlp.nlp.representation.CanonicalPosTag.OTHER,			CanonicalPosTag.OTHER);
		
		// Fill EOP to BIU
		EOP_TO_BIU.put(CanonicalPosTag.N,		ac.biu.nlp.nlp.representation.CanonicalPosTag.NOUN);
		EOP_TO_BIU.put(CanonicalPosTag.NN,		ac.biu.nlp.nlp.representation.CanonicalPosTag.NOUN);
		EOP_TO_BIU.put(CanonicalPosTag.NP,		ac.biu.nlp.nlp.representation.CanonicalPosTag.NOUN);
		EOP_TO_BIU.put(CanonicalPosTag.V,		ac.biu.nlp.nlp.representation.CanonicalPosTag.VERB);
		EOP_TO_BIU.put(CanonicalPosTag.ADJ,		ac.biu.nlp.nlp.representation.CanonicalPosTag.ADJECTIVE);
		EOP_TO_BIU.put(CanonicalPosTag.ADV,		ac.biu.nlp.nlp.representation.CanonicalPosTag.ADVERB);
		EOP_TO_BIU.put(CanonicalPosTag.PP,		ac.biu.nlp.nlp.representation.CanonicalPosTag.PREPOSITION);
		EOP_TO_BIU.put(CanonicalPosTag.CONJ,	ac.biu.nlp.nlp.representation.CanonicalPosTag.PREPOSITION);
		EOP_TO_BIU.put(CanonicalPosTag.PR,		ac.biu.nlp.nlp.representation.CanonicalPosTag.PRONOUN);
		EOP_TO_BIU.put(CanonicalPosTag.PUNC,	ac.biu.nlp.nlp.representation.CanonicalPosTag.PUNCTUATION);
		EOP_TO_BIU.put(CanonicalPosTag.ART,		ac.biu.nlp.nlp.representation.CanonicalPosTag.DETERMINER);
		EOP_TO_BIU.put(CanonicalPosTag.CARD,	ac.biu.nlp.nlp.representation.CanonicalPosTag.OTHER);
		EOP_TO_BIU.put(CanonicalPosTag.O,		ac.biu.nlp.nlp.representation.CanonicalPosTag.OTHER);
		EOP_TO_BIU.put(CanonicalPosTag.OTHER,	ac.biu.nlp.nlp.representation.CanonicalPosTag.OTHER);
	}

	public ac.biu.nlp.nlp.representation.CanonicalPosTag getBiuPosTag(CanonicalPosTag tag) {
		return EOP_TO_BIU.get(tag);
	}
	
	public CanonicalPosTag getEopPosTag(ac.biu.nlp.nlp.representation.CanonicalPosTag tag) {
		return BIU_TO_EOP.get(tag);
	}
}
