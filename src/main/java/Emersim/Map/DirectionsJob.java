package Emersim.Map;

import com.teamdev.jxmaps.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

class DirectionsJob implements Callable<DirectionsResult> {

    /*
     * Dialog to notify the user of errors when placing a client region on the map
     */

    private MapConfig mapConfig;
    private TravelMode travelMode;
    private double lat1;
    private double lng1;
    private double lat2;
    private double lng2;

    DirectionsJob(MapConfig mapConfig, TravelMode travelMode, double lng1, double lat1, double lng2, double lat2) {
        this.mapConfig = mapConfig;
        this.travelMode = travelMode;
        this.lat1 = lat1;
        this.lng1 = lng1;
        this.lat2 = lat2;
        this.lng2 = lng2;
    }

    // Calls the JxMaps API and returns the result
    @Override
    public DirectionsResult call() throws DirectionsNotFoundException, CallRateExceededException {
        // Creating a directions request
        final DirectionsRequest request = new DirectionsRequest();
        // Setting of the origin location to the request
        request.setOriginString(lat1 + ", " + lng1);
        // Setting of the destination location to the request
        request.setDestinationString(lat2 + ", " + lng2);
        // Setting of the travel mode
        request.setTravelMode(travelMode);
        // Calculating the route between locations
        final BlockingQueue<DirectionsResult> directions = new ArrayBlockingQueue<>(1);
        final BlockingQueue<Boolean> resultStatus = new ArrayBlockingQueue<>(1);
        // API call encased in try/catch to prevent errors from crashing simulator
        try {
            mapConfig.getServices().getDirectionService().route(request, new DirectionsRouteCallback(MapConfig.map) {
                @Override
                public void onRoute(DirectionsResult result, DirectionsStatus status) {
                    // Checking of the operation status
                    if (status == DirectionsStatus.OK) {
                        directions.add(result);
                        resultStatus.add(true);
                    } else {
                        System.out.println("JxMaps API Error - Directions not found");
                        resultStatus.add(false);
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("JxMaps API Error - Call rate exceeded");
            throw new CallRateExceededException();
        }
        try {
            if (!resultStatus.take()) {
                // No directions could be calculated for this travel mode
                throw new DirectionsNotFoundException();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Critical error so exit
            System.exit(-1);
        }
        DirectionsResult directionsResult = null;
        try {
            directionsResult = directions.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            // Critical error so exit
            System.exit(-1);
        }
        return directionsResult;
    }
}
