package entities;

public class Answer {
    private String title;
    private boolean isCorrect;

    public Answer(String _title, boolean _isCorrect) {
        title = _title;
        isCorrect = _isCorrect;
    }

    public void printAnswer(int _ordinal) {
        char letter = (char)('a' + _ordinal);
        System.out.printf("\t%c. %s;\n", letter, title);
    }
}