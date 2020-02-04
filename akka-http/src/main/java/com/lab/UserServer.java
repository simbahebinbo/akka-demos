package com.lab;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.util.Timeout;
import com.lab.UserMessages.ActionPerformed;
import com.lab.UserMessages.CreateUserMessage;
import com.lab.UserMessages.GetUserMessage;
import scala.concurrent.duration.Duration;

import static akka.http.javadsl.server.PathMatchers.longSegment;
import static akka.http.javadsl.server.PathMatchers.segment;

class UserServer extends HttpApp {

  private final ActorRef userActor;

  Timeout timeout = new Timeout(Duration.create(5, TimeUnit.SECONDS));

  UserServer(ActorRef userActor) {
    this.userActor = userActor;
  }

  @Override
  public Route routes() {
    return path("users", this::postUser)
        .orElse(path(segment("users").slash(longSegment()), id ->
            route(getUser(id))));
  }

  private Route getUser(Long id) {
    return get(() -> {
      CompletionStage<Optional<User>> user = PatternsCS.ask(userActor, new GetUserMessage(id), timeout)
          .thenApply(obj -> (Optional<User>) obj);

      return onSuccess(() -> user, performed -> {
        if (performed.isPresent()) {
          return complete(StatusCodes.OK, performed.get(), Jackson.marshaller());
        } else {
          return complete(StatusCodes.NOT_FOUND);
        }
      });
    });
  }

  private Route postUser() {
    return route(post(() -> entity(Jackson.unmarshaller(User.class), user -> {
      CompletionStage<ActionPerformed> userCreated = PatternsCS.ask(userActor, new CreateUserMessage(user), timeout)
          .thenApply(obj -> (ActionPerformed) obj);

      return onSuccess(() -> userCreated, performed -> complete(StatusCodes.CREATED, performed, Jackson.marshaller()));
    })));
  }
}
