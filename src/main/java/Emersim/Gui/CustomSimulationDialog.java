package Emersim.Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by joshuazeltser on 22/11/2016.
 */
class CustomSimulationDialog {

    //Dialog that is produced when custom dialog selected
    public CustomSimulationDialog(SpatialQueueFrame aFrame) {

        String serverName = "";
        String clientName = "";

        JPanel p = new JPanel(new BorderLayout(5, 10));

        //create labels for server and client input
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 5));
        labels.add(new JLabel("Server", SwingConstants.RIGHT));
        labels.add(new JLabel("Client", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        // create text fields so the user can input their chosen names
        JPanel params = new JPanel(new GridLayout(0, 1, 2, 5));
        final JTextField server = new JTextField();
        params.add(server);

        final JTextField client = new JTextField();
        params.add(client);

        p.add(params, BorderLayout.CENTER);

        // create the dialog box
        int result = JOptionPane.showConfirmDialog(
                aFrame, p, "Custom Simulation Setup", JOptionPane.OK_CANCEL_OPTION);

        clientName = client.getText();
        serverName = server.getText();

        //if ok is clicked save the settings and close the dialog
        if (result == 0) {
            aFrame.dispose();
            SpatialQueueFrame newSqf = new SpatialQueueFrame();
            newSqf.setCustomLabels(clientName, serverName);
            if (clientName.equals("") || serverName.equals("")) {
                newSqf.setCustomLabels("Client", "Server");
            }
        }
    }
}
