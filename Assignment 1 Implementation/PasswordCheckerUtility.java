import java.util.ArrayList;

public class PasswordCheckerUtility {

	public PasswordCheckerUtility() {
	}

	/**
	 * Compare equality of two passwords
	 * @param password password string to be checked for
	 * @param passwordConfirm passwordConfirm string to be checked against password for
	 * @throws UnmatchedException thrown if not same (case sensitive)
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {

		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}

	}

	/**
	 * Compare equality of two passwords
	 * @param password password string to be checked for
	 * @param passwordConfirm passwordConfirm string to be checked against password for
	 * @return true if both same (case sensitive), false otherwise
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {

		if (password.equals(passwordConfirm)) {
			return true;
		}
		return false;

	}

	/**
	 * This method will accept an ArrayList of passwords as the parameter and return an ArrayList
	 *  with the status of any invalid passwords (weak passwords are not considered invalid). The ArrayList of invalid passwords 
	 *  will be of the following format: password BLANK message of the exception thrown
	 * @param passwords list of passwords
	 * @return of invalid passwords in the correct format
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {

		int i = 0;
		ArrayList<String> invalidPasswordList = new ArrayList<String>();
		while (i < passwords.size()) {
			try {
				if (isValidPassword(passwords.get(i))) {

				}
			} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException
					| NoSpecialCharacterException | InvalidSequenceException e) {
				invalidPasswordList.add(passwords.get(i) + " -> " + e.getMessage());
				System.out.println(passwords.get(i) + " -> " + e.getMessage());
			}
			i++;
		}
		return invalidPasswordList;
	}

	/**
	 * checks if the password contains 6 to 9 characters
	 * @param password password string to be checked for
	 * @return true if password contains 6 to 9 characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {

		if (password.length() <= 9 || password.length() >= 6) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks the password Digit requirement - Password must contain a numeric character
	 * @param password password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException thrown if does not meet Digit requirement
	 */
	public static boolean hasDigit(String password) throws NoDigitException {

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
				return true;
			}
		}

		throw new NoDigitException();
	}

	/**
	 * Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
	 * @param password password string to be checked for lowercase requirement
	 * @return true if meets lowercase requirement
	 * @throws NoLowerAlphaException thrown if does not meet lowercase requirement
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}

	/**
	 * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password password string to be checked for Sequence requirement
	 * @return false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException thrown if meets Sequence requirement
	 */
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {

		int num = 0;
		for (int i = 0; i < password.length(); i++) {
			if (num < 2 && i + 1 < password.length() && password.charAt(i) == password.charAt(i + 1))
				num++;
			else if (num < 2 && i + 1 < password.length() && password.charAt(i) != password.charAt(i + 1))
				num = 0;
		}

		if (num < 2) {
			return true;
		} else {
			throw new InvalidSequenceException();
		}
	}

	/**
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password password string to be checked for SpecialCharacter requirement
	 * @return true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {

		for (int i = 0; i < password.length(); i++) {
			if ((password.charAt(i) >= 58 && password.charAt(i) <= 64)
					|| (password.charAt(i) >= 32 && password.charAt(i) <= 47)
					|| (password.charAt(i) >= 91 && password.charAt(i) <= 96)
					|| (password.charAt(i) >= 123 && password.charAt(i) <= 126)) {
				return true;
			}
		}
		throw new NoSpecialCharacterException();
	}

	/**
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException thrown if does not meet alpha character requirement
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {

		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
				return true;
			}
		}
		throw new NoUpperAlphaException();
	}

	/**
	 * Checks the password length requirement - The password must be at least 6 characters long
	 * @param password password string to be checked for length
	 * @return true if meets minimum length requirement
	 * @throws LengthException thrown if does not meet minimum length requirement
	 */
	public static boolean isValidLength(String password) throws LengthException {
		System.out.println();
		int min = 6;
		if (password.length() >= min) {
			return true;
		} else {
			throw new LengthException();
		}
	}

	/**
	 * Return true if valid password (follows all the following rules), returns false if an invalid password 
	 * 1. At least 6 characters long - 2. At least 1 numeric character- 3. At least 1 uppercase alphabetic character - 
	 * 4. At least 1 lowercase alphabetic character - 5. At least 1 special character - 
	 * 6. No more than 2 of the same character in a sequence
	 * @param password  string to be checked for validity
	 * @return true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException thrown if no uppercase alphabetic
	 * @throws NoLowerAlphaException thrown if no lowercase alphabetic
	 * @throws NoDigitException thrown if no digit
	 * @throws NoSpecialCharacterException thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException thrown if more than 2 of same character.
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
			NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		try {
			if (PasswordCheckerUtility.isValidLength(password) == true
					&& PasswordCheckerUtility.hasUpperAlpha(password) == true
					&& PasswordCheckerUtility.hasLowerAlpha(password) == true
					&& PasswordCheckerUtility.hasDigit(password) == true
					&& PasswordCheckerUtility.hasSpecialChar(password) == true
					&& PasswordCheckerUtility.NoSameCharInSequence(password) == true) {
				return true;
			}
		} catch (LengthException e) {

			throw new LengthException();
		} catch (NoUpperAlphaException e) {

			throw new NoUpperAlphaException();
		} catch (NoLowerAlphaException e) {

			throw new NoLowerAlphaException();
		} catch (NoDigitException e) {

			throw new NoDigitException();
		} catch (NoSpecialCharacterException e) {

			throw new NoSpecialCharacterException();
		} catch (InvalidSequenceException e) {

			throw new InvalidSequenceException();
		}
		return false;

	}

	/**
	 * Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param password string to be checked if weak password
	 * @return false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
	 * @throws WeakPasswordException if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID.
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {

		if (password.length() <= 9) {

			throw new WeakPasswordException();
		} else {
			return false;
		}

	}

}