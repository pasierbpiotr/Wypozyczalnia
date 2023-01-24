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

        Object[] movie1 = {"The Dark Knight",2008};
        Object[] movie2 = {"Inception",2010};
        Object[] movie3 = {"The Matrix",1999};
        Object[] movie4 = {"Star Wars: Episode V - The Empire Strikes Back",1980};
        Object[] movie5 = {"The Lord of The Rings: The Return of the King",2003};
        Object[] movie6 = {"The Lord of The Rings: The Fellowship of the Ring",2001};
        Object[] movie7 = {"Seven Samurai",1954};
        Object[] movie8 = {"Interstellar",2014};
        Object[] movie9 = {"Back to the Future",1985};
        Object[] movie10 = {"The Lion King",1994};

        model.addRow(movie1);
        model.addRow(movie2);
        model.addRow(movie3);
        model.addRow(movie4);
        model.addRow(movie5);
        model.addRow(movie6);
        model.addRow(movie7);
        model.addRow(movie8);
        model.addRow(movie9);
        model.addRow(movie10);

        moviesExtension = moviesTable;

        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleText.getText();
                String year = String.valueOf(yearBox.getSelectedIndex()+1930);
                if(title.isBlank()) {
                    JOptionPane.showMessageDialog(moviesPanel,"You need to enter a title.");
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
