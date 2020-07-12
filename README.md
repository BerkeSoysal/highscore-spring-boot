# High Score API

Heroku page : https://limitless-thicket-20437.herokuapp.com/

A REST API that can use to control and view high scores. Each point submission adds to user's previous score. Each submission rearranges the rankings of the users that can be affected by submission.

Used technologies: Spring Boot, JPA, Heroku.

## Rest Endpoints:

### GET /leaderboard

Returns the list of users ordered by their ranks.

### GET /leaderboard/{countryCode}

Returns the list of users belong to the given country ordered by their ranks.

### POST /user/create
Creates a user with 0 points.
Needs JSON Body with displayName and country.

For instance:
`
{
    "display_name" : "user",
    "country_code" : "US"
}
`

### POST /score/submit
Needs Json body with score,timestamp and userid.
`
{
	"score_worth" : 554.0,
	"user_id" : "5743acc8-d951-443d-aed6-44bc3fa5ec4e",
	"timestamp" : 3
}
`
### GET /user/profile/{guid}
Returns the user with id
