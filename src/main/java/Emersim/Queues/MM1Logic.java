package Emersim.Queues;

import Emersim.Queues.Exceptions.NonErgodicException;

import java.util.Random;

/**
 * Contains the algorithm of the queue M/M/1  where lambda represent the arrival of the job
 * in second and s as service time of the job in the processor.
 * M/M/1 is the poisson process of arrival and service time.
 */
public class MM1Logic implements QueueLogic {

    /**
     * arrival rate [job/ms]
     */
    private double lambda;

    /**
     * service time in the processor[ms]
     */
    private double s;

    /**
     * random number generator
     */

    private Random rnd;

    /**
     * Initialize the queue
     *
     * @param lambda represent the medium of arrival of <i>Poisson process</i> <b>[job/s]</b>
     * @param s      represent the medium of the service time of <i>Poisson process</i> <b>[ms]</b>
     */
    public MM1Logic(double lambda, double s) {
        this.lambda = lambda;
        this.s = s;
        rnd = new Random();
    }

    /* (non-Javadoc)
     * @see Queues.QueueLogic#getStatusProbability(int)
     */
    public double getStatusProbability(int status) throws NonErgodicException {
        return ((1 - utilization()) * Math.pow(utilization(), status));
    }

    /**
     * setting the value of <b>lambda</b>
     *
     * @param lambda new value <b>[job/s]</b>
     */
    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    /**
     * setting the value of <b>s</b>
     *
     * @param s new value <b>[s]</b>
     */
    public void setS(double s) {
        this.s = s;
    }

    /**
     * Calculate the average jobs in the station
     * with respect to lambda and s
     *
     * @return number of average jobs in the station
     * @throws NonErgodicException queue is not ergodic (U > 1 o U < 0)
     */
    public double mediaJobs() throws NonErgodicException {
        return (utilization()) / (1.0 - utilization());
    }

    /**
     * Calculate the utilizaion of the server
     * with respect to parameters lambda and s
     *
     * @return utilization
     * @throws NonErgodicException queue is not ergodic (U > 1 o U < 0)
     */
    public double utilization() throws NonErgodicException {
        if ((lambda * s) > 1) {
            throw new NonErgodicException();
        } else {
            return (lambda * s);
        }
    }

    /* (non-Javadoc)
     * @see Queues.QueueLogic#throughput()
     */
    public double throughput() throws NonErgodicException {
        return mediaJobs() / responseTime();
    }

    /* (non-Javadoc)
     * @see Queues.QueueLogic#responseTime()
     */
    public double responseTime() throws NonErgodicException {
        return s / (1.0 - utilization());
    }

    public double getLambda() {
        return lambda;
    }

    public double getS() {
        return s;
    }

}
