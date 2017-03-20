package Emersim.Simulation;

import com.teamdev.jxmaps.DirectionsResult;
import Emersim.Gui.GuiComponents;

/**
 * Requests are sent by Senders to Receivers. They fulfil the same function as Jobs in JMCH.
 */
public class Request implements Comparable<Request> {
    private Client client;

    private int requestId;

    private DirectionsResult directionsResult;

    private double nextEventTime;

    private double responseTime;

    enum RequestState {IN_QUEUE, BEING_SERVED, FINISHED}

    private GuiComponents.QUEUE_MODE queueMode;

    private int priority;

    public Request(int requestId, double time, Client client, int priority, GuiComponents.QUEUE_MODE queueMode) {
        this.requestId = requestId;
        this.client = client;
        this.priority = priority;
        this.queueMode = queueMode;
    }

    @Override
    public int compareTo(Request other) {
        if (other == null) {
            return 0;
        }

        int priorityDiff = this.getPriority() - other.getPriority();

        switch (queueMode) {
            case SHORTEST_DISTANCE_FIRST:
                if (priorityDiff > 0) {
                    return 1;
                } else if (priorityDiff < 0) {
                    return -1;
                } else {
                    double diff = this.getResponseTime() - other.getResponseTime();
                    if (diff > 0) {
                        return 1;
                    } else if (diff < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            case FIRST_COME_FIRST_SERVE:
                if (priorityDiff > 0) {
                    return 1;
                } else if (priorityDiff < 0) {
                    return -1;
                } else {
                    int diff = this.getRequestId() - other.getRequestId();
                    if (diff > 0) {
                        return 1;
                    } else if (diff < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
        }
        return 0;
    }

    public void serve(double currentTime, double finishTime) {
        this.setNextEventTime(finishTime);
    }

    public void finishServing(double time) {
    }

    public Client getClient() {
        return this.client;
    }

    public double getNextEventTime() {
        return this.nextEventTime;
    }

    private void setNextEventTime(double time) {
        this.nextEventTime = time;
    }

    public void setResponseTime(double time) {
        this.responseTime = time;
    }

    public double getResponseTime() {
        return this.responseTime;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public DirectionsResult getDirectionsResult() {
        return directionsResult;
    }

    public void setDirectionsResult(DirectionsResult directionsResult) {
        this.directionsResult = directionsResult;
    }

    private int getPriority() {
        return this.priority;
    }
}
