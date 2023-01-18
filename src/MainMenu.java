import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JPanel mainPanel;
    private JButton viewMoviesButton;
    private JButton viewClientsButton;
    private JButton viewRentsButton;
    private JButton exitButton;

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.setVisible(true);
    }

    public MainMenu() {
        super("Main menu");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,300);


        viewMoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movies movies = new Movies();
                movies.setVisible(true);
            }
        });

        viewClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clients clients = new Clients();
                clients.setVisible(true);
            }
        });

        viewRentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rentals rentals = new Rentals();
                rentals.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
