package client;

import javax.swing.*;

/**
 * @author jeremy on 2022/12/6
 */
public class Client extends JFrame implements Runnable {

    public Client() {
        super("Reservation System");
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
