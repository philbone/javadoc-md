package io.github.philbone.javadocmd.cli;

/**
 * Representa un problema de validación con su corrección asociada
 */
public class ValidationIssue
{

    private final String problem;
    private final String suggestion;
    private final Runnable correction;

    public ValidationIssue(String problem, String suggestion, Runnable correction) {
        this.problem = problem;
        this.suggestion = suggestion;
        this.correction = correction;
    }

    public String getProblem() {
        return problem;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public Runnable getCorrection() {
        return correction;
    }

    public void applyCorrection() {
        if (correction != null) {
            correction.run();
        }
    }
}
