package info.spicyclient.spicyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class FileUtils {
	
	public static File mainDir = new File(System.getenv("APPDATA"), "MoneyHelper"),
			vaultTracker = new File(mainDir, "VaultTracker.json");
	
	public static String readFromFile(String path) throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(path);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader b = new BufferedReader(inputStreamReader);
		
		StringBuilder builder = new StringBuilder();
		
		String line;
		
		while ((line = b.readLine()) != null) {
			
			builder.append(line);
			
		}
		
		b.close();
		inputStreamReader.close();
		fileInputStream.close();
		
		return builder.toString();
		
	}
	
	public static String readFromFile(File file) throws IOException {
		return readFromFile(file.getAbsolutePath());
	}
	
	public static void writeJsonToFile(File file, Object obj) throws IOException {
		
		Gson gson = new Gson();
		
		if (!file.exists()) {
			
			file.createNewFile();
			
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(gson.toJson(obj).getBytes());
			outputStream.close();
			
		}
		
		FileOutputStream outputStream;
		outputStream = new FileOutputStream(file);
		outputStream.write(gson.toJson(obj).getBytes());
		outputStream.close();
		return;
		
	}
	
	public static Object readJsonFromFile(File file, Object obj) throws JsonSyntaxException, IOException {
		
		Gson gson = new Gson();
		
		return gson.fromJson(readFromFile(file), obj.getClass());
		
	}
	
}
