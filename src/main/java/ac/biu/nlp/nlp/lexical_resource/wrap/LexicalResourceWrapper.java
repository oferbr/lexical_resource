package ac.biu.nlp.nlp.lexical_resource.wrap;

import java.util.ArrayList;
import java.util.List;

import ac.biu.nlp.nlp.representation.UnspecifiedPartOfSpeech;
import ac.biu.nlp.nlp.representation.UnsupportedPosTagStringException;

import eu.excitementproject.eop.common.configuration.CommonConfig;
import eu.excitementproject.eop.common.exception.ComponentException;
import eu.excitementproject.eop.common.exception.ConfigurationException;
import eu.excitementproject.eop.core.component.lexicalknowledge.LexicalResource;
import eu.excitementproject.eop.core.component.lexicalknowledge.LexicalResourceException;
import eu.excitementproject.eop.core.component.lexicalknowledge.LexicalRule;
import eu.excitementproject.eop.core.representation.parsetree.CanonicalPosTag;
import eu.excitementproject.eop.core.representation.parsetree.PartOfSpeech;

//TODO Oct 2012: temporary solution for using BIU's resource as a EOP resource. 
/**
 * Temporary solution for using a BIU LexicalResource as a EOP LexicalResource.
 * Gets an existing BIU instance of a BIU LexicalResource in the constructor, and from that point
 * functions as a EOP LexicalResource.
 * @author Ofer Bronstein
 *
 * @param <I>
 */
public class LexicalResourceWrapper<I extends ac.biu.nlp.nlp.lexical_resource.RuleInfo> implements LexicalResource<I> {

	public LexicalResourceWrapper(ac.biu.nlp.nlp.lexical_resource.LexicalResource<I> innerResource) {
		this.innerResource = innerResource;
	}
	
	@Override
	public void initialize(CommonConfig config) throws ConfigurationException,
			ComponentException {
		// We do not support initialize()
		throw new ComponentException("Components from BIU-infrastructure do not support initialize().");
	}

	@Override
	public String getComponentName() {
		// We do not support getComponentName()
		return "Components from BIU-infrastructure do not support getComponentName().";
	}

	@Override
	public String getInstanceName() {
		// We do not support getInstanceName()
		return "Components from BIU-infrastructure do not support getInstanceName().";
	}

	@Override
	public List<LexicalRule<? extends I>> getRulesForLeft(String lemma,
			PartOfSpeech pos) throws LexicalResourceException {

		ac.biu.nlp.nlp.representation.PartOfSpeech innerPos;
		List<ac.biu.nlp.nlp.lexical_resource.LexicalRule<? extends I>> innerResult;
		List<LexicalRule<? extends I>> result;

		try {
			
			innerPos =    getInnerPOS(pos);
			innerResult = innerResource.getRulesForLeft(lemma, innerPos);
			result =      getOuterRules(innerResult);
			
		}
		catch (UnsupportedPosTagStringException e) {
			throw getOuterResourceException(e);
		}
		catch (eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException e) {
			throw getOuterResourceException(e);
		}
		catch (ac.biu.nlp.nlp.lexical_resource.LexicalResourceException e) {
			throw getOuterResourceException(e);
		}
		
		return result;		
	}

	@Override
	public List<LexicalRule<? extends I>> getRulesForRight(String lemma,
			PartOfSpeech pos) throws LexicalResourceException {

		ac.biu.nlp.nlp.representation.PartOfSpeech innerPos;
		List<ac.biu.nlp.nlp.lexical_resource.LexicalRule<? extends I>> innerResult;
		List<LexicalRule<? extends I>> result;

		try {
			
			innerPos =    getInnerPOS(pos);
			innerResult = innerResource.getRulesForRight(lemma, innerPos);
			result =      getOuterRules(innerResult);
			
		}
		catch (UnsupportedPosTagStringException e) {
			throw getOuterResourceException(e);
		}
		catch (eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException e) {
			throw getOuterResourceException(e);
		}
		catch (ac.biu.nlp.nlp.lexical_resource.LexicalResourceException e) {
			throw getOuterResourceException(e);
		}
		
		return result;		
	}

