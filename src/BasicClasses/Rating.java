
package BasicClasses;

public class Rating {

	public static long DEFAULT_RATING = 1200;

//	public static long calculateRating

	private long value;

	public Rating( long value ) {
		this.setValue( value );
	}

	@Override
	public String toString() {
		return Long.toString( this.getValue() );
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
