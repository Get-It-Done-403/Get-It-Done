package com.cse403.getitdone.task;


import com.google.api.client.util.DateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Task {
    @Id

    private String tid;
    private String title;
    private String dueDate;
    private String description;
    private int hoursToComplete;
    private boolean isCompleted;

    public Task(final String title, final String dueDate, final String description, final int hoursToComplete) {
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.hoursToComplete = hoursToComplete;
        this.isCompleted = false;
        this.tid = java.util.UUID.randomUUID().toString();
    }


    public Task(final String title,
                final int year, final int month, final int dayOfMonth, final int hour, final int minute,
                final int hoursToComplete) {
        Date date = new Date(year, month, dayOfMonth, hour, minute);
        this.title = title;
        this.dueDate = new DateTime(date).toStringRfc3339();
        this.description = description;
        this.hoursToComplete = hoursToComplete;
        this.isCompleted = false;

        this.tid = java.util.UUID.randomUUID().toString();
    }

    public Task(final String title,
                final int year, final int month, final int dayOfMonth, final int hour, final int minute) {
        Date date = new Date(year, month, dayOfMonth, hour, minute);
        this.title = title;
        this.dueDate = new DateTime(date).toStringRfc3339();
        this.description = description;
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

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setDueDate(final int year, final int month, final int dayOfMonth, final int hour, final int minute) {
        Date date = new Date(year, month, dayOfMonth, hour, minute);
        this.dueDate = new DateTime(date).toStringRfc3339();
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