import datechooser.beans.DateChooserCombo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

public class Rentals extends JFrame {

    private JPanel rentalsPanel;
    private JTabbedPane tabbedPane1;
    private JTable rentalsTable;
    private JButton removeButton;
    private JButton returnButton;
    private JButton checkButton;
    private JComboBox movieBox;
    private JComboBox clientBox;
    private JButton addRentalButton;
    private DateChooserCombo dateUntil;

    public Rentals(JTable movies, JTable clients) {
        super("Rentals");
        this.setContentPane(rentalsPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,500);

        DefaultTableModel moviesTable = (DefaultTableModel) movies.getModel();
        DefaultTableModel clientsTable = (DefaultTableModel) clients.getModel();
        int rowCountMovies = moviesTable.getRowCount();
        int rowCountClients = clientsTable.getRowCount();

        for(int i=0;i<rowCountMovies;i++) {
            String title = String.valueOf(moviesTable.getValueAt(i,0));
            String year = String.valueOf(moviesTable.getValueAt(i,1));
            Object value = title+" - "+year;
            movieBox.addItem(value);
        }
        for(int i=0;i<rowCountClients;i++) {
            String name = String.valueOf(clientsTable.getValueAt(i,0));
            String surname = String.valueOf(clientsTable.getValueAt(i,1));
            Object value = name+" "+surname;
            clientBox.addItem(value);
        }

        String[] ColumnNames = {"Movie","Client","Rented until"};

        DefaultTableModel model = new DefaultTableModel(ColumnNames,0);
        rentalsTable.setModel(model);

        Object[] rent1 = {"Casablanca - 123","0","19-01-2023"};
        Object[] rent2 = {"Casablanca - 123","1","20-01-2023"};

        model.addRow(rent1);
        model.addRow(rent2);

        addRentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int movie = movieBox.getSelectedIndex();
                String title = String.valueOf(moviesTable.getValueAt(movie,0));
                int client = clientBox.getSelectedIndex();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date rentedUntilGet = dateUntil.getSelectedDate().getTime();
                Date todaysDateGet = new Date();
                String rentedUntil = dateFormat.format(rentedUntilGet);
                String todaysDate = dateFormat.format(todaysDateGet);
                if(rentedUntil.matches(todaysDate)) {
                    JOptionPane.showMessageDialog(rentalsPanel,"You cannot rent a movie with return due today");
                }
                else {
                    String addDate = dateFormat.format(rentedUntil);
                    Object[] rent = {title,client,rentedUntil};
                    model.addRow(rent);
                }
            }
        });
    }
}
