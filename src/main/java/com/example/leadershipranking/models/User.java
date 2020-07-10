package com.example.leadershipranking.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User
{
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column
    private String displayName;

    @Column
    private Double points;

    @Column
    private Long ranking;

    public User() {

    }

    public User(String displayName) {
        this.uuid = UUID.randomUUID();
        this.displayName = displayName;
        this.points = 0D;
    }

    public UUID getUuid()
    {
        return uuid;
    }

    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public Double getPoints()
    {
        return points;
    }

    public void setPoints(Double points)
    {
        this.points = points;
    }

    public Long getRanking()
    {
        return ranking;
    }

    public void setRanking(Long ranking)
    {
        this.ranking = ranking;
    }
}
