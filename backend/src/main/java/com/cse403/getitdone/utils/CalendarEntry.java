package com.cse403.getitdone.utils;

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
}
