package Emersim;

import com.teamdev.jxmaps.LatLng;
import Emersim.Simulation.ClientRegion;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class ClientRegionTest {

    private LatLng[] vertices = {new LatLng(2, 0),
            new LatLng(1.5, -1.5),
            new LatLng(0, -2),
            new LatLng(-1.5, -1.5),
            new LatLng(-2, 0),
            new LatLng(-1.5, 1.5),
            new LatLng(0, 2),
            new LatLng(1.5, 1.5)};
    private ClientRegion polygon = new ClientRegion(vertices, null);

    @Test
    public void canTellWhetherPointIsInRegion() {
        Assert.assertTrue(polygon.contains(new Point(0, 0)));
    }

    @Test
    public void getCanTellWhetherPointIsOutsideRegion() {
        Assert.assertFalse(polygon.contains(new Point(10, 0)));
    }

    @Test
    public void getGenerateRandomPointWithinRegion() {
        Assert.assertNotNull(polygon.generatePoint());
    }
}
