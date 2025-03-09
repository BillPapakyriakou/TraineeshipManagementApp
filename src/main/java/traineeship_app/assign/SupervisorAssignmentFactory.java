package traineeship_app.assign;

public class SupervisorAssignmentFactory {

    private AssignmentBasedOnLoad assignmentBasedOnLoad;
    private AssignmentBasedOnInterests assignmentBasedOnInterests;


    public SupervisorAssignmentStrategy create(String strategy) {
        return switch (strategy.toLowerCase()) {
            case "load" -> assignmentBasedOnLoad;
            case "interests" -> assignmentBasedOnInterests;
            default -> throw new IllegalArgumentException("Unknown strategy: " + strategy);
        };
    }

}
