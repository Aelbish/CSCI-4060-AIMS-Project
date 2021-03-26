package csci4060.project.newaimsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import csci4060.project.newaimsapp.database.entity.Customer;
import csci4060.project.newaimsapp.database.entity.Delivery;
import csci4060.project.newaimsapp.database.entity.Driver;
import csci4060.project.newaimsapp.database.entity.Load;
import csci4060.project.newaimsapp.database.entity.Trip;
import csci4060.project.newaimsapp.database.entity.Vendor;

public class TripJSONParser {
    private String input;
    private final JSONObject reader;

    private String driver_code;
    private String driver_name;
    private int truck_id;
    private String truck_code;
    private String truck_description;
    private int trailer_id;
    private String trailer_code;
    private String trailer_description;
    private int trip_id;
    private String trip_name;
    private String trip_date;
    private int sequence_number;
    private String waypoint_description;
    private double latitude;
    private double longitude;
    private String destination_code;
    private String destination_name;
    private String site_container_code;
    private String site_container_description;
    private String address_1;
    private String address_2;
    private String city;
    private String state_short;
    private int postal_code;
    private int delivery_req_number;
    private int delivery_req_line_number;
    private int product_id;
    private String product_code;
    private String product_description;
    private double requested_quantity;
    private String unit_of_measurement;
    private String fill;

    public TripJSONParser(String input) throws JSONException {
        this.input = input;
        this.reader = new JSONObject(input);

    }

    public void setInput(String input) {
        this.input = input;
    }

    public void parseData() throws JSONException {
        JSONObject data = reader.getJSONObject("data");
        JSONArray resultSet1 = data.getJSONArray("resultSet1");
            
        for(int i = 0; i < resultSet1.length(); i++) {
            driver_code = resultSet1.getJSONObject(i).getString("DriverCode");
            driver_name = resultSet1.getJSONObject(i).getString("DriverName");
            truck_id = resultSet1.getJSONObject(i).getInt("TruckId");
            truck_code = resultSet1.getJSONObject(i).getString("TruckCode");
            truck_description = resultSet1.getJSONObject(i).getString("TruckDesc");
            trailer_id = resultSet1.getJSONObject(i).getInt("TrailerId");
            trailer_code = resultSet1.getJSONObject(i).getString("TrailerCode");
            trailer_description = resultSet1.getJSONObject(i).getString("TrailerDesc");
            trip_id = resultSet1.getJSONObject(i).getInt("TripId");
            trip_name = resultSet1.getJSONObject(i).getString("TripName");
            trip_date = resultSet1.getJSONObject(i).getString("TripDate");
            sequence_number = resultSet1.getJSONObject(i).getInt("SeqNum");
            waypoint_description = resultSet1.getJSONObject(i).getString("WaypointTypeDescription");
            latitude = resultSet1.getJSONObject(i).getDouble("Latitude");
            longitude = resultSet1.getJSONObject(i).getDouble("Longitude");
            destination_code = resultSet1.getJSONObject(i).getString("DestinationCode");
            destination_name = resultSet1.getJSONObject(i).getString("DestinationName");
            site_container_code = resultSet1.getJSONObject(i).getString("SiteContainerCode");
            site_container_description = resultSet1.getJSONObject(i).getString("SiteContainerDescription");
            address_1 = resultSet1.getJSONObject(i).getString("Address1");
            address_2 = resultSet1.getJSONObject(i).getString("Address2");
            city = resultSet1.getJSONObject(i).getString("City");
            state_short = resultSet1.getJSONObject(i).getString("StateAbbrev");
            postal_code = resultSet1.getJSONObject(i).getInt("PostalCode");
            delivery_req_number = resultSet1.getJSONObject(i).optInt("DelReqNum");
            delivery_req_line_number = resultSet1.getJSONObject(i).optInt("DelReqLineNum");
            product_id = resultSet1.getJSONObject(i).optInt("ProductId");
            product_code = resultSet1.getJSONObject(i).getString("ProductCode");
            product_description = resultSet1.getJSONObject(i).getString("ProductDesc");
            requested_quantity = resultSet1.getJSONObject(i).optDouble("RequestedQty", 0.0);
            unit_of_measurement = resultSet1.getJSONObject(i).getString("UOM");
            fill = resultSet1.getJSONObject(i).getString("Fill");

            storeData();
        }
    }

    private void storeData() {
        Driver driver = new Driver(driver_code, driver_name, truck_id, truck_code,
                truck_description, trailer_id, trailer_code, trailer_description);
        Trip trip = new Trip(trip_id, trip_name, trip_date, driver_code);
        Load load = new Load(sequence_number, waypoint_description, latitude, longitude, trip_id);
        Vendor vendor = new Vendor(destination_code, destination_name, address_1,
                address_2, city, state_short, postal_code, sequence_number);
        Customer customer = new Customer(destination_code, destination_name,
                site_container_code, site_container_description, address_1, address_2, city,
                state_short, postal_code, sequence_number);
        Delivery delivery = new Delivery(sequence_number, delivery_req_number,
                delivery_req_line_number, product_id, product_code, product_description,
                requested_quantity, unit_of_measurement, fill);

        AIMSApp.repository.storeData(driver, trip, load, vendor ,customer, delivery);
    }
}
