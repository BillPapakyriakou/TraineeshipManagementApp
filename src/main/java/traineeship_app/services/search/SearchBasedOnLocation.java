package traineeship_app.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.CompanyMapper;
import traineeship_app.mappers.StudentMapper;

import java.util.List;

@Component
public class SearchBasedOnLocation implements PositionsSearchStrategy {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        // Implement logic to search based on location
        // For example: Retrieve positions that match the applicant's location.
        return List.of(); // Return a list of matching positions
    }

}
