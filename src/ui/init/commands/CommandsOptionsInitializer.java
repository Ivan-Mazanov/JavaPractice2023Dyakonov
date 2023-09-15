package ui.init.commands;

import ui.service.FileService;
import ui.init.OptionsInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandsOptionsInitializer implements OptionsInitializer {

    private static boolean isInit = false;
    private static List<CommandTemplate> commands = new ArrayList<>();
    private static String message;

    public boolean isInit() {
        return isInit;
    }

    public List<CommandTemplate> getCommands() {
        if (isInit())
            return List.copyOf(commands);

        return null;
    }

    @Override
    public boolean initOptions(String filePath) {
        if (isInit) {
            message = "Can't load command list. Reason: commands list has been init already";
            return false;
        }

        try {
            ArrayList<List<String>> listOfLines = FileService.getListOfLines(filePath);

            for (List<String> line : listOfLines) {
                if (line.size() < 3)
                    line.add(null);

                commands.add(new CommandTemplate(
                        line.get(0),
                        line.get(2) == null ? 0 : line.get(2).split(" ").length,
                        line.get(1),
                        line.get(2) == null ? null : line.get(2).split(" ")));
            }
        } catch (IOException e) {
            message = "Unable to load command list. Can't find options file path. Program is shutting down";
            return false;
        }
        message = "Successfully load all commands";
        isInit = true;
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
