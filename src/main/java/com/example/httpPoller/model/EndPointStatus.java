package com.example.httpPoller.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class EndPointStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int status;
    private String reason;
    private Timestamp timestamp;

    @JoinColumn(name = "endpoint_id", referencedColumnName = "id")
    @ManyToOne
    private EndPoint endPoint;


    public EndPointStatus() {

    }

    public EndPointStatus(int status, Timestamp timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }


    public EndPointStatus(int status, Timestamp timestamp, String reason) {
        this.status = status;
        this.timestamp = timestamp;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int Status) {
        this.status = status;
    }

    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndPointStatus)) return false;
        EndPointStatus that = (EndPointStatus) o;
        return id == that.id &&
                status == that.status &&
                reason.equals(that.reason) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, reason, timestamp);
    }

    @Override
    public String toString() {
        String result = String.format(
                "EndPointStatus[id=%d, status=%d]",
                id,status);
        return result;
    }

}
