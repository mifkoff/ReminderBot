import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.toIntExact;


public class CheKakTamege extends TelegramLongPollingBot {

    long Your_chat_id = 1; // TODO: @myidbot - id  можно получить здесь
    // TODO-метками пометил места, которые необходимо заменить

    private Timer timer = new Timer();
    public boolean setOnce = true;

    ArrayList<String> monday_tasks = new ArrayList<String>();
    ArrayList<String> monday_complete = new ArrayList<String>();

    ArrayList<String> tuesday_tasks = new ArrayList<String>();
    ArrayList<String> tuesday_complete = new ArrayList<String>();

    ArrayList<String> wednesday_tasks = new ArrayList<String>();
    ArrayList<String> wednesday_complete = new ArrayList<String>();

    ArrayList<String> thursday_tasks = new ArrayList<String>();
    ArrayList<String> thursday_complete = new ArrayList<String>();

    ArrayList<String> friday_tasks = new ArrayList<String>();
    ArrayList<String> friday_complete = new ArrayList<String>();

    ArrayList<String> saturday_tasks = new ArrayList<String>();
    ArrayList<String> saturday_complete = new ArrayList<String>();

    ArrayList<String> sunday_tasks = new ArrayList<String>();
    ArrayList<String> sunday_complete = new ArrayList<String>();


    // TODO: Ежедненые таски вписывать здесь
    public void setTasks(){
        monday_tasks.add("Математика [Вариант]");
        monday_tasks.add("Русский [Сочинение]");

        tuesday_tasks.add("Информатика [Вариант]");

        wednesday_tasks.add("Математика [Вариант]");
        wednesday_tasks.add("Русский [Вариант]");
        wednesday_tasks.add("Английский [Эссе + Письмо]");

        thursday_tasks.add("Математика [Вариант]");
        thursday_tasks.add("Английский [Эссе + Письмо]");

        friday_tasks.add("Информатика [Вариант]");
        friday_tasks.add("Английский [Вариант]");
        friday_tasks.add("Русский [Сочинение]");

        saturday_tasks.add("Математика [Вариант]");
        saturday_tasks.add("Русский [Сочинение]");

        sunday_tasks.add("Математика [Вариант]");
        sunday_tasks.add("Информатика [Вариант]");
        sunday_tasks.add("Английский [Эссе + Письмо]");
        sunday_tasks.add("Русский [Вариант]");
    }


