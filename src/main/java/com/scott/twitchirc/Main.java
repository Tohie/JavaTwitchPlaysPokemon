package com.scott.twitchirc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TwitchIRC irc = new TwitchIRC("Your nick", "your token");
            irc.connectToChannel("#twitchplayspokemon");
            String message;
            while (true) {
                message = irc.readMessage();
                if (message != null) {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
