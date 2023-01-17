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

    public Clients() {
        super("Clients");
        this.setContentPane(clientsPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(500,500);

        String[] ColumnNames = {"Name","Surname","Address 1","Address 2","Phone number"};

        String[][] data = {
                {"Piotr","Pasierb","Lutoryz 413","36-040 Boguchwala","881655214"},
                {"Natalia","Rzeszutek","Zaczernie 1051B","35-540 Trzebownisko","882413232"}
        };

        DefaultTableModel model = new DefaultTableModel(ColumnNames,0);
        clientsTable.setModel(model);

        Object[] client1 = {"Piotr","Pasierb","Lutoryz 413","36-040 Boguchwala","881655214"};
        Object[] client2 = {"Natalia","Rzeszutek","Zaczernie 1051B","35-540 Trzebownisko","882413232"};

        model.addRow(client1);
        model.addRow(client2);


        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] client = {nameText.getText(),surnameText.getText(),address1Text.getText(),address2Text.getText(),pnumberText.getText()};
                model.addRow(client);
                nameText.setText("");
                surnameText.setText("");
                address1Text.setText("");
                address2Text.setText("");
                pnumberText.setText("");
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
