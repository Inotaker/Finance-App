package by.itacademy.accountschedulerservice.model.dto;

public class Schedule {
    private long start_time;//$int64
    private long stop_time;//$int64
    private int interval;//$int64
    private String time_unit;

    public Schedule() {
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getStop_time() {
        return stop_time;
    }

    public void setStop_time(long stop_time) {
        this.stop_time = stop_time;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getTime_unit() {
        return time_unit;
    }

    public void setTime_unit(String time_unit) {
        this.time_unit = time_unit;
    }

    public static final class Builder {
        private long start_time;//$int64
        private long stop_time;//$int64
        private int interval;//$int64
        private String time_unit;

        private Builder() {
        }

        public static Builder aSchedule() {
            return new Builder();
        }

        public Builder withStart_time(long start_time) {
            this.start_time = start_time;
            return this;
        }

        public Builder withStop_time(long stop_time) {
            this.stop_time = stop_time;
            return this;
        }

        public Builder withInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder withTime_unit(String time_unit) {
            this.time_unit = time_unit;
            return this;
        }

        public Schedule build() {
            Schedule schedule = new Schedule();
            schedule.setStart_time(start_time);
            schedule.setStop_time(stop_time);
            schedule.setInterval(interval);
            schedule.setTime_unit(time_unit);
            return schedule;
        }
    }
}
