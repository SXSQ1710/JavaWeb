package edu.gdut.imis.vote.model;

import java.io.Serializable;

/**
 * @author Administrator JavaBean-style
 */
public class VoteModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int one;
	private int two;
	private int three;

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}

	public int getThree() {
		return three;
	}

	public void setThree(int three) {
		this.three = three;
	}

}
