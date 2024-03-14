package com.elie309.reservation.models;

import java.time.LocalDateTime;

public class Reservation {

    private long id;

    private Client client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String service;
    private Status status;

    public Reservation(long id, Client client, LocalDateTime startTime, LocalDateTime endTime, String service, Status status) {
        this.id = id;
        this.client = client;
        this.startTime = startTime;
        this.endTime = endTime;
        this.service = service;
        this.status = status;
    }

    //#region Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    //#endregion

    @Override
    public String toString() {
        return "Reservation{" +
                ", client=" + client +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", service='" + service + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
