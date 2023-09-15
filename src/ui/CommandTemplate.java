package ui;

public class CommandTemplate {
    private String commandName;
    private int amountOfArgs;
    private String commandDescription;
    private String[] argsNames;

    public CommandTemplate(String commandName, int amountOfArgs, String commandDescription, String[] argsNames) {
        this.commandName = commandName;
        this.amountOfArgs = amountOfArgs;
        this.commandDescription = commandDescription;
        this.argsNames = argsNames;
    }

    public String getCommandName() {
        return commandName;
    }

    public int getAmountOfArgs() {
        return amountOfArgs;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public String[] getArgsNames() {
        return argsNames;
    }
}
