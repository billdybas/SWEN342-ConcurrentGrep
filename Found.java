import java.util.ArrayList;
import java.util.List;

public class Found {

	private final String filename;
	private List<String> occurences = new ArrayList<String>();

	public Found(String filename){
		this.filename = filename;
	}

	public void addOccurance(int lineNumber, String text){
		occurences.add(lineNumber + " " + text);
	}

	public String getFilename() {
		return filename;
	}

	public List<String> getList(){
		return occurences;
	}
}
