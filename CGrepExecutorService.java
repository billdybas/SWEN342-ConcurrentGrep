import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

public class CGrepExecutorService{

	private final ExecutorService executor;
	
	public CGrepExecutorService(ExecutorService executor){
		this.executor = executor;
	}

}
