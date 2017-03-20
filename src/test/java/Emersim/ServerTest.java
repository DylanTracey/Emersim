package Emersim;

import Emersim.Simulation.Server;
import Emersim.Utils.Location;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ServerTest {

    private Server server = new Server(null, createRandomLocation());

    private static Location createRandomLocation() {
        double x = Math.random() * 90;
        double y = Math.random() * 180;

        return new Location(x, y);
    }

    @Test
    public void testReceiverExists() {
        assertNotNull(this.server);
    }
}
