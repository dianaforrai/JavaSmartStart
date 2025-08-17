import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

class Event {
    private String eventId;
    private String eventName;
    LocalDateTime startDate;
    LocalDateTime endDate;
    private ZoneId timeZone;
    private boolean recurring;
    private Duration recurrenceInterval;

    public Event(String eventId, String eventName, LocalDateTime startDate,
                 LocalDateTime endDate, ZoneId timeZone) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeZone = timeZone;
        this.recurring = false;
        this.recurrenceInterval = null;

        validateEvent();
    }

    public Event(String eventId, String eventName, LocalDateTime startDate,
                 LocalDateTime endDate, ZoneId timeZone, Duration recurrenceInterval) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeZone = timeZone;
        this.recurring = true;
        this.recurrenceInterval = recurrenceInterval;

        validateEvent();
    }

    void validateEvent() {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        if (eventName == null || eventName.trim().isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }
        if (recurring && recurrenceInterval == null) {
            throw new IllegalArgumentException("Recurring events must have a recurrence interval");
        }
    }

    public ZonedDateTime getStartDateInTimeZone(ZoneId targetTimeZone) {
        return ZonedDateTime.of(startDate, timeZone).withZoneSameInstant(targetTimeZone);
    }

    public ZonedDateTime getEndDateInTimeZone(ZoneId targetTimeZone) {
        return ZonedDateTime.of(endDate, timeZone).withZoneSameInstant(targetTimeZone);
    }

    public Duration getDuration() {
        return Duration.between(startDate, endDate);
    }

    public Optional<Event> getNextOccurrence() {
        if (!recurring || recurrenceInterval == null) {
            return Optional.empty();
        }

        LocalDateTime nextStart = startDate.plus(recurrenceInterval);
        LocalDateTime nextEnd = endDate.plus(recurrenceInterval);

        return Optional.of(new Event(
                eventId + "_" + nextStart.toString(),
                eventName,
                nextStart,
                nextEnd,
                timeZone,
                recurrenceInterval
        ));
    }

    public boolean occursOnDate(LocalDate date, ZoneId targetTimeZone) {
        ZonedDateTime startInTargetZone = getStartDateInTimeZone(targetTimeZone);
        ZonedDateTime endInTargetZone = getEndDateInTimeZone(targetTimeZone);

        LocalDate startDate = startInTargetZone.toLocalDate();
        LocalDate endDate = endInTargetZone.toLocalDate();

        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public String getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public ZoneId getTimeZone() { return timeZone; }
    public boolean isRecurring() { return recurring; }
    public Duration getRecurrenceInterval() { return recurrenceInterval; }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        validateEvent();
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        validateEvent();
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("Event{id='%s', name='%s', start=%s, end=%s, zone=%s, recurring=%s}",
                eventId, eventName, startDate.format(formatter), endDate.format(formatter),
                timeZone.getId(), recurring);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }
}