package traineeship_app.assign;

public class SupervisorAssignmentFactory {

    private AssignmentBasedOnLoad assignmentBasedOnLoad;
    private AssignmentBasedOnInterests assignmentBasedOnInterests;


    public SupervisorAssignmentStrategy create(String strategy) {
        switch (strategy.toLowerCase()) {
            case "load":
                return assignmentBasedOnLoad;
            case "interests":
                return assignmentBasedOnInterests;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
    }

}
