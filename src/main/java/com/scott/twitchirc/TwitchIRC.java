package com.scott.twitchirc;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwitchIRC {
    private final String token;
    private final String nick;
    private final Socket sock;
    private BufferedReader br;
    private Pattern message;

    public TwitchIRC(String nick, String token) throws IOException {
        this.nick = nick;
        this.token = token;
        this.sock = new Socket();
        sock.connect(new InetSocketAddress("irc.twitch.tv", 6667));
        this.br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        message = Pattern.compile(".* PRIVMSG #twitchplayspokemon :(.*)");
    }

    public void connectToChannel(String channel) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(sock.getOutputStream( )));
        writer.write("PASS oauth:" + token + "\r\n");
        writer.write("NICK " + nick + "\r\n");
        writer.write("JOIN " + channel + "\r\n");
        writer.flush();
        System.out.println("Connected");
    }

    public String readLine() throws IOException {
        return br.readLine();
    }

    public String readMessage() throws IOException {
        String line = readLine();
        if (line == null) {
            return null;
        }
        Matcher matches = message.matcher(line);
        if (!matches.matches()) {
            return null;
        }
        return matches.group(1);
    }
}