    public void My_method(){

        // TODO: Выбираете свой GMT, настраиваете в today2 время, когда бот первый раз вам пришлёт ту-ду
        // Ниже настройте зарержку между сообщениями бота

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        Date date=new Date();
        Timer timer = new Timer();

        Calendar today1 = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
        today1.set(Calendar.HOUR_OF_DAY, 23);
        today1.set(Calendar.MINUTE, 59);
        today1.set(Calendar.SECOND, 0);

        Calendar today2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
        today2.set(Calendar.HOUR_OF_DAY, 12);
        today2.set(Calendar.MINUTE, 30);
        today2.set(Calendar.SECOND, 0);

        Calendar today3 = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));
        today3.set(Calendar.HOUR_OF_DAY, 1);
        today3.set(Calendar.MINUTE, 47);
        today3.set(Calendar.SECOND, 30);


        timer.schedule(new TimerTask(){
            public void run(){
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
                String date = String.valueOf(new Date()).split(" ")[0];
                switch (date) {
                    case "Mon":
                        MondayList();
                        break;
                    case "Tue":
                        TuesdayList();
                        break;
                    case "Wed":
                        WednesdayList();
                        break;
                    case "Thu":
                        ThursdayList();
                        break;
                    case "Fri":
                        FridayList();
                        break;
                    case "Sat":
                        SaturdayList();
                        break;
                    case "Sun":
                        SundayList();
                        break;
                }

            }
        },today2.getTime(), TimeUnit.MICROSECONDS.convert(4, TimeUnit.HOURS));

        // TODO: 4 - кол-во часов, спустя которое повторяется рассылка ту-ду листа
        // (!) Время первой рассылки подкрутить можно выше


        timer.schedule(new TimerTask(){
            public void run(){

                DailyComplete();

            }
        },today1.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));

    }


    public void MondayList(){
        for (int i = 0; i < monday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < monday_complete.size(); j++) {
                if (monday_tasks.get(i).equals(monday_complete.get(j))){
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(monday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Mon " + monday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void TuesdayList(){
        for (int i = 0; i < tuesday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < tuesday_complete.size(); j++) {
                if (tuesday_tasks.get(i).equals(tuesday_complete.get(j))) {
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(tuesday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Tue " + tuesday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void WednesdayList() {
        for (int i = 0; i < wednesday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < wednesday_complete.size(); j++) {
                if (wednesday_tasks.get(i).equals(wednesday_complete.get(j))) {
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(wednesday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Wed " + wednesday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void ThursdayList(){
        for (int i = 0; i < thursday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < thursday_complete.size(); j++) {
                if (thursday_tasks.get(i).equals(thursday_complete.get(j))){
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(thursday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Thu " + thursday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void FridayList(){
        for (int i = 0; i < friday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < friday_complete.size(); j++) {
                if (friday_tasks.get(i).equals(friday_complete.get(j))){
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(friday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Fri " + friday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void SaturdayList(){
        for (int i = 0; i < saturday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < saturday_complete.size(); j++) {
                if (saturday_tasks.get(i).equals(saturday_complete.get(j))){
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(saturday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Sat " + saturday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void SundayList(){
        for (int i = 0; i < sunday_tasks.size(); i++) {
            boolean complete_ckecker = false;

            for (int j = 0; j < sunday_complete.size(); j++) {
                if (sunday_tasks.get(i).equals(sunday_complete.get(j))){
                    complete_ckecker = true;
                    break;
                }
            }

            if (complete_ckecker == false) {
                SendMessage message = new SendMessage()
                        .setChatId(Your_chat_id)
                        .setText(sunday_tasks.get(i));
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Сделано 🤙").setCallbackData("update_msg_text" + " Sun " + sunday_tasks.get(i)));
                rowsInline.add(rowInline);
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void DailyComplete(){
        StringBuilder text = new StringBuilder();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
        String day = String.valueOf(new Date()).split(" ")[0];

        switch (day) {
            case "Mon":
                for (int i = 0; i < monday_complete.size(); i++){
                    text.append(" - " + monday_complete.get(i)+"\n");
                }
                monday_complete.clear();
                break;
            case "Tue":
                for (int i = 0; i < tuesday_complete.size(); i++){
                    text.append(" - " + tuesday_complete.get(i)+"\n");
                }
                tuesday_complete.clear();
                break;
            case "Wed":
                for (int i = 0; i < wednesday_complete.size(); i++){
                    text.append(" - " + wednesday_complete.get(i)+"\n");
                }
                wednesday_complete.clear();
                break;
            case "Thu":
                for (int i = 0; i < thursday_complete.size(); i++){
                    text.append(" - " + thursday_complete.get(i)+"\n");
                }
                thursday_complete.clear();
                break;
            case "Fri":
                for (int i = 0; i < friday_complete.size(); i++){
                    text.append(" - " + friday_complete.get(i)+"\n");
                }
                friday_complete.clear();
                break;
            case "Sat":
                for (int i = 0; i < saturday_complete.size(); i++){
                    text.append(" - " + saturday_complete.get(i)+"\n");
                }
                saturday_complete.clear();
                break;
            case "Sun":
                for (int i = 0; i < sunday_complete.size(); i++){
                    text.append(" - " + sunday_complete.get(i)+"\n");
                }
                sunday_complete.clear();
                break;
        }

            SendMessage message = new SendMessage()
                    .setChatId(Your_chat_id)
                    .setText("📚 За сегодня выполнено:\n"+text.toString()+"\nМожно отдохнуть 😴");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (!update.getMessage().getText().equals("/start") || chat_id != Your_chat_id) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Не андерстенд!\nПопробуйте позже :)");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (update.getMessage().getText().equals("/start") && chat_id == Your_chat_id) {
                TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
                if (setOnce == true) {
                    setTasks();
                    SendMessage message = new SendMessage()
                            .setChatId(chat_id)
                            .setText("Задания успешно установлены :)\nРаботаем!\n"+new Date());
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    setOnce = false;
                }

                My_method();

            } else {

            }

        } else if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.contains("update_msg_text")) {
                String answer = "Огонь! 🔥";

                String day = call_data.replaceAll("update_msg_text ","").split(" ")[0];

                switch (day) {
                    case "Mon":
                        monday_complete.add(call_data.replaceAll("update_msg_text Mon ",""));
                        break;
                    case "Tue":
                        tuesday_complete.add(call_data.replaceAll("update_msg_text Tue ", ""));
                        break;
                    case "Wed":
                        wednesday_complete.add(call_data.replaceAll("update_msg_text Wed ", ""));
                        break;
                    case "Thu":
                        thursday_complete.add(call_data.replaceAll("update_msg_text Thu ", ""));
                        break;
                    case "Fri":
                        friday_complete.add(call_data.replaceAll("update_msg_text Fri ", ""));
                        break;
                    case "Sat":
                        saturday_complete.add(call_data.replaceAll("update_msg_text Sat ", ""));
                        break;
                    case "Sun":
                        sunday_complete.add(call_data.replaceAll("update_msg_text Sun ", ""));
                        break;
                }

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    @Override
    public String getBotUsername() {
        return "<bot_name>";
    }

    @Override
    public String getBotToken() {
        return "485569272:AAEMu3Rn <token>";
    }
}