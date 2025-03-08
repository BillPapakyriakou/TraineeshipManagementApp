package traineeship_app.domainmodel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "professor_name", nullable = false)
    private String professorName;

    @Column(name = "interests")
    private String interests;

    @OneToMany(mappedBy = "supervisor")
    private List<TraineeshipPosition> supervisedPositions;  // One-to-many relationship with TraineeshipPosition


    public Professor() {}

    // Professor constructor
    public Professor(String username, String professorName, String interests, List<TraineeshipPosition> supervisedPositions) {
        this.username = username;
        this.professorName = professorName;
        this.interests = interests;
        this.supervisedPositions = supervisedPositions;

    }


    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public List<TraineeshipPosition> getSupervisedPositions() {
        return supervisedPositions;
    }

    public void setSupervisedPositions(List<TraineeshipPosition> supervisedPositions) {
        this.supervisedPositions = supervisedPositions;
    }
}
