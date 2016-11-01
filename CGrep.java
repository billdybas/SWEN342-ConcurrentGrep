import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CGrep {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletionService<Found> completionService = new ExecutorCompletionService<Found>(executor);

		// Display usage info if no args
		if (args.length == 0) {
			System.err.println("Usage: java CGrep pattern [file...]");
			return;
		}

		// Get the regex to match
		String regex = args[0];

		// Map between filenames and InputStreams that can read those files
		Map<String, InputStream> streams = new HashMap<String, InputStream>();

		if (args.length == 1) {
			// If there's no file names listed, we're using stdin

			// Use the empty string as the file name since actual files must have a non-empty name
			streams.put("", System.in);
		} else {
			// Otherwise, the rest of args is file names

			// Convert the file args to a List
			List<String> files = new ArrayList<String>(Arrays.asList(args));
			// Remove the Pattern argument
			files.remove(0);

			// Make FileInputStreams for each file
			for (String s: files) {
				try {
					streams.put(s, new FileInputStream(s));
				} catch (FileNotFoundException e) {
					System.err.println("Can't find file " + s + ". Skipping it.");
				}
			}
		}

		// Go through every stream and read its contents
		for (String name: streams.keySet()) {
			BufferedReader b = new BufferedReader(new InputStreamReader(streams.get(name)));

			List<String> contents = new ArrayList<String>();
			String line;
			try {
				while ((line = b.readLine()) != null) {
					contents.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Submit a new Callable to the CompletionService
			completionService.submit(new MatchedCallable(regex, name, contents));
		}

		for (int i = 0; i < streams.keySet().size(); i++) {
			try {
				// Get the next task's Future or wait until the Future has finished execution
				Future<Found> f = completionService.take();
				Found matches = f.get();

				// Print Out Each Match
				System.out.println("Matches for: " + (matches.getFilename() != "" ? matches.getFilename() : "stdin"));
				for (String match: matches.getList()){
					System.out.println(match);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		// Shutdown the ExecutorService
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
