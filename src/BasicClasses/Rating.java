
package BasicClasses;

/*
Har Playeri ye rating daare, oonja miaam az in celass estefaade mikonim
 */

public class Rating {

//	Defaut-e rating-e har kasi in meghdaare...
	public static long DEFAULT_RATING = 1200;

//	public static long calculateRating

//	Meghdaar-e fe'li-e rating
	private long value;

//	Constructor
	public Rating( long value ) {
		this.setValue( value );
	}

//	toString
	@Override
	public String toString() {
		return Long.toString( this.getValue() );
	}

//	Getters and Setters

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
