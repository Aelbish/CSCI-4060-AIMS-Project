package csci4060.project.aimsmobileapp.model;

public class TripInfoModel {
    private int TripId;
    private String DriverCode;
    private String DriverName;
    private int TruckId;
    private String TruckCode;
    private String TruckDesc;
    private int TrailerId;
    private String TrailerCode;
    private String TrailerDesc;
    private String TripName;
    private String TripDate;
    private int  SeqNum;
    private String Waypoint;
    private String DestinationCode;
    private String DestinationName;
    private String Address;
    private String City;
    private int PostalCode;
    private int ProductId;
    private String ProductCode;
    private String ProductDesc;
    private double RequestedQty;
    private String UOM;
    private String Fill;

    private double Latitude;
    private double Longitude;

    public double getLatitude(){return Latitude;}

    public void setLatitude(double latitude){Latitude = latitude;}

    public double getLongitude(){return Longitude;}

    public void setLongitude(double longitude){Longitude = longitude;}

    public int getSeqNum() {
        return SeqNum;
    }

    public void setSeqNum(int seqNum) {
        SeqNum = seqNum;
    }

    public TripInfoModel(int tripId, String driverCode, String driverName, int truckId, String truckCode, String truckDesc, int trailerId, String trailerCode, String trailerDesc, String tripName, String tripDate, int seqNum, String waypoint, String destinationCode, String destinationName, String address, String city, int postalCode, int productId, String productCode, String productDesc, double requestedQty, String UOM, String fill, Double latitude, Double longitude) {
        setTripId(tripId);
        setDriverCode(driverCode);
        setDriverName(driverName);
        setTruckId(truckId);
        setTruckCode(truckCode);
        setTruckDesc(truckDesc);
        setTrailerId(trailerId);
        setTrailerCode(trailerCode);
        setTrailerDesc(trailerDesc);
        setTripName(tripName);
        setTripDate(tripDate);
        setSeqNum(seqNum);
        setWaypoint(waypoint);
        setDestinationCode(destinationCode);
        setDestinationName(destinationName);
        setAddress(address);
        setCity(city);
        setPostalCode(postalCode);
        setProductId(productId);
        setProductCode(productCode);
        setProductDesc(productDesc);
        setRequestedQty(requestedQty);
        setUOM(UOM);
        setFill(fill);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public String getWaypoint() {
        return Waypoint;
    }

    public void setWaypoint(String waypoint) {
        Waypoint = waypoint;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public int getTripId() {
        return TripId;
    }

    public void setTripId(int tripId) {
        TripId = tripId;
    }

    public String getDriverCode() {
        return DriverCode;
    }

    public void setDriverCode(String driverCode) {
        DriverCode = driverCode;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public int getTruckId() {
        return TruckId;
    }

    public void setTruckId(int truckId) {
        TruckId = truckId;
    }

    public String getTruckCode() {
        return TruckCode;
    }

    public void setTruckCode(String truckCode) {
        TruckCode = truckCode;
    }

    public String getTruckDesc() {
        return TruckDesc;
    }

    public void setTruckDesc(String truckDesc) {
        TruckDesc = truckDesc;
    }

    public int getTrailerId() {
        return TrailerId;
    }

    public void setTrailerId(int trailerId) {
        TrailerId = trailerId;
    }

    public String getTrailerCode() {
        return TrailerCode;
    }

    public void setTrailerCode(String trailerCode) {
        TrailerCode = trailerCode;
    }

    public String getTrailerDesc() {
        return TrailerDesc;
    }

    public void setTrailerDesc(String trailerDesc) {
        TrailerDesc = trailerDesc;
    }

    public String getTripName() {
        return TripName;
    }

    public void setTripName(String tripName) {
        TripName = tripName;
    }

    public String getTripDate() {
        return TripDate;
    }

    public void setTripDate(String tripDate) {
        TripDate = tripDate;
    }

    public String getDestinationCode() {
        return DestinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        DestinationCode = destinationCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(int postalCode) {
        PostalCode = postalCode;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public double getRequestedQty() {
        return RequestedQty;
    }

    public void setRequestedQty(double requestedQty) {
        RequestedQty = requestedQty;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public String getFill() {
        return Fill;
    }

    public void setFill(String fill) {
        Fill = fill;
    }
}



