import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CGrep {


	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletionService<Found> completionService = new ExecutorCompletionService<Found>(executor);
		

	}

}
