import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movies extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel moviesPanel;
    public JTable moviesTable;
    private JButton returnButton;
    private JButton removeButton;
    private JTextField titleText;
    private JComboBox yearBox;
    private JButton addMovieButton;
    public static JTable moviesExtension;


    public Movies() {
        super("Movies");
        this.setContentPane(moviesPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,500);

        String[] ColumnNames = {"Title","Year"};

        DefaultTableModel model = new DefaultTableModel(ColumnNames,0);
        moviesTable.setModel(model);

        Object[] movie1 = {"Casablanca",123};
        Object[] movie2 = {"Casablanca",123};

        model.addRow(movie1);
        model.addRow(movie2);

        moviesExtension = moviesTable;

        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleText.getText();
                String year = String.valueOf(yearBox.getSelectedIndex()+1930);
                if(title.isBlank()) {
                    JOptionPane.showMessageDialog(moviesPanel,"You need to enter a title");
                    }
                else {
                    Object[] movie = {title,year};
                    model.addRow(movie);
                    titleText.setText("");
                    yearBox.setSelectedIndex(0);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedItem = moviesTable.getSelectedRow();
                model.removeRow(selectedItem);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
