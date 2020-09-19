/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 2
 Instructor: Professor Alexander
 Description: Invalid Notation Format Exception class
 Due: 09/30/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/

/**
 * Thrown when notation format is invalid 
 * @author Anthony Liu
 *
 */
public class InvalidNotationFormatException extends Exception {

	InvalidNotationFormatException() {
		super("Invalid Notation Format");
	}
}
