package com.flux.agenda.controller;

import com.flux.agenda.google.service.CalendarEventService;
import com.google.api.services.calendar.model.Event;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    final CalendarEventService calendarEventService;

    public EventController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    @SneakyThrows
    @GetMapping("/agenda/getAllEvents")
    public List<Event> getAllEvents() {
        return calendarEventService.getAllEvents();
    }
}
