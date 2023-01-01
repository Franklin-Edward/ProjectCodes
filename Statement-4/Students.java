import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.IOException;

public class Students {
  public static void main(String[] args) throws IOException {
    Map<String,Integer> students = new HashMap<>();
    Scanner scan = new Scanner(System.in);
    String fileName;
    outer:while(true){
        System.out.println("Enter the File Name in .txt format:");
        fileName=scan.nextLine();
        try{
            FileUtils.checkFileFormat(fileName);
            break;
        } catch (FileFormatException e) {
            System.out.println(e.getMessage());
            continue outer;
        }
    }
    
    
    try{
        BufferedReader read = new BufferedReader(new FileReader(fileName));
        String line;
        while((line = read.readLine())!= null){
            String[] words = line.split(" ");
            try{
                students.put(words[0],Integer.parseInt(words[1]));
            }
            catch(NumberFormatException e) {
                System.out.println("Error: " + e.getMessage());
            }  
        }
    }catch(IOException e){
        System.out.println("Error while reading the file");
        return;
    }
    System.out.println("Sorted By Name");
    Map<String,Integer> sortByName = new TreeMap<>(students);
    sortByName.forEach((key,value)->System.out.println(key+" "+value));

    System.out.println("-------------------------------");
    
    System.out.println("Sorted By Percentage");
    Map<String,Integer> sortByPercentage = students.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
    sortByPercentage.forEach((key,value)->System.out.println(key+" "+value));
  }
}
