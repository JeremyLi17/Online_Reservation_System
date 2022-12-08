package client;

import javax.swing.*;
import java.awt.*;

/**
 * @author jeremy on 2022/12/7
 */
public class AppClient extends JDialog {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 1200;

    private JPanel registerPanel;
    private JTextField uNameText;
    private JTextField lNameText;
    private JTextField fNameText;
    private JTextField emailText;
    private JButton registerNewUserButton;
    private JButton updateInfoButton;
    private JLabel icon;
    private JLabel title;
    private JLabel uNameLabel;
    private JLabel lNameLabel;
    private JLabel fNameLabel;
    private JLabel emailLabel;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JComboBox timeSlotCombo;
    private JTextField userText;
    private JButton reserveButton;
    private JButton cancelResButton;
    private JLabel appointmentTitle;
    private JLabel userRegisterTitle;
    private JLabel roomLabel;
    private JLabel DateLabel;
    private JLabel yearText;
    private JLabel monthText;
    private JLabel dayText;
    private JLabel userLabel;
    private JLabel timeSlotLabel;
    private JTextField roomText;
    private JTextField updateIdText;
    private JLabel note;
    private JLabel updateId;
    private JLabel searchTitle;
    private JTextField searchEmailText;
    private JTextArea userDetailTextArea;
    private JTextArea allReserveTextArea;
    private JLabel searchByEmailLabel;
    private JButton searchUserButton;
    private JButton searchReservationButton;
    private JLabel DetailResultLabel;
    private JScrollPane userDetailPanel;
    private JScrollPane allReservationPanel;
    private JLabel allReservationLabel;
    private JLabel searchAllReserveLabel;

    public AppClient(JFrame parent) {
        super(parent);
        setTitle("Reservation System");

        // configuration


        setContentPane(registerPanel);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        AppClient appClient = new AppClient(null);
    }
}
