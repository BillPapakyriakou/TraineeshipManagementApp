package traineeship_app.domainmodel;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Company {


    private String username;
    private String companyName;
    private String companyLocation;

    private ArrayList<TraineeshipPosition> positions;


    // Company constructor
    public Company(String username, String companyName, String companyLocation, ArrayList<TraineeshipPosition> positions) {
        this.username = username;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.positions = positions;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public ArrayList<TraineeshipPosition> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<TraineeshipPosition> positions) {
        this.positions = positions;
    }
}
