package com.flux.agenda.google.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class CalendarEventService {

    private final Calendar calendar;

    public CalendarEventService(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Event> getAllEvents() throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendar.events().list("primary")
                .setMaxResults(1000)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        log.info("Access role: {}", events.getAccessRole());
        return events.getItems();
    }

    public String addEvent(Map<String, String> eventsParameters) throws IOException {
        Event event = new Event()
                .setSummary(eventsParameters.get("Name"))
                .setDescription("A chance to hear more about Google's developer products.");

        LocalDateTime dateTime = LocalDateTime.parse(String.format("20%s-%s-%sT%s:00",eventsParameters.get("Year"),
                eventsParameters.get("Month"),
                eventsParameters.get("Day"),
                eventsParameters.get("Time")));


        DateTimeFormatter outputFormatter = DateTimeFormatter
                .ofPattern("uuuu-MM-dd'T'HH:mm:ss");


        Date in = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        DateTime startDateTime = new DateTime(in, TimeZone.getTimeZone("Europe/Chisinau"));
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Chisinau");

        event.setStart(start);

        DateTime endDateTime = new DateTime(dateTime.minusHours(3).format(outputFormatter));
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };

        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
        event = calendar.events().insert(calendarId, event).execute();
        log.info("Event created: {}", event.getHtmlLink());
        return event.getHtmlLink();
    }
}
