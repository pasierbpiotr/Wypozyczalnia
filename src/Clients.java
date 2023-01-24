import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clients extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel clientsPanel;
    private JTable clientsTable;
    private JButton returnButton;
    private JButton removeButton;
    private JTextField nameText;
    private JTextField surnameText;
    private JTextField address1Text;
    private JTextField address2Text;
    private JTextField pnumberText;
    private JButton addClientButton;
    public static JTable clientsExtension;

    public Clients() {
        super("Clients");
        this.setContentPane(clientsPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,500);

        String[] ColumnNames = {"Name","Surname","Address 1","Address 2","Phone number"};

        DefaultTableModel model = new DefaultTableModel(ColumnNames,0);
        clientsTable.setModel(model);

        Object[] client1 = {"Piotr","Pasierb","Lutoryz 413","36-040 Boguchwala","881655214"};
        Object[] client2 = {"Natalia","Rzeszutek","Zaczernie 1051B","35-540 Trzebownisko","882413232"};
        Object[] client3 = {"Krzysztof","Kolumb","Rzeszów Św.Wincentego 34","36-342 Rzeszów","123456789"};
        Object[] client4 = {"Jerzy","Tajemniczy","Terliczka 51C","33-320 Trzebownisko","234567890"};
        Object[] client5 = {"Patrycja","Kolumb","Józefów 23","25-344 Józefów","345678901"};

        model.addRow(client1);
        model.addRow(client2);
        model.addRow(client3);
        model.addRow(client4);
        model.addRow(client5);

        clientsExtension = clientsTable;


        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                String surname = surnameText.getText();
                String address1 = address1Text.getText();
                String address2 = address2Text.getText();
                String pnumber = pnumberText.getText();
                if(name.isBlank() || surname.isBlank() || address1.isBlank() || address2.isBlank() || pnumber.isBlank()) {
                    JOptionPane.showMessageDialog(clientsPanel,"The text fields cannot be left empty.");
                }
                else {
                    Object[] client = {name,surname,address1,address2,pnumber};
                    model.addRow(client);
                    nameText.setText("");
                    surnameText.setText("");
                    address1Text.setText("");
                    address2Text.setText("");
                    pnumberText.setText("");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeRow(clientsTable.getSelectedRow());
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
