
import com.github.lalyos.jfiglet.FigletFont;
import picocli.CommandLine;

import java.io.IOException;

import static java.lang.IO.println;

/* Fix implementation and then uncomment
@CommandLine.Command(name = "rossigo", mixinStandardHelpOptions = true, version = "1.0", description = "RossiGo CLI")
public class MainCLI implements Runnable {

    @CommandLine.Parameters(index = "0", description = "Command: unlock, end-rent,...")
    private String command;

    @CommandLine.Parameters(index = "1", defaultValue = "", description = "Optional parameter (es. vehicle code)")
    private String parameter;

    @Override
    public void run() {
        switch (command.toLowerCase()) {
            case "unlock":
                println("Unlocking vehicle: " + parameter);
                break;
            case "end-rent":
                println("Ending rent: " + parameter);
                break;
            default:
                println("Unrecognized command: " + command);
        }
    }
}*/

void main() {
    String banner = null;
    try {
        banner = FigletFont.convertOneLine("RossiGo");
        println(banner);
    } catch (IOException e) {
        println("RossiGo");
    }
    // new CommandLine(new MainCLI()).execute(); Also this uncomment
}