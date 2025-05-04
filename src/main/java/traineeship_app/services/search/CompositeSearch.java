package traineeship_app.services.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import traineeship_app.domainmodel.TraineeshipPosition;
import java.util.List;

@Component
public class CompositeSearch implements PositionsSearchStrategy {

    @Autowired
    private SearchBasedOnLocation searchBasedOnLocation;

    @Autowired
    private SearchBasedOnInterests searchBasedOnInterests;

    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        // Combine results from both searches (location + interests)
        List<TraineeshipPosition> locationBasedResults = searchBasedOnLocation.search(applicantUsername);
        List<TraineeshipPosition> interestBasedResults = searchBasedOnInterests.search(applicantUsername);

        // Combine the results (you could apply additional logic here if needed)
        locationBasedResults.addAll(interestBasedResults);
        return locationBasedResults; // Or apply some filtering/merging logic
    }
}
