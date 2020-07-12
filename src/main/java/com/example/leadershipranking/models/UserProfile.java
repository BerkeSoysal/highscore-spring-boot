package com.example.leadershipranking.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.UUID;

@Entity(name = "user_profile")
public class UserProfile implements Serializable
{
    @Id
    @Column(name = "user_id")
    private UUID uuid;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "ranking")
    private Long ranking;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "points")
    private Long points;

    public UserProfile() {

    }

    public UserProfile(String displayName, String countryCode)
    {
        this.uuid = UUID.randomUUID();
        this.displayName = displayName;
        this.countryCode = countryCode;
        this.points = 0L;
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

    public Long getRanking()
    {
        return ranking;
    }

    public void setRanking(Long ranking)
    {
        this.ranking = ranking;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public Long getPoints()
    {
        return points;
    }

    public void setPoints(Long points)
    {
        this.points = points;
    }

    @Override
    public String toString()
    {
        return "UserProfile{" +
                "uuid=" + uuid +
                ", displayName='" + displayName + '\'' +
                ", ranking=" + ranking +
                ", countryCode='" + countryCode + '\'' +
                ", points=" + points +
                '}';
    }
}
