package csci4060.project.aimsmobileapp;

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
    private String DestinationCode;
    private String Address;
    private String City;
    private String PostalCode;
    private int ProductId;
    private String ProductDesc;
    private int RequestedQty;
    private String UOM;
    private String Fill;

    public TripInfoModel(int tripId, String driverCode, String driverName, int truckId, String truckCode, String truckDesc, int trailerId, String trailerCode, String trailerDesc, String tripName, String tripDate, String destinationCode, String address, String city, String postalCode, int productId, String productDesc, int requestedQty, String UOM, String fill) {
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
        setDestinationCode(destinationCode);
        setAddress(address);
        setCity(city);
        setPostalCode(postalCode);
        setProductId(productId);
        setProductDesc(productDesc);
        setRequestedQty(requestedQty);
        setUOM(UOM);
        setFill(fill);
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

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
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

    public int getRequestedQty() {
        return RequestedQty;
    }

    public void setRequestedQty(int requestedQty) {
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



