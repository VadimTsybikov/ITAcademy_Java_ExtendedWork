package entities;

import java.util.ArrayList;

public class Question {
    private QuestionKind kind;
    private String title;
    private int weight;
    private ArrayList<Answer> answers;

    public int getWeight() {
        return weight;
    }

    public Question(QuestionKind _kind, String _title, int _weight) {
        kind = _kind;
        title = _title;
        weight = _weight;
        answers = new ArrayList<Answer>();
    }

    public void appendAnswer(String _answerTitle, boolean _isCorrect) {
        answers.add(new Answer(_answerTitle, _isCorrect));
    }

    public void print(int _ordinal) {
        System.out.printf("%d. %s", _ordinal, title);
        switch (kind) {
            case QT_SINGLE_ANSWER:
                System.out.println(" (выберите единственный вариант):");
                printAnswers();
                break;
            case QT_MULTIPLE_ANSWER:
                System.out.println(" (выберите несколько вариантов через пробел:");
                printAnswers();
                break;
            case QT_OPEN_ANSWER:
                System.out.println(" (введите ответ с клавиатуры):");
                break;
        }
        printAnswers();
    }

    private void printAnswers() {
        for (int i = 0; i < answers.size(); i++) {
            answers.get(i).printAnswer(i);
        }
    }

    public boolean validateInput(String _answerStr) {
        switch (kind) {
            case QT_SINGLE_ANSWER:
                if (_answerStr.trim().length() > 1)
                    return false;
            case QT_MULTIPLE_ANSWER:
                for (String answer : _answerStr.split(" "))
                    if (answer.trim().length() > 1)
                        return false;
            case QT_OPEN_ANSWER:
                if (_answerStr.trim().length() == 0)
                    return false;
        }
        return true;
    }
}