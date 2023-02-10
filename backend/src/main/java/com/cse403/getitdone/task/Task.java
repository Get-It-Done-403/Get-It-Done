package com.cse403.getitdone.task;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity

public class Task {
    @Id

    private String tid;
    private String title;
    private LocalDateTime dueDate;
    private int hoursToComplete;
    private boolean isCompleted;


    public Task(final String uid, final String title,
                final int year, final int month, final int dayOfMonth, final int hour, final int minute,
                final int hoursToComplete) {
        this.title = title;
        this.dueDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        this.hoursToComplete = hoursToComplete;
        this.isCompleted = false;

        this.tid = java.util.UUID.randomUUID().toString();
    }

    public Task(final String uid, final String title,
                final int year, final int month, final int dayOfMonth, final int hour, final int minute) {
        this.title = title;
        this.dueDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
        this.hoursToComplete = -1;
        this.isCompleted = false;

        this.tid = java.util.UUID.randomUUID().toString();
    }

    public Task() {}


    public String getTid() {
        return this.tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(final int year, final int month, final int dayOfMonth, final int hour, final int minute) {
        this.dueDate = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    public int getHoursToComplete() {
        return hoursToComplete;
    }

    public void setHoursToComplete(final int hoursToComplete) {
        this.hoursToComplete = hoursToComplete;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(final boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(this.title) +
                ((this.hoursToComplete == -1) ? 0: Integer.valueOf(this.hoursToComplete)) * this.dueDate.hashCode();
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof Task)) {
            return false;
        } else {
            Task e = (Task) o;
            return this.title.equals(e.title)
                    && this.dueDate.equals(e.dueDate)
                    && this.hoursToComplete == e.hoursToComplete
                    && this.isCompleted == e.isCompleted;
        }
    }

    @Override
    public String toString() {
        return String.format("Task [title=%s, dueDate=%s, hoursToComplete=%s, isCompleted]",
                title, this.dueDate.toString(), hoursToComplete, isCompleted);
    }

}