package traineeship_app.domainmodel;

import java.util.ArrayList;

public class Professor {

    private String username;
    private String professorName;
    private String interests;

    private ArrayList<TraineeshipPosition> supervisedPositions;


    // Professor constructor
    public Professor(String username, String professorName, String interests, ArrayList<TraineeshipPosition> supervisedPositions) {
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

    public ArrayList<TraineeshipPosition> getSupervisedPositions() {
        return supervisedPositions;
    }

    public void setSupervisedPositions(ArrayList<TraineeshipPosition> supervisedPositions) {
        this.supervisedPositions = supervisedPositions;
    }
}
