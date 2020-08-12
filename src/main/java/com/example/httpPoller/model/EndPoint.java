package com.example.httpPoller.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "endpoint")
public class EndPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;

    @JsonIgnore
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "endPoint", orphanRemoval = true)
    private List<EndPointStatus> endPointStatusList = new ArrayList<EndPointStatus>();

    public void setCurrentStatus(EndPointStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    @JsonGetter(value = "currentStatus")
    public EndPointStatus getCurrentStatus() {
        return this.currentStatus;
    }

    @OneToOne()
    @JoinColumn(name="currentendpointstatus_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private EndPointStatus currentStatus;

    public EndPoint(){

    }

    public EndPoint(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public void setEndPointStatusList(List <EndPointStatus> endPointStatusList) {
        this.endPointStatusList = endPointStatusList;
        this.currentStatus = endPointStatusList.get((endPointStatusList.size()-1));
    }

    public List<EndPointStatus> getEndPointStatusList() {
        return this.endPointStatusList;
    }

    public void addEndPointStatus(EndPointStatus endPointStatus){
        this.endPointStatusList.add(endPointStatus);
        //endPointStatusList.add(endPointStatus);
        endPointStatus.setEndPoint(this);
        this.currentStatus = endPointStatus;
    }

    @Override
    public String toString() {
        return "EndPoint{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", endPointStatusList=" + endPointStatusList +
                ", currentStatus=" + currentStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndPoint)) return false;
        EndPoint endPoint = (EndPoint) o;
        return id == endPoint.id &&
                url.equals(endPoint.url) &&
                Objects.equals(endPointStatusList, endPoint.endPointStatusList) &&
                Objects.equals(currentStatus, endPoint.currentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, endPointStatusList, currentStatus);
    }
}

