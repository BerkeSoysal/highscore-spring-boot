package com.example.leadershipranking.models;

import com.example.leadershipranking.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.UUID;

@Entity
public class UserProfile
{
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column
    private String displayName;

    @Transient
    private Score score;

    @Column
    private Long ranking;

    @Column
    private String countryCode;

    public UserProfile() {

    }

    public UserProfile(String displayName, String countryCode) {
        this.uuid = UUID.randomUUID();
        this.displayName = displayName;
        this.countryCode = countryCode;
        this.score = new Score(uuid,0L,0D);
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

    public Score getScore()
    {
        return this.score;
    }
}