package queryProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import relationalCategory.Row;
import relationalCategory.Table;
import tree.Tree;

public class SQLquery {

    //private String query;
    private String[] attributes;
    private Table persons;

    public SQLquery() throws FileNotFoundException {

        Scanner reader = new Scanner(new File("C:\\Users\\Valter Uotila\\Desktop\\demo-system\\src\\persons\\Person.csv"));

        this.attributes = reader.nextLine().split(",");
        this.persons = new Table("Persons", this.attributes);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");
            Row record = new Row(this.attributes, line);
            this.persons.addRow(record);
        }

    }

    public Tree loadDemoQueryTree() {
        String[] attributes2 = {"name", "surname"};
        Table answer = new Table("answer", attributes2);
        for (Row row : this.persons.getRows()) {
            if (row.getRow()[0].equals("933")) {
                String[] names = {row.getRow()[1], row.getRow()[2]};
                Row answerRow = new Row(attributes2, names);
                answer.addRow(answerRow);
            }
        }
        return answer.TabletoTree();
    }

}
