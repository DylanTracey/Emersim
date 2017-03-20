package Emersim.Queues;

import Emersim.Queues.Exceptions.NonErgodicException;

/**
 * This interface represent the logic of the queue types: the arrival
 * and the execution of the process/job. This changed with respect to
 * inputs arrival rate(lambda), service time, number of station capacity
 * or number of servers.
 * Each queue type could have different inputs.
 */
public interface QueueLogic {
    /**
     * Calculates the average utilization of the queue(U)
     */
    double utilization() throws NonErgodicException;

}
