package com.example.leadershipranking.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "score")
public class Score
{
	@Id
	@GeneratedValue
	@Column(name = "score_id")
	private UUID id;

	@Column(name = "user_id")
	private UUID userId;

	@Column(name = "timestamp")
	private Long timestamp;

	@Column(name = "score_worth")
	private Double scoreWorth;

	public Score() {

	}

	public Score(UUID userId, Long timestamp, Double scoreWorth){
		this.id = UUID.randomUUID();
		this.userId = userId;
		this.timestamp = timestamp;
		this.scoreWorth = scoreWorth;
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public UUID getUserId()
	{
		return userId;
	}

	public void setUserId(UUID userId)
	{
		this.userId = userId;
	}

	public Long getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Long timestamp)
	{
		this.timestamp = timestamp;
	}

	public double getScoreWorth()
	{
		return scoreWorth;
	}

	public void setScoreWorth(Double scoreWorth)
	{
		this.scoreWorth = scoreWorth;
	}

	@Override
	public String toString()
	{
		return "Score{" +
				"id=" + id +
				", userId=" + userId +
				", timestamp=" + timestamp +
				", scoreWorth=" + scoreWorth +
				'}';
	}
}
