package traineeship_app.search;

import traineeship_app.domainmodel.TraineeshipPosition;
import java.util.List;

public interface PositionsSearchStrategy {

    List<TraineeshipPosition> search(String applicantUsername);
}
