package com.diaryapp.EventHandler;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.util.Log;

import com.diaryapp.EventHandler.DB.DbHandler;

/**
 * Класс для управления определенным событием.
 */
public class Event {

    private int eventId;
    private boolean isClosed;
    private String groupName;
    private String eventTitle;
    private String eventDate;
    private int eventType;
    private int eventStartTime;
    private int eventEndTime;
    private int eventColor;
    private String eventText;

    // сеттеры
    public void setId(int eventId) {
        this.eventId = eventId;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public void setGroup(String groupName) {
        this.groupName = groupName;
    }

    public void setTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setType(int eventType) {
        this.eventType = eventType;
    }

    public void setStartTime(int eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public void setEndTime(int eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public void setColor(int eventColor) {
        this.eventColor = eventColor;
    }
    public void setText(String eventText) {
        this.eventText = eventText;
    }

    // геттеры
    public int getId() {
        return eventId;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public String getGroup() {
        return groupName;
    }

    public String getTitle() {
        return eventTitle;
    }

    public String getDate() {
        return eventDate;
    }

    public int getType() {
        return eventType;
    }

    public int getStartTime() {
        return eventStartTime;
    }

    public int getEndTime() {
        return eventEndTime;
    }

    public int getColor() {
        return eventColor;
    }
    public String getText() {
        return this.eventText;
    }

    /**
     * Конструктор события по умолчанию.
     */
    @SuppressLint("Range")
    public Event() {
        this.eventId = 0;
        this.isClosed = false;
        this.groupName = "Без группы";
        this.eventTitle = "Безымянное событие";
        this.eventDate = "2024-01-01";
        this.eventType = 0;
        this.eventStartTime = 1000;
        this.eventEndTime = 2400;
        this.eventColor = 0xFFFF7676;
        this.eventText = "Без описания";
    }


    /**
     * Изменение данных события.
     * @param isClosed закрыто ли событие.
     * @param groupName название группы.
     * @param eventTitle имя события.
     * @param eventDate дата события.
     * @param eventType тип события (0 - простое, 1 - продолжительное).
     * @param eventStartTime время начала.
     * @param eventEndTime время конца.
     * @param eventColor цвет.
     * @param eventText описание.
     */
    public void rewrite(boolean isClosed,
                       String groupName,
                       String eventTitle,
                       String eventDate,
                       int eventType,
                       int eventStartTime,
                       int eventEndTime,
                       int eventColor,
                       String eventText) {

        this.isClosed = isClosed;
        this.groupName = groupName;
        this.eventTitle = eventTitle;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventColor = eventColor;
        this.eventText = eventText;
    }

    /**
     * Конструктор из курсора базы данных.
     * @param cursor курсор базы данных.
     */
    @SuppressLint("Range")
    public Event(Cursor cursor) {
        this.eventId = cursor.getInt(cursor.getColumnIndex("event_id"));
        this.isClosed = DbHandler.intToBool(cursor.getInt(cursor.getColumnIndex("is_closed")));
        this.groupName = cursor.getString(cursor.getColumnIndex("group_name"));
        this.eventTitle = cursor.getString(cursor.getColumnIndex("event_title"));
        this.eventDate = cursor.getString(cursor.getColumnIndex("event_date"));
        this.eventType = cursor.getInt(cursor.getColumnIndex("event_type"));
        this.eventStartTime = cursor.getInt(cursor.getColumnIndex("event_start_time"));
        this.eventEndTime = cursor.getInt(cursor.getColumnIndex("event_end_time"));
        this.eventColor = cursor.getInt(cursor.getColumnIndex("event_color"));
        this.eventText = cursor.getString(cursor.getColumnIndex("event_text"));
    }

    /**
     * Метод для получения события из базы данных по ID.
     * @param db объект DbHandler.
     * @return объект Event.
     */
    public static Event getById(DbHandler db, int Id) {
        Event event = db.getEventById(Id);
        event.log();
        return event;
    }

    /**
     * Метод для добавления события в базу данных.
     * @param db объект DbHandler.
     */
    public void add(DbHandler db) {
        this.log();
        db.addEvent(this);
    }

    /**
     * Метод для обновления информации о событии.
     * @param db объект DbHandler.
     */
    public void save(DbHandler db) {
        this.log();
        db.updateEvent(this);
    }

    /**
     * Метод для удаления события из базы данных.
     * @param db объект DbHandler.
     */
    public void delete(DbHandler db) {
        db.deleteEvent(this);
    }

    /**
     * Метод для вывода в консоль всей информации о событии.
     */
    public void log() {
        Log.d("EventInfo", "Id: " + this.getId() +
                ", Title: " + this.getTitle() +
                ", Is closed: " + this.isClosed() +
                ", Group: " + this.getGroup() +
                ", Type: " + this.getType() +
                ", Date: " + this.getDate() +
                ", Start time: " + this.getStartTime() +
                ", End time: " + this.getEndTime() +
                ", Color: " + this.getColor() +
                ", Text: " + this.getText());
    }
}
