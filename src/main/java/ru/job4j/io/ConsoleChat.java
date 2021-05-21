package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answersList = null;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            String userAnswer = userInput.readLine();
            String botAnswer;
            List<String> chatLog = new ArrayList<>();
            boolean stopProgram = false;
            while (!userAnswer.equals(OUT)) {
                chatLog.add(userAnswer);
                if (userAnswer.equals(STOP)) {
                    stopProgram = true;
                }
                if (userAnswer.equals(CONTINUE)) {
                    stopProgram = false;
                }
                if (!stopProgram) {
                    botAnswer = getBotAnswers(botAnswers);
                    System.out.println(botAnswer);
                    chatLog.add(botAnswer);
                }
                userAnswer = userInput.readLine();
            }
            writeLog(chatLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLog(List<String> chatLog) {
        try (BufferedWriter chatLogWriter = new BufferedWriter(new FileWriter(path))) {
            for (String chatLine : chatLog) {
                chatLogWriter.write(chatLine);
                chatLogWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBotAnswers(String botAnswers) {
        if (answersList == null) {
            try (BufferedReader buffRead = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
                answersList = buffRead.lines().collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Random random = new Random();
        return answersList.get(random.nextInt(answersList.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/main/resources/answersLog.txt", "./src/main/resources/answers.txt");
        cc.run();
    }
}
