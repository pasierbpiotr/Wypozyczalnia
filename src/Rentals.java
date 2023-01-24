import datechooser.beans.DateChooserCombo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;

public class Rentals extends JFrame {

    private JPanel rentalsPanel;
    private JTabbedPane tabbedPane1;
    private JTable rentalsTable;
    private JTable viewClientTable;
    private JButton removeButton;
    private JButton returnButton;
    private JButton checkButton;
    private JComboBox movieBox;
    private JComboBox clientBox;
    private JButton addRentalButton;
    private DateChooserCombo dateUntil;
    private JButton clientCheckButton;
    private JComboBox clientCheckBox;

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

        for(int i=0;i<rowCountClients;i++) {
            String name = String.valueOf(clientsTable.getValueAt(i,0));
            String surname = String.valueOf(clientsTable.getValueAt(i,1));
            Object value = name+" "+surname;
            clientCheckBox.addItem(value);
        }

        String[] ColumnNames = {"Movie","Client","Rented until"};

        DefaultTableModel modelViewRentals = new DefaultTableModel(ColumnNames,0);
        DefaultTableModel modelViewClient = new DefaultTableModel(ColumnNames,0);
        rentalsTable.setModel(modelViewRentals);
        viewClientTable.setModel(modelViewClient);

        Object[] rent1 = {"The Dark Knight - 2008","Krzysztof Kolumb","19-01-2023"};
        Object[] rent2 = {"Inception - 2010","Krzysztof Kolumb","19-01-2023"};
        Object[] rent3 = {"The Matrix - 1999","Natalia Rzeszutek","20-01-2023"};
        Object[] rent4 = {"Seven Samurai - 1954","Natalia Rzeszutek","20-01-2023"};
        Object[] rent5 = {"Interstellar - 2014","Natalia Rzeszutek","20-01-2023"};
        Object[] rent6 = {"Interstellar - 2014","Piotr Pasierb","23-01-2023"};
        Object[] rent7 = {"Seven Samurai - 1954","Piotr Pasierb","23-01-2023"};
        Object[] rent8 = {"The Matrix - 1999","Piotr Pasierb","23-01-2023"};
        Object[] rent9 = {"Back to the Future - 1985","Patrycja Kolumb","26-01-2023"};

        modelViewRentals.addRow(rent1);
        modelViewRentals.addRow(rent2);
        modelViewRentals.addRow(rent3);
        modelViewRentals.addRow(rent4);
        modelViewRentals.addRow(rent5);
        modelViewRentals.addRow(rent6);
        modelViewRentals.addRow(rent7);
        modelViewRentals.addRow(rent8);
        modelViewRentals.addRow(rent9);

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
//                    String addDate = String.valueOf(rentedUntil);
                    Object[] rent = {movie,client,rentedUntil};
                    modelViewRentals.addRow(rent);
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCountRentals = rentalsTable.getRowCount();
                for(int i=0;i<rowCountRentals;i++) {

                    String dateOfReturn = String.valueOf(rentalsTable.getValueAt(i, 2));
                    String clientInfo = String.valueOf(rentalsTable.getValueAt(i, 1));
                    String movieInfo = String.valueOf(rentalsTable.getValueAt(i, 0));

                    Date untilDate;
                    try {
                        untilDate = new SimpleDateFormat("dd-MM-yyyy").parse(dateOfReturn);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    Date dateToday;
                    try {
                        dateToday = new SimpleDateFormat("dd-MM-yyyy").parse(todaysDate);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    int diff = dateToday.getDate() - untilDate.getDate();

                    if(diff>0) {
                        JOptionPane.showMessageDialog(rentalsPanel,"Client: "+clientInfo+" has to return the movie: "+movieInfo+"\nClient is late by "+Math.abs(diff)+" days and the fine will be: "+(diff*3)+" zł");
                    } else if(diff>=-2 && diff<=0) {
                        JOptionPane.showMessageDialog(rentalsPanel,"Client: "+clientInfo+" has to return the movie: "+movieInfo+" in "+Math.abs(diff)+" days");
                    }
                }
            }
        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelViewRentals.removeRow(rentalsTable.getSelectedRow());
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        clientCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelViewClient.setRowCount(0);
                int rowCountRentals = rentalsTable.getRowCount();
                for(int i=0;i<rowCountRentals;i++) {

                    String movieInfo = String.valueOf(rentalsTable.getValueAt(i,0));
                    String clientInfo = String.valueOf(rentalsTable.getValueAt(i,1));
                    String dateInfo = String.valueOf(rentalsTable.getValueAt(i,2));

                    Object client = clientCheckBox.getSelectedItem();
                    String chosenClient = String.valueOf(client);

                    Object[] info = {movieInfo,clientInfo,dateInfo};
                    if(clientInfo.equals(chosenClient)) {
                        modelViewClient.addRow(info);
                    }
                }
            }
        });
    }
}
