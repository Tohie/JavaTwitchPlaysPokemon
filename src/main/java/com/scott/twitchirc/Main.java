package com.scott.twitchirc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> commands = new HashMap<String, String>();
        commands.put("start", "x");
        commands.put("a", "a");
        commands.put("b", "b");
        commands.put("left", "l");
        commands.put("right", "r");
        commands.put("up", "u");
        commands.put("down", "d");

        try {
            TwitchIRC irc = new TwitchIRC("Tohie", "vgld8t1i4k1rcuxpqicl10usskt3gq");
            irc.connectToChannel("#twitchplayspokemon");
            String message;
            while (true) {
                message = irc.readMessage().toLowerCase();
                System.out.println(message);
                if (message != null) {
                    if (commands.containsKey(message)) {
                        execute(args[0], commands.get(message));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void execute(String window, String button) throws IOException {
        String command =
                "xdotool search --desktop 0 " + window + " windowactivate --sync key --clearmodifiers " + button;
        Runtime.getRuntime().exec(command);
    }
}
