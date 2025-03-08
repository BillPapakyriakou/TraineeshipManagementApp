package traineeship_app.search;

import traineeship_app.domainmodel.TraineeshipPosition;
import java.util.ArrayList;
import java.util.List;

public class CompositeSearch implements PositionsSearchStrategy {

    private SearchBasedOnLocation searchBasedOnLocation;
    private SearchBasedOnInterests searchBasedOnInterests;


    @Override
    public List<TraineeshipPosition> search(String applicantUsername) {
        // Get the positions based on location
        List<TraineeshipPosition> locationResults = searchBasedOnLocation.search(applicantUsername);

        // Get the positions based on interests
        List<TraineeshipPosition> interestResults = searchBasedOnInterests.search(applicantUsername);

        // Combine the results
        List<TraineeshipPosition> combinedResults = new ArrayList<>();
        combinedResults.addAll(locationResults);
        combinedResults.addAll(interestResults);

        // Return the combined list of positions
        return combinedResults;
    }

}
