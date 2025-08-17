import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class EventSchedulingSystem {
    private List<Event> events;
    private ZoneId systemTimeZone;

    private static final LocalTime BUSINESS_START = LocalTime.of(9, 0);
    private static final LocalTime BUSINESS_END = LocalTime.of(17, 0);
    private static final Duration MIN_SLOT_DURATION = Duration.ofMinutes(30);

    public EventSchedulingSystem() {
        this.events = new ArrayList<>();
        this.systemTimeZone = ZoneId.systemDefault();
    }

    public EventSchedulingSystem(ZoneId systemTimeZone) {
        this.events = new ArrayList<>();
        this.systemTimeZone = systemTimeZone;
    }

    // i) Create an event with given start date and time
    public Event createEvent(String eventId, String eventName, LocalDateTime startDate,
                             Duration duration, ZoneId timeZone) {
        LocalDateTime endDate = startDate.plus(duration);
        Event newEvent = new Event(eventId, eventName, startDate, endDate, timeZone);

        if (hasOverlappingEvents(newEvent)) {
            throw new IllegalStateException("Event overlaps with existing events");
        }

        events.add(newEvent);
        System.out.printf("Created event: %s%n", newEvent);
        return newEvent;
    }

    public Event createRecurringEvent(String eventId, String eventName, LocalDateTime startDate,
                                      Duration duration, ZoneId timeZone, Duration recurrenceInterval) {
        LocalDateTime endDate = startDate.plus(duration);
        Event newEvent = new Event(eventId, eventName, startDate, endDate, timeZone, recurrenceInterval);

        if (hasOverlappingEvents(newEvent)) {
            throw new IllegalStateException("Event overlaps with existing events");
        }

        events.add(newEvent);
        System.out.printf("Created recurring event: %s%n", newEvent);
        return newEvent;
    }

    // ii) List all events scheduled for a given date
    public List<Event> getEventsForDate(LocalDate date, ZoneId targetTimeZone) {
        return events.stream()
                .filter(event -> event.occursOnDate(date, targetTimeZone))
                .sorted(Comparator.comparing(event ->
                        event.getStartDateInTimeZone(targetTimeZone).toLocalDateTime()))
                .collect(Collectors.toList());
    }

    // iii) Check if a new event overlaps with existing events
    public boolean hasOverlappingEvents(Event newEvent) {
        return events.stream()
                .anyMatch(existingEvent -> eventsOverlap(newEvent, existingEvent));
    }

    private boolean eventsOverlap(Event event1, Event event2) {
        ZoneId comparisonZone = systemTimeZone;

        ZonedDateTime start1 = event1.getStartDateInTimeZone(comparisonZone);
        ZonedDateTime end1 = event1.getEndDateInTimeZone(comparisonZone);
        ZonedDateTime start2 = event2.getStartDateInTimeZone(comparisonZone);
        ZonedDateTime end2 = event2.getEndDateInTimeZone(comparisonZone);

        return start1.isBefore(end2) && start2.isBefore(end1);
    }

    // iv) Find available time slots for scheduling a new event
    public List<TimeSlot> findAvailableTimeSlots(LocalDate date, Duration eventDuration,
                                                 ZoneId targetTimeZone) {
        List<TimeSlot> availableSlots = new ArrayList<>();

        List<Event> dayEvents = getEventsForDate(date, targetTimeZone);

        List<TimeSlot> occupiedSlots = dayEvents.stream()
                .map(event -> {
                    ZonedDateTime start = event.getStartDateInTimeZone(targetTimeZone);
                    ZonedDateTime end = event.getEndDateInTimeZone(targetTimeZone);
                    return new TimeSlot(start.toLocalDateTime(), end.toLocalDateTime(), targetTimeZone);
                })
                .sorted(Comparator.comparing(TimeSlot::getStart))
                .collect(Collectors.toList());

        LocalDateTime currentTime = date.atTime(BUSINESS_START);
        LocalDateTime endOfDay = date.atTime(BUSINESS_END);

        for (TimeSlot occupiedSlot : occupiedSlots) {
            if (currentTime.isBefore(occupiedSlot.getStart())) {
                Duration gapDuration = Duration.between(currentTime, occupiedSlot.getStart());
                if (gapDuration.compareTo(eventDuration) >= 0) {
                    availableSlots.add(new TimeSlot(currentTime, occupiedSlot.getStart(), targetTimeZone));
                }
            }
            currentTime = occupiedSlot.getEnd();
        }

        if (currentTime.isBefore(endOfDay)) {
            Duration finalGapDuration = Duration.between(currentTime, endOfDay);
            if (finalGapDuration.compareTo(eventDuration) >= 0) {
                availableSlots.add(new TimeSlot(currentTime, endOfDay, targetTimeZone));
            }
        }

        return availableSlots;
    }

    // v) Reschedule an existing event to a new date and time
    public boolean rescheduleEvent(String eventId, LocalDateTime newStartDate, ZoneId newTimeZone) {
        Optional<Event> eventOptional = findEventById(eventId);

        if (eventOptional.isEmpty()) {
            System.out.printf("Event with ID '%s' not found%n", eventId);
            return false;
        }

        Event event = eventOptional.get();
        Duration eventDuration = event.getDuration();
        LocalDateTime newEndDate = newStartDate.plus(eventDuration);

        Event tempEvent = new Event(eventId + "_temp", event.getEventName(),
                newStartDate, newEndDate, newTimeZone);

        events.remove(event);

        if (hasOverlappingEvents(tempEvent)) {
            events.add(event);
            System.out.printf("Cannot reschedule event '%s' - conflicts with existing events%n", eventId);
            return false;
        }

        LocalDateTime oldStartDate = event.getStartDate();
        LocalDateTime oldEndDate = event.getEndDate();
        ZoneId oldTimeZone = event.getTimeZone();

        try {
            event.startDate = newStartDate;
            event.endDate = newEndDate;
            event.setTimeZone(newTimeZone);

            event.validateEvent();

            events.add(event);
            System.out.printf("Rescheduled event '%s' to %s%n", eventId, newStartDate);
            return true;

        } catch (IllegalArgumentException e) {
            event.startDate = oldStartDate;
            event.endDate = oldEndDate;
            event.setTimeZone(oldTimeZone);
            events.add(event);

            System.out.printf("Cannot reschedule event '%s' - validation error: %s%n", eventId, e.getMessage());
            return false;
        }
    }

    // vi) Cancel an existing event
    public boolean cancelEvent(String eventId) {
        Optional<Event> eventOptional = findEventById(eventId);

        if (eventOptional.isEmpty()) {
            System.out.printf("Event with ID '%s' not found%n", eventId);
            return false;
        }

        Event event = eventOptional.get();
        events.remove(event);
        System.out.printf("Cancelled event: %s%n", event.getEventName());
        return true;
    }

    private Optional<Event> findEventById(String eventId) {
        return events.stream()
                .filter(event -> event.getEventId().equals(eventId))
                .findFirst();
    }

    // vii) Time zone handling methods
    public void displayEventInTimeZone(String eventId, ZoneId targetTimeZone) {
        Optional<Event> eventOptional = findEventById(eventId);

        if (eventOptional.isEmpty()) {
            System.out.printf("Event with ID '%s' not found%n", eventId);
            return;
        }

        Event event = eventOptional.get();
        ZonedDateTime startInTargetZone = event.getStartDateInTimeZone(targetTimeZone);
        ZonedDateTime endInTargetZone = event.getEndDateInTimeZone(targetTimeZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");

        System.out.printf("Event: %s%n", event.getEventName());
        System.out.printf("Original time (%s): %s - %s%n",
                event.getTimeZone().getId(),
                ZonedDateTime.of(event.getStartDate(), event.getTimeZone()).format(formatter),
                ZonedDateTime.of(event.getEndDate(), event.getTimeZone()).format(formatter));
        System.out.printf("In %s: %s - %s%n",
                targetTimeZone.getId(),
                startInTargetZone.format(formatter),
                endInTargetZone.format(formatter));
    }

    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    public int getEventCount() {
        return events.size();
    }

    public void displayDaySchedule(LocalDate date, ZoneId timeZone) {
        System.out.printf("%n=== Schedule for %s (%s) ===%n", date, timeZone.getId());

        List<Event> dayEvents = getEventsForDate(date, timeZone);

        if (dayEvents.isEmpty()) {
            System.out.println("No events scheduled for this date");
            return;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        dayEvents.forEach(event -> {
            ZonedDateTime start = event.getStartDateInTimeZone(timeZone);
            ZonedDateTime end = event.getEndDateInTimeZone(timeZone);

            System.out.printf("%s - %s: %s%n",
                    start.format(timeFormatter),
                    end.format(timeFormatter),
                    event.getEventName());
        });
    }

    public List<Event> generateRecurringEventInstances(String eventId, LocalDate endDate) {
        Optional<Event> baseEventOpt = findEventById(eventId);
        if (baseEventOpt.isEmpty() || !baseEventOpt.get().isRecurring()) {
            return Collections.emptyList();
        }

        Event baseEvent = baseEventOpt.get();
        List<Event> instances = new ArrayList<>();

        Optional<Event> currentEvent = Optional.of(baseEvent);
        while (currentEvent.isPresent() &&
                currentEvent.get().getStartDate().toLocalDate().isBefore(endDate.plusDays(1))) {
            instances.add(currentEvent.get());
            currentEvent = currentEvent.get().getNextOccurrence();
        }

        return instances;
    }

    public static void main(String[] args) {
        System.out.println("=== Event Scheduling System Demo ===\n");

        EventSchedulingSystem scheduler = new EventSchedulingSystem(ZoneId.of("America/New_York"));

        try {
            // i) Create events with different timezones
            System.out.println("=== Creating Events ===");

            scheduler.createEvent("MEET001", "Team Standup",
                    LocalDateTime.of(2024, 3, 15, 9, 0),
                    Duration.ofMinutes(30),
                    ZoneId.of("America/New_York"));

            scheduler.createEvent("MEET002", "Client Presentation",
                    LocalDateTime.of(2024, 3, 15, 14, 0),
                    Duration.ofHours(2),
                    ZoneId.of("America/Los_Angeles"));

            scheduler.createRecurringEvent("MEET003", "Weekly Review",
                    LocalDateTime.of(2024, 3, 15, 16, 0),
                    Duration.ofHours(1),
                    ZoneId.of("America/New_York"),
                    Duration.ofDays(7));

            // ii) List events for a specific date
            System.out.println("\n=== Events for March 15, 2024 ===");
            LocalDate targetDate = LocalDate.of(2024, 3, 15);
            ZoneId displayTimeZone = ZoneId.of("America/New_York");

            List<Event> dayEvents = scheduler.getEventsForDate(targetDate, displayTimeZone);
            dayEvents.forEach(System.out::println);

            scheduler.displayDaySchedule(targetDate, displayTimeZone);

            // iii) Check for overlapping events
            System.out.println("\n=== Checking for Overlaps ===");
            try {
                scheduler.createEvent("MEET004", "Overlapping Meeting",
                        LocalDateTime.of(2024, 3, 15, 9, 15),
                        Duration.ofMinutes(45),
                        ZoneId.of("America/New_York"));
            } catch (IllegalStateException e) {
                System.out.println("Overlap detected: " + e.getMessage());
            }

            // iv) Find available time slots
            System.out.println("\n=== Available Time Slots ===");
            List<TimeSlot> availableSlots = scheduler.findAvailableTimeSlots(
                    targetDate, Duration.ofHours(1), displayTimeZone);

            System.out.printf("Available 1-hour slots on %s:%n", targetDate);
            availableSlots.forEach(slot ->
                    System.out.printf("  %s (Duration: %d minutes)%n",
                            slot, slot.getDuration().toMinutes()));

            // v) Reschedule an event
            System.out.println("\n=== Rescheduling Events ===");
            scheduler.rescheduleEvent("MEET001",
                    LocalDateTime.of(2024, 3, 15, 10, 30),
                    ZoneId.of("America/New_York"));

            // vi) Cancel an event
            System.out.println("\n=== Canceling Events ===");
            scheduler.cancelEvent("MEET002");

            // vii) Demonstrate timezone handling
            System.out.println("\n=== Timezone Handling ===");
            scheduler.displayEventInTimeZone("MEET001", ZoneId.of("Europe/London"));
            scheduler.displayEventInTimeZone("MEET001", ZoneId.of("Asia/Tokyo"));

            System.out.println("\n=== Final Schedule ===");
            scheduler.displayDaySchedule(targetDate, displayTimeZone);

            System.out.println("\n=== Recurring Event Instances ===");
            List<Event> recurringInstances = scheduler.generateRecurringEventInstances(
                    "MEET003", LocalDate.of(2024, 4, 15));

            System.out.println("Weekly Review instances for the next month:");
            recurringInstances.forEach(instance ->
                    System.out.printf("  %s%n", instance.getStartDate().toLocalDate()));

            System.out.printf("%nTotal events in system: %d%n", scheduler.getEventCount());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}