package telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    boolean ongoing = true;


    @Override
    public void onUpdateReceived(Update update) {
        //Message.enableHtml
        long chatId = update.getMessage().getChatId();
        String messageReceived = update.getMessage().getText();
        System.out.println(messageReceived);

        // start to evaluate the messages you received
        // 1. Main menu
        if (messageReceived.toLowerCase().startsWith("/start")) {
            sendResponse(chatId, "Quick, detective, we've got a Code Conundrum! \uD83D\uDD75\uFE0F");
            sendResponse(chatId, "Critical GitHub branches vanished, possibly due to a phishing email. You must investigate in the IT department and interrogate 5 suspects.");
            sendResponse(chatId, "<b>Larry Lint</b>: Careless developer.\n\n" +
                    "<b>Rita Codecrunch</b>: Puzzle lover who leaves riddles.\n\n" +
                    "<b>Sam Phishmaster</b>: Social engineering expert.\n\n" +
                    "<b>Maggie Clickson</b>: Click-happy internet enthusiast.\n\n" +
                    "<b>Hubert Norton</b>: Meticulous IT character.");
            sendResponse(chatId, "Search these locations for clues:\n" +
                    "\n" +
                    "<b>Desks</b>: Larry's chaos, Rita's riddles, Sam's deception, Maggie's internet finds, and Hubert's secrecy.\n\n" +
                    "<b>Server Room</b>: The heart of the digital mystery.\n\n" +
                    "<b>Cafeteria</b>: Where secrets might slip during coffee breaks.");
            sendResponse(chatId, "Get ready to crack the case, detective, and save the project! The fate of the code is in your hands.");
        }
        while (ongoing) {
            sendResponse(chatId, "What do you want to do?");
            createButtons();
        }

    }

    private void createButtons() {
        //Menü 1
        InlineKeyboardButton suspect = InlineKeyboardButton.builder().text("Interrogate a suspect").callbackData("suspect").build();
        InlineKeyboardButton place = InlineKeyboardButton.builder().text("Investigate a location").callbackData("place").build();
        InlineKeyboardButton accusation = InlineKeyboardButton.builder().text("Make an accusation and end the game").callbackData("accusation").build();
        //Menü suspects
        InlineKeyboardButton larry = InlineKeyboardButton.builder().text("Interrogate Larry").callbackData("larry").build();
        InlineKeyboardButton rita = InlineKeyboardButton.builder().text("Interrogate Rita").callbackData("rita").build();
        InlineKeyboardButton sam = InlineKeyboardButton.builder().text("Interrogate Sam").callbackData("sam").build();
        InlineKeyboardButton maggie = InlineKeyboardButton.builder().text("Interrogate Maggie").callbackData("maggie").build();
        InlineKeyboardButton hubert = InlineKeyboardButton.builder().text("Interrogate Hubert").callbackData("hubert").build();
        //Menü places
        InlineKeyboardButton serverRoom = InlineKeyboardButton.builder().text("Investigate Server Room").callbackData("serverRoom").build();
        InlineKeyboardButton cafeteria = InlineKeyboardButton.builder().text("Investigate Cafeteria").callbackData("cafeteria").build();
        InlineKeyboardButton desks = InlineKeyboardButton.builder().text("Investigate desks").callbackData("desks").build();
        //Menü desks
        InlineKeyboardButton larrysDesk = InlineKeyboardButton.builder().text("Investigate Larry's desk").callbackData("larrysDesk").build();
        InlineKeyboardButton ritasDesk = InlineKeyboardButton.builder().text("Investigate Rita's desk").callbackData("ritasDesk").build();
        InlineKeyboardButton samsDesk = InlineKeyboardButton.builder().text("Investigate Sam's desk").callbackData("samsDesk").build();
        InlineKeyboardButton maggiesDesk = InlineKeyboardButton.builder().text("Investigate Maggie's desk").callbackData("maggiesDesk").build();
        InlineKeyboardButton hubertsDesk = InlineKeyboardButton.builder().text("Investigate Hubert's desk").callbackData("hubertsDesk").build();



    }

    /**
     * This method counts the letters of a provided string parameter and saves them into a Map which is returned.
     * All letters are converted into lowercase. Digits and other non-letter characters are not counted.
     * @param input the string to be evaluated
     * @return a map containing the result of
     */
    public Map<Character, Integer> countLetters(String input) {
        Map<Character, Integer> letterCountMap = new HashMap<>();

        for (char c : input.toCharArray()) {
            // Convert the character to lowercase to count both uppercase and lowercase letters together
            char lowercaseC = Character.toLowerCase(c);

            // Check if the character is a letter (alphabet character)
            if (Character.isLetter(lowercaseC)) {
                letterCountMap.put(lowercaseC, letterCountMap.getOrDefault(lowercaseC, 0) + 1);
            }
        }
        return letterCountMap;
    }


    private void sendResponse(long chatId, String s) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(s);
        msg.setParseMode("HTML");
        //msg.wait(500);

        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPollToUser(long chatId) {
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(chatId);
        sendPoll.setQuestion("Which programming language do you like the most?");
        sendPoll.setOptions(List.of("Java", "Python", "JavaScript", "C++"));
        try {
            execute(sendPoll);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "6650326737:AAGpegXaGjdNi7-JNPDVlo6FaoJo9hfRjbE";  // TODO: insert your bot token here!
    }

    @Override
    public String getBotUsername() {
        return "codeconundrum_bot";  // TODO: insert your bots username here
    }
}
