/***********************************************************************
 Class: CMSC204 CRN 22378
 Program: Assignment # 2
 Instructor: Professor Alexander
 Description: Queue Overflow Exception 
 Due: 09/30/2020
 I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
 Anthony Liu
************************************************************************/
/**
 * QueueOverflowException
 * Thrown when queue is full 
 * 
 * @author Anthony Liu
 *
 */
public class QueueOverflowException extends Exception {

	QueueOverflowException(){
		super("Queue is full.");
		
	}
}
