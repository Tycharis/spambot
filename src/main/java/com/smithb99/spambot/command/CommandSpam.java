/* Copyright 2018 Braedon Smith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

package com.smithb99.spambot.command;

import com.smithb99.spambot.Bot;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

public class CommandSpam implements Command {
    public void runCommand(MessageReceivedEvent event, List<String> args) {
        if (args.size() < 2) {
            Bot.sendMessage(event.getChannel(), "Invalid command. Usage: \"" +
                    Bot.getBotPrefix() +
                    "spam <@mention: User...> <count: long> [remainder: String]\"");
        }

        List<IUser> users = event.getMessage().getMentions();

        for (int i = 0; i < users.size(); i++) {
            args.remove(i);
        }

        StringBuilder mentionsBuilder = new StringBuilder();

        for (int i = 0; i < users.size(); i++) {
            mentionsBuilder.append(users.get(0).mention());
            mentionsBuilder.append(" ");
        }

        String mentions = mentionsBuilder.toString();

        long count = Long.parseLong(args.get(args.size() - 1));
        long i = 0;

        StringBuilder remainderBuilder = new StringBuilder();

        for (String s: args) {
            remainderBuilder.append(s);
        }

        String message = mentions + remainderBuilder.toString();

        while (i < count) {
            Bot.sendMessage(event.getChannel(), message);
            i++;
        }
    }
}
