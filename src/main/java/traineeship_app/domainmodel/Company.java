package traineeship_app.domainmodel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(name = "company_location")
    private String companyLocation;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TraineeshipPosition> positions;


    // Default constructor (JPA requirement for entities)
    public Company() {}

    // Company constructor
    public Company(String username, String companyName, String companyLocation, List<TraineeshipPosition> positions) {
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

    public List<TraineeshipPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<TraineeshipPosition> positions) {
        this.positions = positions;
    }
}
