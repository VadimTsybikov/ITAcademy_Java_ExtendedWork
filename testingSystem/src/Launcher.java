import entities.Test;
import entities.Question;
import entities.QuestionKind;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Launcher {
    private static ArrayList<Test> tests = new ArrayList<Test>();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        printMenu();
    }

    private static void printMenu() {
        while (true) {
            System.out.println("ТЕСТИРУЮЩИЙ КОМПЛЕКС:");

            System.out.println();
            printAllTests();
            System.out.println();

            System.out.println("a. Создать тест;");
            System.out.println("e. Редактировать тест;");
            System.out.println("d. Удалить тест.");
            System.out.println("x. Выход");
            System.out.print("Введите номер варианта: ");

            String selectedCase = scanner.nextLine();
            switch (selectedCase.toLowerCase()) {
                case "a":
                    newTest();
                    break;
                case "e":
                    break;
                case "d":
                    break;
                case "x":
                    return;
                default:
                    continue;
            }
        }
    }

    private static void printAllTests() {
        if (tests.size() > 0) {
            for (int i = 0; i < tests.size(); i++) {
                tests.get(i).printInfo(i);
            }
        }
        else
            System.out.println("Еще нет тестов.");
    }

    private static void newTest() {
        System.out.print("Введите название теста: ");
        String testName = scanner.nextLine();

        System.out.print("Введите описание теста: ");
        String testDescription = scanner.nextLine();

        System.out.print("Укажите длительность теста в формате HH:MM:SS: ");
        String testDurationStr = scanner.nextLine();

        Test newTest = new Test(testName, testDescription, testDurationStr);

        Boolean makeNewQuestion = true;
        while (makeNewQuestion) {
            QuestionKind kind = QuestionKind.QT_SINGLE_ANSWER;
            System.out.println("Выберите тип вопроса:");
            System.out.println("\ta. единственный выбор;");
            System.out.println("\tb. множественный выбор;");
            System.out.println("\tc. открытый ответ.");
            String selectedCase = scanner.nextLine();
            switch (selectedCase.toLowerCase()) {
                case "a":
                    kind = QuestionKind.QT_SINGLE_ANSWER;
                    break;
                case "b":
                    kind = QuestionKind.QT_MULTIPLE_ANSWER;
                    break;
                case "c":
                    kind = QuestionKind.QT_OPEN_ANSWER;
                    break;
                default:
                    continue;
            }

            System.out.println("Введите формулировку вопроса.");
            String questionTitle = scanner.nextLine();

            System.out.println("Введите вес вопроса.");
            int questionWeight = scanner.nextInt();

            newTest.appendQuestion(kind, questionTitle, questionWeight);

            System.out.print("Ввести следующий вопрос? [да - 1; нет - 0]: ");
            makeNewQuestion = scanner.nextInt() == 1;
        }

        tests.add(newTest);
    }
}