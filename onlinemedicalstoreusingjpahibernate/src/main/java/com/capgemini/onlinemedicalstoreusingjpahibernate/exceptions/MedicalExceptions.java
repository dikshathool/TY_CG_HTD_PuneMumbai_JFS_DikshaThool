package com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions;

public class MedicalExceptions extends Exception {

	private static final long serialVersionUID = 1L;

	public MedicalExceptions(String s) {
		System.err.println(s);
	}

}
