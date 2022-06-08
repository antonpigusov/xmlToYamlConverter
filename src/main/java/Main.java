import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();
        System.out.println("Please, enter .xml file path");
        File xml = new File(new Scanner(System.in).nextLine());
        Map<String, Object> objectTree = xmlMapper.readValue(xml, new TypeReference<Map<String, Object>>() {});

        try {
            File yaml = new File("output.yaml");
            if (yaml.createNewFile()) {
                System.out.println("File created: " + yaml.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.getMessage();
        }

        Yaml yaml = new Yaml();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.yaml"));
        String dump = yaml.dump(objectTree);
        //System.out.println(dump);
        bufferedWriter.write(dump);

        bufferedWriter.close();
    }
}
