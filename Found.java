import java.util.ArrayList;
import java.util.List;

public class Found {

	public final String fileName;
	private List<String> occurences;
	
	public Found(String filename){
		this.fileName = filename;
		this.occurences = new ArrayList<String>();
	}
	
	public void addOcc(int lineNumber, String text){
		occurences.add(lineNumber+" "+text);
	}
	
	public List<String> getList(){
		return occurences;
	}
	
}
