package kodlama.io.hrms2.core.utilities.results;

public class SuccesResult extends Result {
	
	public SuccesResult() {
		super(true);
	}
	
	public SuccesResult( String message) {
		super(true, message);
	}
}
