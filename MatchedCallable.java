
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class MatchedCallable implements Callable {
	
	private String someRegex;
	private String filename;
	private List<String> lines;


	public MatchedCallable(String regexPattern ,String file,List<String> lineList) {
		this.someRegex = regexPattern;
		this.filename = file;
		this.lines = lineList;
	}
	
	@Override
	public Object call() throws Exception {
		int count = 0;
		Found matchingObject = new Found(filename);
		// TODO Auto-generated method stub
		for(String line: this.lines) {
			if(Pattern.matches(someRegex, line) ) {
				matchingObject.addOcc(count,line);
				count ++;
			}
		}
		return matchingObject;
	}
}