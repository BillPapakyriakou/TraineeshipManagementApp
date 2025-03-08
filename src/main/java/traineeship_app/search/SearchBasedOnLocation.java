package traineeship_app.search;

import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.CompanyMapper;
import traineeship_app.mappers.StudentMapper;

import java.util.List;

public class SearchBasedOnLocation implements PositionsSearchStrategy {

    private CompanyMapper companyMapper;
    private StudentMapper studentMapper;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        String location = studentMapper.getLocation(applicantUsername);
        return companyMapper.findPositionsByLocation(location);
    }

}
