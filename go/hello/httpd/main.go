package main

import (
	"context"
	"github.com/masterdevskills/hello/httpd/handlers"
	"go.uber.org/zap"
	"log"
	"net/http"
	"os"
	"os/signal"
	"time"
)

func main() {
	logger, _ := zap.NewProduction()
	ph := handlers.NewUsers(logger)

	sm := http.NewServeMux()
	sm.Handle("/users", ph)

	s := &http.Server{
		Addr:         ":3000",
		Handler:      sm,
		IdleTimeout:  120 * time.Second,
		ReadTimeout:  27 * time.Second,
		WriteTimeout: 28 * time.Second,
	}

	go func() {
		log.Fatal(s.ListenAndServe())
	}()

	sigChan := make(chan os.Signal)
	signal.Notify(sigChan, os.Interrupt)
	signal.Notify(sigChan, os.Kill)

	sig := <-sigChan
	logger.Info("Receive terminate, shutting down : " + sig.String())

	tc, _ := context.WithTimeout(context.Background(), 30*time.Second)
	s.Shutdown(tc)
}