	@Override
	public List<LexicalRule<? extends I>> getRules(String leftLemma,
			PartOfSpeech leftPos, String rightLemma, PartOfSpeech rightPos)
			throws LexicalResourceException {

		ac.biu.nlp.nlp.representation.PartOfSpeech innerLeftPos;
		ac.biu.nlp.nlp.representation.PartOfSpeech innerRightPos;
		List<ac.biu.nlp.nlp.lexical_resource.LexicalRule<? extends I>> innerResult;
		List<LexicalRule<? extends I>> result;

		try {
			
			innerLeftPos =  getInnerPOS(leftPos);
			innerRightPos = getInnerPOS(rightPos);
			innerResult =   innerResource.getRules(leftLemma, innerLeftPos, rightLemma, innerRightPos);
			result =        getOuterRules(innerResult);
			
		}
		catch (UnsupportedPosTagStringException e) {
			throw getOuterResourceException(e);
		}
		catch (eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException e) {
			throw getOuterResourceException(e);
		}
		catch (ac.biu.nlp.nlp.lexical_resource.LexicalResourceException e) {
			throw getOuterResourceException(e);
		}
		
		return result;		
	}

	
	/**
	 * Converts a list of rules (a return value from some of the methdos in LexicalResource)
	 * from the BIU version to the EOP version.
	 * @param innerResult
	 * @return
	 * @throws LexicalResourceException
	 * @throws eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException
	 */
	private List<LexicalRule<? extends I>> getOuterRules(
			List<ac.biu.nlp.nlp.lexical_resource.LexicalRule<? extends I>> innerResult) throws LexicalResourceException, eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException {
		
		LexicalRule<? extends I> outerRule;
		List<LexicalRule<? extends I>> result = new ArrayList<LexicalRule<? extends I>>();
		for (ac.biu.nlp.nlp.lexical_resource.LexicalRule<? extends I> rule : innerResult) {
			PartOfSpeech outerLeftPos  = getOuterPOS(rule.getLPos());
			PartOfSpeech outerRightPos = getOuterPOS(rule.getRPos());
			outerRule = new LexicalRule<I>(
					rule.getLLemma(),
					outerLeftPos, 
					rule.getRLemma(),
					outerRightPos,
					rule.getConfidence(),
					rule.getRelation(),
					rule.getResourceName(),
					rule.getInfo());
			result.add(outerRule);
		}
		return result;
	}

	/**
	 * Converts a PartOfSpeech from the EOP version to the BIU version.
	 * @param pos
	 * @return
	 * @throws UnsupportedPosTagStringException
	 */
	private ac.biu.nlp.nlp.representation.PartOfSpeech getInnerPOS(PartOfSpeech pos) throws UnsupportedPosTagStringException {
		CanonicalPosTag outerTag = pos.getCanonicalPosTag();
		ac.biu.nlp.nlp.representation.CanonicalPosTag innerTag = POS_MAPPING.getBiuPosTag(outerTag);
		return new UnspecifiedPartOfSpeech(innerTag);
	}
	
	/**
	 * Converts a PartOfSpeech from the BIU version to the EOP version.
	 * @param pos
	 * @return
	 * @throws eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException
	 */
	private PartOfSpeech getOuterPOS(ac.biu.nlp.nlp.representation.PartOfSpeech pos) throws eu.excitementproject.eop.core.representation.parsetree.UnsupportedPosTagStringException {
		ac.biu.nlp.nlp.representation.CanonicalPosTag innerTag = pos.getCanonicalPosTag();
		CanonicalPosTag outerTag = POS_MAPPING.getEopPosTag(innerTag);
		return new ac.biu.nlp.nlp.lexical_resource.wrap.UnspecifiedPartOfSpeech(outerTag);
	}
	
	/**
	 * converts an exception to the EOP version of LexicalResourceException.
	 * @param e
	 * @return
	 */
	private LexicalResourceException getOuterResourceException(Exception e) {
		return new LexicalResourceException("Got Exception", e);
	}


	private ac.biu.nlp.nlp.lexical_resource.LexicalResource<I> innerResource;
	private static final PosMapping POS_MAPPING = new PosMapping();
}
