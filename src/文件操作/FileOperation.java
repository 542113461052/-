package 文件操作;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {
    public void exportTable(JTable table, JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            File file1 = new File(fileChooser.getSelectedFile().toString());
            TableModel model = table.getModel();
            BufferedWriter bWriter = null;
            try {
                bWriter = new BufferedWriter(new FileWriter(file1));
                for (int i = 0; i < model.getColumnCount(); i++) {
                    bWriter.write(model.getColumnName(i));
                    bWriter.write("\t");
                }
                bWriter.newLine();
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        bWriter.write(model.getValueAt(i, j).toString());
                        bWriter.write("\t");
                    }
                    bWriter.newLine();
                }
                bWriter.close();
            } catch (IOException e) {
            }
        }


    }
}
