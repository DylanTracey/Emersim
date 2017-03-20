package Emersim.Gui;

import Emersim.Queues.Exceptions.NonErgodicException;
import Emersim.Simulation.ClientRegion;
import Emersim.Simulation.SpatialQueueSimulator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by joshuazeltser on 30/11/2016.
 */
class SummaryPage extends JFrame {

    private SpatialQueueSimulator sim;

    public SummaryPage(SpatialQueueSimulator sim) {
        this.sim = sim;
        init();
    }

    private void init() {
        setTitle("Summary Statistics");

        GridBagConstraints c = new GridBagConstraints();

        //set window size
        Dimension d = new Dimension(650, 400);
        setPreferredSize(d);

        int count = 0;

        String respStrS = "Avg. Response Time";
        String thrStrS = "Average Throughput";
        String uStrS = "Average Utilization";
        String nStrS = "No. of Customers";
        Object columnNames[] = {"Region", nStrS, thrStrS, uStrS, respStrS};

        Object rowData[][] = new Object[sim.getRegions().size()][5];

        // for each region collect the stats and print them to the summary page
        for (ClientRegion cr : sim.getRegions()) {
            cr.getGenerator().updateAverageServiceTime(0);
            rowData[count][0] = cr.getClientGraphic().getName();

            // make sure to catch any saturation errors and print accordingly
            try {
                double media = cr.getGenerator().getStats().getQueueLogic().mediaJobs();
                String nStrE = " cust.";
                rowData[count][1] = String.format("%.6f", media) + nStrE;
            } catch (NonErgodicException e) {
                rowData[count][1] = "Saturation";
            }
            double throughput = cr.getGenerator().getStats().getQueueLogic().getLambda();
            String thrStrE = " cust./s";
            rowData[count][2] = String.format("%.6f", throughput) + thrStrE;

            try {
                double utilisation = cr.getGenerator().getStats().getQueueLogic().utilization();
                String uStrE = "";
                rowData[count][3] = String.format("%.6f", utilisation) + uStrE;
            } catch (NonErgodicException e) {
                rowData[count][3] = "Saturation";
            }

            try {
                double responseTime = cr.getGenerator().getStats().getQueueLogic().responseTime();
                String respStrE = " s";
                rowData[count][4] = String.format("%.6f", responseTime) + respStrE;
            } catch (NonErgodicException e) {
                rowData[count][4] = "Saturation";
            }
            count++;
        }

        // create a table to store the stats
        JTable table = new JTable(rowData, columnNames) {

            // stop the table from being editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);


        this.setLayout(new GridLayout(1, 1));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.removeEditor();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
