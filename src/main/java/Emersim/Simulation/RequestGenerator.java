package Emersim.Simulation;

import Emersim.Gui.Statistics;

import java.util.LinkedList;

public class RequestGenerator implements Runnable {

    private SpatialQueueSimulator sim;
    private double lambda;
    private Statistics stats;
    private ClientRegion cr;
    private LinkedList<Request> servedRequests;

    RequestGenerator(SpatialQueueSimulator sim, double lambda, ClientRegion cr) {
        this.sim = sim;
        this.cr = cr;
        // set lambda to be #(arrivals per millisecond)
        this.lambda = lambda / 1000;
        servedRequests = new LinkedList<>();
        stats = new Statistics();
        stats.setLambda(this.lambda);
        stats.setSI(sim.getAverageServiceTime());
    }

    public void run() {
        while (this.sim.isRunning() && this.sim.moreRequests()) {
            Request newRequest = this.sim.createRequest(cr);
            this.sim.enqueueRequest(newRequest);
            this.sim.getQueueDrawer().enterQueue();

            double timeToWait = getNextArrivalTime(lambda) / sim.getTimeMultiplier();

            System.out.println(timeToWait);
            try {
                Thread.sleep((long) timeToWait);
                servedRequests.add(newRequest);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private double getNextArrivalTime(double lambda) {
        return (Math.log(1.0 - ((Math.random() / 2) + 0.25)) / -lambda);
    }

    public Statistics getStats() {
        return stats;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public void updateAverageServiceTime(double currentTime) {
        double averageServiceTime;
        if (this.servedRequests.isEmpty()) {
            averageServiceTime = 0;
        } else {
            double temp = 0;
            for (Request r : servedRequests) {
                temp += r.getResponseTime();
            }
            averageServiceTime = temp / this.servedRequests.size();
        }
        stats.setLambda(lambda * 1000);
        stats.setSI(averageServiceTime);
    }

}



