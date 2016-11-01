import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class MatchedCallable implements Callable<Found> {

	private String regex;
	private String filename;
	private List<String> content;

	public MatchedCallable(String regex, String filename, List<String> content) {
		this.regex = regex;
		this.filename = filename;
		this.content = content;
	}

	@Override
	public Found call() throws Exception {

		Found match = new Found(filename);

		int lineNumber = 0;
		for (String line: content) {
			if (Pattern.matches(regex, line)) {
				match.addOccurance(lineNumber, line);
			}
			lineNumber++;
		}

		return match;
	}
}
