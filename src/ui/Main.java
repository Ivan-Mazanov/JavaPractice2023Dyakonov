package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Консольный ввод/вывод
public class Main {
    private static ConsoleUI consoleUI = new ConsoleUI();

    //Cписок шаблонов команд
    private static List<CommandTemplate> commands = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Program is starting");
        initializeCommandTemplate();

        while (true) {
            System.out.println(System.lineSeparator() + "Please, input a command");

            String[] dividedCommandLine = new Scanner(System.in).nextLine().split(" ");

            if (!isCommandCorrect(dividedCommandLine)) {
                System.out.println("Unknown command. Use \"/help\" to see a list of available commands");
                continue;
            }

            if (!isCorrectAmountOfArgs(dividedCommandLine)) {
                System.out.println("Wrong amount of args. Use \"/help\" to see a list of available commands");
                continue;
            }

            String[] commandLine = new String[3];
            System.arraycopy(dividedCommandLine, 0, commandLine, 0, dividedCommandLine.length);

            if (commandLine[0].equals("/exit")) {
                System.out.println(consoleUI.saveAllData());
                System.out.println("Program is shutting down");
                break;
            }

            String output = executeCommand(commandLine);

            System.out.println(output);
        }
    }

    private static boolean isCommandCorrect(String[] dividedCommandLine) {
        for (CommandTemplate template : commands) {
            if (template.getCommandName().equals(dividedCommandLine[0]))
                return true;
        }
        return false;
    }

    private static boolean isCorrectAmountOfArgs(String[] dividedCommandLine) {
        for (CommandTemplate template : commands) {
            if (template.getCommandName().equals(dividedCommandLine[0]))
                return template.getAmountOfArgs() == dividedCommandLine.length - 1;
        }
        return false;
    }

    private static String executeCommand(String[] commandLine) {
        switch (commandLine[0]) {
            case "/help":
                StringBuilder helpString = new StringBuilder();
                for (CommandTemplate commandTemplate : commands) {
                    helpString.append(System.lineSeparator()).append(commandTemplate.getCommandName()).append(" - ")
                            .append(commandTemplate.getCommandDescription()).append(System.lineSeparator())
                            .append("Args: ").append(Arrays.toString(commandTemplate.getArgsNames()))
                            .append(System.lineSeparator());
                }
                return helpString.toString();
            case "/names":
                return consoleUI.getNames();
            case "/switch":
                return consoleUI.switchSpecifiedDict(commandLine[1]);
            case "/show":
                return consoleUI.getCurrentDict();
            case "/delete":
                return consoleUI.deleteFromCurrentDict(commandLine[1]);
            case "/find":
                return consoleUI.findInCurrentDict(commandLine[1]);
            case "/add":
                return consoleUI.addInCurrentDict(commandLine[1], commandLine[2]);
            case "/save":
                return consoleUI.saveCurrentDict();
            default:
                return "Unknown command. Use \"/help\" to see a list of available commands";
        }
    }

    private static void initializeCommandTemplate() {
        String line;
        String[] rawData = new String[4];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/file/commands.csv"))) {

            while ((line = bufferedReader.readLine()) != null) {

                String[] fileLine = line.split(",");
                System.arraycopy(fileLine, 0, rawData, 0, fileLine.length);

                commands.add(new CommandTemplate(rawData[0], Integer.parseInt(rawData[1]),
                        rawData[2], rawData[3] != null ? rawData[3].split(" ") : null));

                rawData[3] = null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}