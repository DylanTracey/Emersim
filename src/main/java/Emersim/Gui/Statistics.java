package Emersim.Gui;

import Emersim.Queues.Exceptions.NonErgodicException;
import Emersim.Queues.MM1Logic;
import Emersim.Simulation.SpatialQueueSimulator;
import Emersim.Gui.SizeConstants.DrawNormal;
import Emersim.Utils.Formatter;

import javax.swing.*;
import java.awt.*;

import static Emersim.Gui.GuiComponents.sim;

/**
 * Created by joshuazeltser on 02/11/2016.
 */
public class Statistics {

    double U; // Utilization [%]
    double Q; // Average customer in station
    private String nStrS = "Avg. Cust. in Station (Queue + Service) N = ";
    private String nStrE = " cust.";
    private String uStrS = "Avg. Utilization (Sum of All Servers) U = ";
    private String uStrE = "";
    private String thrStrS = "Avg. Throughput X =";
    private String thrStrE = " cust./s";
    private String respStrS = "Avg. Response Time R = ";
    private String respStrE = " s";

    private MM1Logic ql;
    private QueueDrawer queueDrawer;
    private DrawNormal dCst;
    private JLabel mediaJobsL;
    private JLabel utilizationL;
    private JLabel thrL;
    private JLabel responseL;

    public Statistics() {
        ql = new MM1Logic(0, 0);
        queueDrawer = new QueueDrawer(ql, true);
        init();
        dCst = new DrawNormal();
    }

    //initialise components
    private void init() {
        mediaJobsL = new JLabel();
        utilizationL = new JLabel();
        thrL = new JLabel();
        responseL = new JLabel();
    }

    // update values for stats in the stats results at the bottom of the gui
    private void updateFields(SpatialQueueSimulator sim) {
        boolean nonErgodic = false;
        try {
            Q = ql.mediaJobs();
            U = ql.utilization();
            utilizationL.setForeground(Color.BLACK);
            utilizationL.setText(uStrS + Formatter.formatNumber(U, 3) + uStrE);
            mediaJobsL.setText(nStrS + Formatter.formatNumber(Q, 3) + nStrE);

            double t = ql.throughput();
            double r = ql.responseTime();

            thrL.setText(thrStrS + Formatter.formatNumber(t, 3) + thrStrE);
            responseL.setText(respStrS + Formatter.formatNumber(r, 3) + respStrE);
            nonErgodic = false;

        } catch (NonErgodicException e) {
            // if saturation has been reached
            Q = 0.0;
            U = 0.0;
            mediaJobsL.setText(nStrS + "Saturation");

            utilizationL.setForeground(Color.RED);
            utilizationL.setText(uStrS + "Saturation");
            responseL.setText(respStrS + "Saturation");
            nonErgodic = true;
        }
        queueDrawer.setMediaJobs(Q - U);
    }

    // generate the initial stats for when the frame is first opened
    void generateSimulationStats(JPanel resultsP) {
        // media
        mediaJobsL.setText(nStrS + "0" + nStrE);
        mediaJobsL.setFont(dCst.getNormalGUIFont());
        resultsP.add(mediaJobsL);

        // utilization
        utilizationL.setText(uStrS + "0" + uStrE);
        utilizationL.setFont(dCst.getNormalGUIFont());
        resultsP.add(utilizationL);

        // throughput
        thrL.setText(thrStrS + "0" + thrStrE);
        thrL.setFont(dCst.getNormalGUIFont());
        resultsP.add(thrL);

        // response time
        responseL.setText(respStrS + "0" + respStrE);
        responseL.setFont(dCst.getNormalGUIFont());
        resultsP.add(responseL);
    }

    //setup queue visualisation and pointer
    void showQueue() {

        ql = new MM1Logic(0, 0);

        queueDrawer.updateLogic(ql);
        queueDrawer.setMaxJobs(0);
        queueDrawer.setCpuNumber(1);
        updateFields(sim);
    }

    // set the service time
    public void setSI(double sI) {
        ql.setS(sI / 1000);
        updateFields(sim);

    }

    public void setLambda(double lambda) {
        ql.setLambda(lambda);
        updateFields(sim);
    }

    public QueueDrawer getQueueDrawer() {
        return queueDrawer;
    }

    public MM1Logic getQueueLogic() {
        return ql;
    }


}