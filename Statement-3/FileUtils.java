
public class FileUtils {

  public static void checkFileFormat(String fileName) throws FileFormatException {
    if (!fileName.endsWith(".txt")) {
      throw new FileFormatException("Error: file must have a .txt extension");
    }
  }
}