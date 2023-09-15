package ui;

import ui.init.commands.CommandTemplate;
import ui.init.commands.CommandsOptionsInitializer;
import ui.init.dictionaries.DictionariesOptionsInitializer;

import java.util.Arrays;
import java.util.Scanner;

//Консольный ввод/вывод
public class Main {
    private static ConsoleUI consoleUI = new ConsoleUI();
    private static CommandsOptionsInitializer commandsOptionsInitializer = new CommandsOptionsInitializer();
    private static DictionariesOptionsInitializer dictionariesOptionsInitializer = new DictionariesOptionsInitializer();

    public static void main(String[] args) {
        System.out.println("Program is starting");

        boolean initCommandsResult = commandsOptionsInitializer.
                initOptions("src/file/options/commands.csv");
        System.out.println(commandsOptionsInitializer.getMessage());

        if (!initCommandsResult)
            return;


        boolean initDictionariesResult = dictionariesOptionsInitializer.
                initOptions("src/file/options/dict-options.csv");
        System.out.println(dictionariesOptionsInitializer.getMessage());

        if (!initDictionariesResult)
            return;


        startProgramCycle();

        System.out.println("Program is shutting down");
    }

    private static boolean isCommandCorrect(String[] dividedCommandLine) {
        for (CommandTemplate template : commandsOptionsInitializer.getCommands()) {
            if (template.getCommandName().equals(dividedCommandLine[0]))
                return true;
        }
        return false;
    }

    private static boolean isCorrectAmountOfArgs(String[] dividedCommandLine) {
        for (CommandTemplate template : commandsOptionsInitializer.getCommands()) {
            if (template.getCommandName().equals(dividedCommandLine[0]))
                return template.getAmountOfArgs() == dividedCommandLine.length - 1;
        }
        return false;
    }

    private static void startProgramCycle() {
        boolean isRunning = true;

        while (isRunning) {
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

                isRunning = false;
                continue;
            }

            String output = executeCommand(commandLine);

            System.out.println(output);
        }
    }

    private static String executeCommand(String[] commandLine) {
        switch (commandLine[0]) {
            case "/help":
                StringBuilder helpString = new StringBuilder();
                for (CommandTemplate commandTemplate : commandsOptionsInitializer.getCommands()) {
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
}