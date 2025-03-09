package traineeship_app.services.search;

import traineeship_app.domainmodel.TraineeshipPosition;
import traineeship_app.mappers.StudentMapper;
import traineeship_app.mappers.TraineeshipPositionsMapper;

import java.util.List;


public class SearchBasedOnInterests implements PositionsSearchStrategy {

    private TraineeshipPositionsMapper positionsMapper;

    private StudentMapper studentMapper;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        String interests = studentMapper.getInterests(applicantUsername);
        return positionsMapper.findByInterests(interests);
    }


}
