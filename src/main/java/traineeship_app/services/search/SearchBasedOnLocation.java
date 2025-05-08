package traineeship_app.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import traineeship_app.domainmodel.Student;
import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.CompanyMapper;
import traineeship_app.mappers.StudentMapper;

import java.util.List;
/*
public class SearchBasedOnLocation implements PositionsSearchStrategy {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {

        //studentMapper.getLocation(applicantUsername);
        //return companyMapper.findPositionsByLocation(location);

        // Retrieve student entity
        Student student = studentMapper.findByUsername(applicantUsername);

        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        // Get location from the student entity
        String location = student.getPreferredLocation();

        // Ensure location is not null or empty
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Student location is not available.");
        }

        // Retrieve traineeship positions based on the location
        //return companyMapper.findPositionsByLocation(location);
    }

} */
