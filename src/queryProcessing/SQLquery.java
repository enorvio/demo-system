package queryProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import relationalCategory.Row;
import relationalCategory.Table;

public class SQLquery {

    private String[] attributes;
    private Table persons;

    public SQLquery(File file1) throws FileNotFoundException {
        Scanner reader = new Scanner(file1);
        this.attributes = reader.nextLine().split(",");
        this.persons = new Table("Persons", this.attributes);
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",");
            Row record = new Row(this.attributes, line);
            this.persons.addRow(record);
        }
    }

    public Table loadDemoQuery(String[] attributes2, String search_id) {
        Table answer = new Table("answer", attributes2);
        for (Row row : this.persons.getRows()) {
            if (row.getRow()[0].equals(search_id)) {
                String[] names = {row.getRow()[0], row.getRow()[1], row.getRow()[2]};
                Row answerRow = new Row(attributes2, names);
                answer.addRow(answerRow);
            }
        }
        return answer;
    }
}
