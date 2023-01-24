import datechooser.beans.DateChooserCombo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Calendar;
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

        Object[] rent1 = {"Casablanca - 123","Piotr Pasierb","19-01-2023"};
        Object[] rent2 = {"Casablanca - 123","Natalia Rzeszutek","20-01-2023"};

        model.addRow(rent1);
        model.addRow(rent2);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date todaysDateGet = new Date();
        String todaysDate = dateFormat.format(todaysDateGet);

        addRentalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object movie = movieBox.getSelectedItem();

                Object client = clientBox.getSelectedItem();

                Date rentedUntilGet = dateUntil.getSelectedDate().getTime();
                String rentedUntil = dateFormat.format(rentedUntilGet);

                if(rentedUntil.compareTo(todaysDate)<=0) {
                    JOptionPane.showMessageDialog(rentalsPanel,"Incorrect date chosen");
                }
                else {
                    String addDate = String.valueOf(rentedUntil);
                    Object[] rent = {movie,client,rentedUntil};
                    model.addRow(rent);
                }
            }
        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeRow(rentalsTable.getSelectedRow());
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCountRentals = rentalsTable.getRowCount();
                for(int i=0;i<rowCountRentals;i++) {
                    String dateOfReturn = String.valueOf(rentalsTable.getValueAt(i,2));
                    String clientInfo = String.valueOf(rentalsTable.getValueAt(i,1));
                    String movieInfo = String.valueOf(rentalsTable.getValueAt(i,0));
                    if(todaysDate.compareTo(dateOfReturn)>-2) {
                        JOptionPane.showMessageDialog(rentalsPanel,"Client: "+clientInfo+" has to return the movie: "+movieInfo);
                    }
                }
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
