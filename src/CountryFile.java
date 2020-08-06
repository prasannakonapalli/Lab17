import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CountryFile {
	public static void main(String[] args) {

		Path relativePath = Paths.get("population.txt");
		System.out.println(relativePath);

		if (!Files.exists(relativePath)) {
			try {
				Files.createFile(relativePath);
			} catch (IOException e) {
				System.out.println("unable to create file" + relativePath);
			}
		}
		System.out.println(Files.exists(relativePath));

	}

}
