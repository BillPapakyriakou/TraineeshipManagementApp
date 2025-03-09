package traineeship_app.services.assign;

import org.springframework.beans.factory.annotation.Autowired;
import traineeship_app.domainmodel.Professor;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.ProfessorMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

public class AssignmentBasedOnInterests implements SupervisorAssignmentStrategy {

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;
    @Autowired
    private ProfessorMapper professorMapper;


    @Override
    public void assign(Integer positionId) {
        // Find the position by ID
        TraineeshipPosition position = positionsMapper.findById(positionId)
                .orElseThrow(() -> new IllegalArgumentException("Position not found"));

        // Get the topics associated with the position
        String topics = positionsMapper.getTopics(positionId);

        // Try to find a professor based on expertise in the topics
        String bestProfessor = professorMapper.findProfessorByExpertise(topics);

        // If a professor with expertise in the topics is found, assign them as the supervisor
        if (bestProfessor != null) {
            Professor supervisor = professorMapper.findByUsername(bestProfessor);
            if (supervisor == null) {
                throw new IllegalArgumentException("Supervisor not found");
            }
            position.setSupervisor(supervisor);
            positionsMapper.update(position);  // Save the updated position to the database
        } else {
            // If no professor with expertise in the topics is found, fallback to the least busy professor
            String leastBusyProfessor = professorMapper.findLeastBusyProfessor();
            if (leastBusyProfessor == null) {
                throw new IllegalArgumentException("No available professors to assign.");
            }

            // Find the least busy professor and assign them as supervisor
            Professor supervisor = professorMapper.findByUsername(leastBusyProfessor);
            if (supervisor == null) {
                throw new IllegalArgumentException("Supervisor not found");
            }

            position.setSupervisor(supervisor);  // Directly assign the supervisor
            positionsMapper.update(position);  // Save the updated position to the database
        }
    }
}
