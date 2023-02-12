package com.cse403.getitdone.utils;

import com.cse403.getitdone.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CalendarEntry {

    @Id
    private String eid;
    private String tid;
    private String startDate;
    private String endDate;

    public CalendarEntry(final String tid, final String startDate, final String endDate) {
        this.eid = java.util.UUID.randomUUID().toString();
        this.tid = tid;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CalendarEntry() {

    }

    public String getEid() {
        return this.eid;
    }

    public String getTid() {
        return this.tid;
    }

    public void setEid(final String eid) {
        this.eid = eid;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(this.eid) * Integer.valueOf(this.tid);
    }

    @Override
    public boolean equals( Object o ) {
        if (!(o instanceof CalendarEntry)) {
            return false;
        } else {
             CalendarEntry e = (CalendarEntry) o;
            return this.eid.equals(e.eid)
                    && this.tid.equals(e.tid)
                    && this.startDate.equals(e.startDate)
                    && this.endDate.equals(e.endDate);
        }
    }

    @Override
    public String toString() {
        return String.format("CalendarEntry [entry id=%s, task id=%s, startDate=%s, endDate=%s]",
                eid, tid, startDate, endDate);
    }
}
