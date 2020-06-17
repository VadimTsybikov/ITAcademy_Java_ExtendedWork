package entities;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    private String name;
    private String description;
    private String duration;
    private ArrayList<Question> questions;
    private Scanner scanner;

    public Test(String _name, String _description, String _duration) {
        name = _name;
        description = _description;
        duration = _duration;
        questions = new ArrayList<Question>();
        scanner = new Scanner(System.in);
    }

    private int evalTotalWeight() {
        int weight = 0;
        for (Question question : questions) {
            weight += question.getWeight();
        }
        return weight;
    }

    public void appendQuestion(QuestionKind _kind, String _title, int _weight) {
        Question newQuestion = new Question(_kind, _title, _weight);

        System.out.println("Введите ответы. Верный ответ начните писать со знака \"+\".");
        System.out.println("Нажмите Ctrl+C для завершения ввода ответов:");
        String inputStr = "";
        do {
            inputStr = scanner.nextLine();
            boolean isCorrect = false;
            if (inputStr.startsWith("+")) {
                isCorrect = true;
                inputStr = inputStr.substring(1);
            }
            newQuestion.appendAnswer(inputStr, isCorrect);
        } while (!inputStr.equals("^C"));

        questions.add(newQuestion);
    }

    public void printInfo(int _ordinal) {
        System.out.printf("%d. %s (длительность %d, максимальное число баллов %d)", _ordinal, name, duration, evalTotalWeight());
    }

    public void printAllQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).print(i + 1);
        }
    }
}