package com.wine.to.up.deployment.service.entity;

import java.time.LocalDateTime;

public class Log {
    private LocalDateTime createdDate;
    private String log;

    public Log(LocalDateTime createdDate, String log) {
        this.createdDate = createdDate;
        this.log = log;
    }

    public Log() {
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Log)) return false;
        final Log other = (Log) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$createdDate = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        final Object this$log = this.getLog();
        final Object other$log = other.getLog();
        if (this$log == null ? other$log != null : !this$log.equals(other$log)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Log;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        final Object $log = this.getLog();
        result = result * PRIME + ($log == null ? 43 : $log.hashCode());
        return result;
    }

    public String toString() {
        return "Log(createdDate=" + this.getCreatedDate() + ", log=" + this.getLog() + ")";
    }
}
