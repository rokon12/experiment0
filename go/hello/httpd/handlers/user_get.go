package handlers

import (
	"github.com/masterdevskills/hello/httpd/data"
	"go.uber.org/zap"
	"net/http"
	"strconv"
	"time"
)

type Users struct {
	l *zap.Logger
}

func NewUsers(l *zap.Logger) *Users {
	return &Users{l}
}

func (u *Users) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	u.l.Info("serving users")
	u.GetUsers(rw, r)
}

func (u *Users) GetUsers(rw http.ResponseWriter, r *http.Request) {
	param := r.URL.Query().Get("delay")
	if param == "" {
		rw.Write([]byte("Param `delay` is required"))
		return
	}
	u.findAllUsers(rw, param)
}

func (u *Users) findAllUsers(rw http.ResponseWriter, param string) {
	delay, _ := strconv.ParseInt(param, 10, 32)
	time.Sleep(time.Duration(delay) * time.Millisecond)

	users := data.GetUsers()
	err := users.ToJSON(rw)

	if err != nil {
		http.Error(rw, "Error converting to json", http.StatusInternalServerError)
	}
}
