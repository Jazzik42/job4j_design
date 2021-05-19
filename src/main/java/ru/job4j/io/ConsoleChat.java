package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bfWriter = new BufferedWriter(new FileWriter(path))) {
            String userAnswer = bfReader.readLine();
            String botAnswer;
            boolean stopProgram = false;
            while (!userAnswer.equals(OUT)) {
                if (userAnswer.equals(STOP)) {
                    stopProgram = true;
                }
                if (userAnswer.equals(CONTINUE)) {
                    stopProgram = false;
                }
                if (!stopProgram) {
                    botAnswer = getBotAnswers(botAnswers);
                    System.out.println(botAnswer);
                    bfWriter.write(userAnswer + System.lineSeparator() + botAnswer + System.lineSeparator());
                } else if (stopProgram) {
                    bfWriter.write(userAnswer + System.lineSeparator());
                }
                userAnswer = bfReader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBotAnswers(String botAnswers) {
        List<String> list = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            String line = buffRead.readLine();
            while (line != null) {
                list.add(line);
                line = buffRead.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return list.get(random.nextInt(25));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/resources/answersLog.txt", "./src/main/resources/answers.txt");
        cc.run();
    }
}
