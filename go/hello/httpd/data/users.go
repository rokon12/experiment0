package data

import (
	"encoding/json"
	"github.com/google/uuid"
	"io"
	"time"
)

type User struct {
	Id            string    `json:"id"`
	Name          string    `json:"name"`
	Email         string    `json:"email"`
	Verified      bool      `json:"verified"`
	CreatedAt     time.Time `json:"created_at"`
	LastModified  time.Time `json:"last_modified"`
	TwitterHandle string    `json:"twitter_handle"`
}

func (u *Users) ToJSON(w io.Writer) error {
	e := json.NewEncoder(w)

	return e.Encode(u)
}

type Users []*User

func GetUsers() Users {

	return users
}

var users = []*User{
	{uuid.New().String(), "Bazlur Rahman", "bazlur@xyz.org", true, time.Now(), time.Now(), "@bazlur_rahman"},
	{uuid.New().String(), "James Gosling", "james@xyz.org", true, time.Now(), time.Now(), "@errcraft"},
	{uuid.New().String(), "Barack Obama", "barack@xyz.org", true, time.Now(), time.Now(), "@BarackObama"},
}
