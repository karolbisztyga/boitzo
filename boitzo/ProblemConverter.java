package boitzo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.json.JSONObject;

public class ProblemConverter {

	public Problem primitiveToDual() {
		return null;
	}
	
	public Problem dualToPrimitive() {
		return null;
	}
	
	public void saveToFile(String fileName, Problem problem) {
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			String content = new Date().toString() + "\n" + new JSONObject(problem).toString() + "\n";
			Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.APPEND);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
