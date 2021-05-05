package csci4060.project.aimsmobileapp;

public class DataSender {
    public static DataSender instance;
    String waypointDescription;
    String driverCode;
    int tripId;

    public DataSender() {
    }

    public static DataSender getDataSender(){
        if (instance == null) {
            synchronized (DataSender.class) {
                if(instance == null){
                    instance = new DataSender();
                }
            }
        }
        return instance;
    }

    public String getWaypointDescription() {
        return waypointDescription;
    }

    public void setWaypointDescription(String waypointDescription) {
        this.waypointDescription = waypointDescription;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }
}
