package com.capgemini.onlinemedicalstorewithjdbc.validations;

public interface MedicalValidations {

	// Regex
	public Integer validateNumber(String id);

	public String emailIdValidate(String email);

	public Double doubleValidate(String number);

	public Long contactValidate(String contact);

	public String passwordValidate(String password);

	public String charValidate(String character);

	public Long cardNumberValidator(String cardNumber);

	public Integer monthValidate(String month);

	public Integer yearValidate(String year);

	public Integer checkCVV(String cvv);

}// End of interface
