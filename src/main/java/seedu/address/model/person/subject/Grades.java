package seedu.address.model.person.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * A high-level class that represents the grades of a student stored by subject
 */
public class Grades {

    public static final String MESSAGE_CONSTRAINTS =
        "Grades should only contain an alphabet, and it should not be blank";
    private static final String EXCEPTION_NO_GRADE_FOUND = "No grade found for assessment %s";

    // the double array contains 3 values: the score for the assessment,
    // the total score for the assessment, and the weightage of the assessment
    private final HashMap<String, double[]> assessmentMarks;

    /**
     * Constructs a {@code Grades}.
     */
    public Grades() {
        assessmentMarks = new HashMap<>();
    }

    /**
     * Updates the {@code marks} HashMap with the specified assessment
     *
     * @param updatedAssessment the Assessment object to add
     */
    public void updateAssessment(Assessment updatedAssessment) {
        if (assessmentMarks.containsKey(updatedAssessment.getAssessmentName())) {
            double[] updatedMarks = new double[3];
            updatedMarks[0] = updatedAssessment.getAssessmentScore();
            updatedMarks[1] = updatedAssessment.getAssessmentTotalScore();
            updatedMarks[2] = updatedAssessment.getAssessmentWeightage();
            assessmentMarks.put(updatedAssessment.getAssessmentName(), updatedMarks);
        } else {
            assessmentMarks.put(updatedAssessment.getAssessmentName(), updatedAssessment.getScoreArray());
        }
    }

    public ArrayList<String> getAllAssessments() {
        return new ArrayList<>(assessmentMarks.keySet());
    }

    public double getCurrentPercentageObtained(HashMap<String, double[]> subjectMarks) {
        ArrayList<double[]> totalMarksArray = new ArrayList<>(subjectMarks.values());
        double totalMarks = 0;
        double totalWeightage = 0;
        for (double[] doubles : totalMarksArray) {
            totalMarks += doubles[0] * doubles[1];
            totalWeightage += doubles[1];
        }
        return (totalMarks / totalWeightage);
    }

    public double[] getGradeForAssessment(String assessment) {
        if (assessmentMarks.containsKey(assessment)) {
            return assessmentMarks.get(assessment);
        } else {
            throw new NoSuchElementException(String.format(EXCEPTION_NO_GRADE_FOUND, assessment));
        }
    }

    @Override
    public int hashCode() {
        return assessmentMarks.hashCode();
    }

    @Override
    public String toString() {
        double currentPercentageObtained = getCurrentPercentageObtained(assessmentMarks);
        return String.format("Grades: %.1f", currentPercentageObtained);
    }

}
