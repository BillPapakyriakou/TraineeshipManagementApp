package traineeship_app.domainmodel;


import jakarta.persistence.*;

@Entity
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Column for the primary key
    private int id;

    @Enumerated(EnumType.STRING)  // Store enum as a string in the database
    @Column(name = "evaluation_type")
    private EvaluationType evaluationType;

    @Column(name = "motivation")
    int motivation;

    @Column(name = "efficiency")
    int efficiency;

    @Column(name = "effectiveness")
    int effectiveness;

    @ManyToOne
    @JoinColumn(name = "traineeship_position_id")
    private TraineeshipPosition traineeshipPosition;

    // We decided to set up EvaluationType as an enum inside the class
    public enum EvaluationType {
        EXAM,
        ASSIGNMENT,
        PRESENTATION,
        QUIZ,
        PROJECT
    }

    public Evaluation() {}

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
