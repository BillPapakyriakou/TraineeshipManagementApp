package traineeship_app.domainmodel;

import java.util.ArrayList;

public class Student {

    private String username;
    private String studentName;
    private String AM;
    private double avgGrade;
    private String preferredLocation;
    private String interests;
    private String skills;

    private boolean lookingForTraineeship;

    private TraineeshipPosition assignedTraineeship;


    // Student constructor
    public Student(String username, String studentName, String AM, double avgGrade, String preferredLocation,
                   String interests, String skills, boolean lookingForTraineeship, TraineeshipPosition assignedTraineeship) {
        this.username = username;
        this.studentName = studentName;
        this.AM = AM;
        this.avgGrade = avgGrade;
        this.preferredLocation = preferredLocation;
        this.interests = interests;
        this.skills = skills;
        this.lookingForTraineeship = lookingForTraineeship;
        this.assignedTraineeship = assignedTraineeship;
    }

    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(double avgGrade) {
        this.avgGrade = avgGrade;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public boolean isLookingForTraineeship() {
        return lookingForTraineeship;
    }

    public void setLookingForTraineeship(boolean lookingForTraineeship) {
        this.lookingForTraineeship = lookingForTraineeship;
    }

    public TraineeshipPosition getAssignedTraineeship() {
        return assignedTraineeship;
    }

    public void setAssignedTraineeship(TraineeshipPosition assignedTraineeship) {
        this.assignedTraineeship = assignedTraineeship;
    }
}
