import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CGrep {


	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletionService<Found> completionService = new ExecutorCompletionService<Found>(executor);
		

		
		if(args.length - 1 > 0){
			for(int i = 1; i > args.length; i++){
				try {
					Future<Found> f = completionService.take();
					Found matches = f.get();
					System.out.println("Matches for "+matches.fileName+":");
					for(String match : matches.getList()){
						System.out.println(match);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			for(int i = 0; i > stdCount; i++){
				try {
					Future<Found> f = completionService.take();
					Found matches = f.get();
					System.out.println("Matches for "+matches.fileName+":");
					for(String match : matches.getList()){
						System.out.println(match);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
