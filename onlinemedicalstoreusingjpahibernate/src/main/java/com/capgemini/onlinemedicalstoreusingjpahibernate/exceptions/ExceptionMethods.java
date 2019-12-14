package com.capgemini.onlinemedicalstoreusingjpahibernate.exceptions;

import com.capgemini.onlinemedicalstoreusingjpahibernate.factory.MedicalFactory;
import com.capgemini.onlinemedicalstoreusingjpahibernate.validations.MedicalValidations;

public class ExceptionMethods {
	static MedicalValidations medicalValidations = MedicalFactory.getMedicalValidations();
	

	// Exceptions Method for Number Validation
	public static Integer numberValidation(String num) throws MedicalExceptions {
		Integer num1 = medicalValidations.validateNumber(num);
		if (num1 != null) {
			return num1;
		} else {
			throw new MedicalExceptions("Invalid Input Format\nPlease Enter It In Number Format");
		}
	}

	// Exception method for contact number validator
	public static Long contactValidator(String mobileNumber) throws MedicalExceptions {
		Long mobileNumber1 = medicalValidations.contactValidate(mobileNumber);
		if (mobileNumber1 != null) {
			return mobileNumber1;
		}
		throw new MedicalExceptions("Please enter the 10 digit mobile number ");
	}

	// Method to check email format is valid or not
	public static String emailValidator(String email) throws MedicalExceptions {
		String emailId = medicalValidations.emailIdValidate(email);
		if (emailId != null) {
			return emailId;
		} else {
			throw new MedicalExceptions("Enter the Email in format like abc@gmail.com");
		}
	}
	
	//Method to check Card number during payment
	public Long cardNumberValidator(String cardNumber) throws MedicalExceptions {
		Long cardNumber1 = medicalValidations.cardNumberValidator(cardNumber);
		if (cardNumber1 != null) {
			return cardNumber1;
		}
		throw new MedicalExceptions("Please Enter 16 digit Card Number");	
	}
	
	//Method to check month range
	public Integer checkMonth(String month) throws MedicalExceptions {
		Integer month1 = medicalValidations.monthValidate(month);
		if (month1 != null) {
			return month1;
		}
		throw new MedicalExceptions("Please Enter Valid Month !!! Enter Month Between 1 to 12");
	}
	
	//Method check year format
	public static Integer checkYear(String year) throws MedicalExceptions {
		Integer num1 = medicalValidations.yearValidate(year);
		if (num1 != null) {
			return num1;
		} else {
			throw new MedicalExceptions("Year should be 4 digits");
		}
	}
	
	//Method to check CVV
	public static Integer checkCVV(String cvv) throws MedicalExceptions {
		Integer num1 = medicalValidations.checkCVV(cvv);
		if (num1 != null) {
			return num1;
		} else {
			throw new MedicalExceptions("CVV number should be 3 digits");
		}
	}
	
	//Method to check password validation
	public static String checkPassword(String password) throws MedicalExceptions {
		String password1 = medicalValidations.passwordValidate(password);
		if (password1 != null) {
			return password1;
		} else {
			throw new MedicalExceptions("Password must be Minimum of 8 Characters");
		}
	}
	
	//Method to check Character validation
	public static String charValidation(String character) throws MedicalExceptions {
		String character1 = medicalValidations.charValidate(character);
		if (character1 != null) {
			return character1;
		} else {
			throw new MedicalExceptions("Invalid Input Format\nPlease Enter Single character");
		}
	}

}//End of class
