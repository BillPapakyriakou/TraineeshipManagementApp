package traineeship_app.domainmodel;

public class Evaluation {

    private int id;
    private EvaluationType evaluationType;
    int motivation;
    int efficiency;
    int effectiveness;

   // We decided to set up EvaluationType as an enum inside the class
    public enum EvaluationType {
        EXAM,
        ASSIGNMENT,
        PRESENTATION,
        QUIZ,
        PROJECT
    }

    // Evaluation constructor
    public Evaluation(int id, EvaluationType evaluationType, int motivation, int efficiency, int effectiveness) {
        this.id = id;
        this.evaluationType = evaluationType;
        this.motivation = motivation;
        this.efficiency = efficiency;
        this.effectiveness = effectiveness;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EvaluationType getEvaluationType() {
        return evaluationType;
    }

    public void setEvaluationType(EvaluationType evaluationType) {
        this.evaluationType = evaluationType;
    }

    public int getMotivation() {
        return motivation;
    }

    public void setMotivation(int motivation) {
        this.motivation = motivation;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public int getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(int effectiveness) {
        this.effectiveness = effectiveness;
    }
}
