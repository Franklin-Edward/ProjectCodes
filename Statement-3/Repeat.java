import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Repeat {
  public static void main(String[] args) throws IOException {
    Map<String,Integer> wordCount = new HashMap<>();
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
            for(String word:words){
                if(!wordCount.containsKey(word)){
                    wordCount.put(word,0);
                }
                else{
                    wordCount.put(word,wordCount.get(word)+1);
                }
            }
        }
    }catch(IOException e){
        System.out.println("Error while reading the file");
        return;
    }
    System.out.println("The Top 3 Repeated Words");
    Map<String,Integer> top3 = wordCount.entrySet().stream().sorted((word1,word2)-> word2.getValue().compareTo(word1.getValue())).limit(3)
                                        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
    top3.forEach((key,value)->System.out.println(key+" "+value));
  }
}
