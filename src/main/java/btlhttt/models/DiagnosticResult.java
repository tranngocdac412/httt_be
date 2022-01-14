package btlhttt.models;

public class DiagnosticResult {
    private String description;
    private String solution;
    private double value;

    public DiagnosticResult() {
    }

    public DiagnosticResult(String description, String solution, double value) {
        this.description = description;
        this.solution = solution;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "description='" + description + '\'' +
                ", solution='" + solution + '\'' +
                ", value=" + value +
                '}';
    }
}
