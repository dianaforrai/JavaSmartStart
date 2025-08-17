import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

class TimeSlot {
    private LocalDateTime start;
    private LocalDateTime end;
    private ZoneId timeZone;

    public TimeSlot(LocalDateTime start, LocalDateTime end, ZoneId timeZone) {
        this.start = start;
        this.end = end;
        this.timeZone = timeZone;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public boolean canFitEvent(Duration eventDuration) {
        return getDuration().compareTo(eventDuration) >= 0;
    }

    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public ZoneId getTimeZone() { return timeZone; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("TimeSlot{%s - %s (%s)}",
                start.format(formatter), end.format(formatter), timeZone.getId());
    }
}
