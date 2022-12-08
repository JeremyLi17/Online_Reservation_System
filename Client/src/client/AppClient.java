package client;

import model.AppUser;
import model.Reservation;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static utils.ClientRequest.*;

/**
 * @author jeremy on 2022/12/7
 */
public class AppClient extends JDialog {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 1200;

    public static final String TIME_SLOT1 = "8AM - 10AM";
    public static final String TIME_SLOT2 = "10AM - 12PM";
    public static final String TIME_SLOT3 = "2PM - 4PM";
    public static final String TIME_SLOT4 = "4AM - 6PM";

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
    private JTextField userIdText;
    private JButton reserveButton;
    private JButton cancelResButton;
    private JLabel appointmentTitle;
    private JLabel userRegisterTitle;
    private JLabel roomLabel;
    private JLabel DateLabel;
    private JLabel yearText;
    private JLabel monthText;
    private JLabel dayText;
    private JLabel userIdLabel;
    private JLabel timeSlotLabel;
    private JTextField roomText;
    private JTextField updateIdText;
    private JLabel note;
    private JLabel updateIdLabel;
    private JLabel searchTitle;
    private JTextField searchByEmailText;
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
    private JTextField descText;
    private JLabel descLabel;
    private JTextField searchByIDText;
    private JLabel orIDLabel;

    public AppClient(JFrame parent) {
        super(parent);
        setTitle("Reservation System");

        userDetailTextArea.setEditable(false);
        allReserveTextArea.setEditable(false);

        // init comboBox
        timeSlotCombo.addItem("");
        timeSlotCombo.addItem(TIME_SLOT1);
        timeSlotCombo.addItem(TIME_SLOT2);
        timeSlotCombo.addItem(TIME_SLOT3);
        timeSlotCombo.addItem(TIME_SLOT4);
        timeSlotCombo.setSelectedIndex(0);

        // add listener
        registerNewUserButton.addActionListener((e) -> {
            String username = uNameText.getText();
            String lastName = lNameText.getText();
            String firstName = fNameText.getText();
            String email = emailText.getText();
            if (username == null || lastName == null || firstName == null
                    || email == null || username.length() == 0
                    || lastName.length() == 0 || firstName.length() == 0
                    || email.length() == 0) {
                return;
            }

            registerUser(username, lastName, firstName, email);
            clearUserForm();
        });

        updateInfoButton.addActionListener((e) -> {
            String username = uNameText.getText();
            String lastName = lNameText.getText();
            String firstName = fNameText.getText();
            String email = emailText.getText();
            if (updateIdText.getText() == null) return;
            Integer id = Integer.valueOf(updateIdText.getText());

            updateUser(id, username,lastName,firstName,email);
            clearUserForm();
        });

        reserveButton.addActionListener((e) -> {
            String id = userIdText.getText();
            String roomNo = roomText.getText();
            String year = yearField.getText();
            String month = monthField.getText();
            String day = dayField.getText();
            String desc = descText.getText();
            String timeSlotStr = (String) timeSlotCombo.getSelectedItem();
            if (id == null || roomNo == null || year == null || month == null
                    || day == null || timeSlotStr == null || id.length() == 0
                    || roomNo.length() == 0 || year.length() == 0 || month.length() == 0
                    || day.length() == 0) {
                return;
            }

            int timeSlot = 0;
            switch (timeSlotStr) {
                case TIME_SLOT1:
                    timeSlot = 1;
                    break;
                case TIME_SLOT2:
                    timeSlot = 2;
                    break;
                case TIME_SLOT3:
                    timeSlot = 3;
                    break;
                case TIME_SLOT4:
                    timeSlot = 4;
                    break;
            }

            try {
                makeReservation(dateFormat.parse(year + "-" + month + "-" + day),
                        Integer.valueOf(roomNo),timeSlot,Integer.valueOf(id),desc);
            } catch (ParseException exception) {
                System.err.println("Error in parse date [ Year:" + year +
                        ", Month:" + month + ", Day:" + day + "]");
            }
            clearReservationForm();
        });

        cancelResButton.addActionListener((e) -> clearUserForm());

        searchUserButton.addActionListener((e) -> {
            userDetailTextArea.setText("");
            String email = searchByEmailText.getText();
            String id = searchByIDText.getText();
            if (email == null && id == null) {
                userDetailTextArea.setText("Please input the user email or ID first");
                return;
            }

            //get the user
            AppUser user = null;
            if (id != null && id.length() != 0) {
                user = getUserInfo(Integer.valueOf(id));
            }
            if (email != null && email.length() != 0 && user == null) {
                user = getUserInfo(email);
            }
            if (user == null) {
                userDetailTextArea.setText("Invalid Email or ID\n");
                return;
            }

            // set display
            userDetailTextArea.append("User ID: " + user.getId() + "\n");
            userDetailTextArea.append("Username: " + user.getUsername() + "\n");
            userDetailTextArea.append("Last Name: " + user.getLastName() + "\n");
            userDetailTextArea.append("First Name: " + user.getFirstName() + "\n");
            userDetailTextArea.append("Email: " + user.getEmail() + "\n");
            userDetailTextArea.append("Reservations: [\n");
            for (int i = 0; i < user.getReservations().size(); i++) {
                Reservation res = user.getReservations().get(i);
                userDetailTextArea.append("    Reservation ID: " + res.getId() + "\n");
                userDetailTextArea.append("    Reservation Date: " + res.getDate() + "\n");
                userDetailTextArea.append("    Reservation Time Slot: "
                        + getTimeSlot(res.getTimeSlot()) + "\n");
                userDetailTextArea.append("    Reservation description: "
                        + res.getDescription() + "\n");
                if (i != user.getReservations().size() - 1) {
                    userDetailTextArea.append("    ------------------------------------\n");
                }
            }
            userDetailTextArea.append("]");
        });

        searchReservationButton.addActionListener((e) -> {
            allReserveTextArea.setText("");
            List<Reservation> reservationList = getAllReservation();
            for (int i = 0; i < reservationList.size(); i++) {
                Reservation res = reservationList.get(i);
                allReserveTextArea.append("Reservation ID: " + res.getId() + "\n");
                allReserveTextArea.append("Reservation Date: " + res.getDate() + "\n");
                allReserveTextArea.append("Reservation Time Slot: "
                        + getTimeSlot(res.getTimeSlot()) + "\n");
                allReserveTextArea.append("Reservation description: "
                        + res.getDescription() + "\n");
                if (i != reservationList.size() - 1) {
                    allReserveTextArea.append("------------------------------------\n");
                }
            }
        });

        setContentPane(registerPanel);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private String getTimeSlot(int timeSlot) {
        switch (timeSlot) {
            case 1:
                return TIME_SLOT1;
            case 2:
                return TIME_SLOT2;
            case 3:
                return TIME_SLOT3;
            case 4:
                return TIME_SLOT4;
        }
        return "Invalid Time Slot";
    }

    private void clearReservationForm() {
        userIdText.setText("");
        roomText.setText("");
        yearField.setText("");
        monthField.setText("");
        dayField.setText("");
        descText.setText("");
        timeSlotCombo.setSelectedIndex(0);
    }

    private void clearUserForm() {
        uNameText.setText("");
        lNameText.setText("");
        fNameText.setText("");
        emailText.setText("");
        updateIdText.setText("");
    }

    public static void main(String[] args) {
        AppClient appClient = new AppClient(null);
    }
}
