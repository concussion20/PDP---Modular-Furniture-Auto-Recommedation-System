package common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileHelper, read file and write file.
 * @author Chang Zhou
 */
public class FileHelper {

  /**
   * read from file.
   * @param filePath input file path
   * @return file content as String
   */
  public static String readJsonFile(final String filePath) {
    String jsonStr = "";
    try {
      final FileReader fileReader = new FileReader(filePath);
      final BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        jsonStr += line;
      }
      bufferedReader.close();
      fileReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonStr;
  }

  /**
   * write to target file.
   * @param filePath target file path
   * @param jsonStr output content
   */
  public static void writeJsonFile(final String filePath, final String jsonStr) {
    try {
      final File file = new File(filePath);

      //if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      final FileWriter fileWritter = new FileWriter(file.getPath());
      final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
      bufferWritter.write(jsonStr);
      bufferWritter.close();
      fileWritter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
