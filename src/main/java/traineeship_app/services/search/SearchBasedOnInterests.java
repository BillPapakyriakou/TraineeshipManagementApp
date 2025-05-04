package traineeship_app.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.StudentMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

import java.util.List;

@Component
public class SearchBasedOnInterests implements PositionsSearchStrategy {

    @Autowired
    private TraineeshipPositionsMapper positionsMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        // Implement logic to search based on interests
        // For example: Retrieve positions that match the applicant's interests.
        return List.of(); // Return a list of matching positions
    }


}
