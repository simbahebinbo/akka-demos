package com.github.lansheng228;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Scheduler;
import akka.actor.typed.javadsl.AskPattern;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import com.github.lansheng228.UserRegistry.User;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.complete;
import static akka.http.javadsl.server.Directives.concat;
import static akka.http.javadsl.server.Directives.delete;
import static akka.http.javadsl.server.Directives.entity;
import static akka.http.javadsl.server.Directives.get;
import static akka.http.javadsl.server.Directives.onSuccess;
import static akka.http.javadsl.server.Directives.path;
import static akka.http.javadsl.server.Directives.pathEnd;
import static akka.http.javadsl.server.Directives.pathPrefix;
import static akka.http.javadsl.server.Directives.post;
import static akka.http.javadsl.server.Directives.rejectEmptyResponse;

/**
 * Routes can be defined in separated classes like shown in here
 */

@Slf4j
public class UserRoutes {
    private final ActorRef<UserRegistry.Command> userRegistryActor;
    private final Duration askTimeout;
    private final Scheduler scheduler;

    public UserRoutes(ActorSystem<?> system, ActorRef<UserRegistry.Command> userRegistryActor) {
        this.userRegistryActor = userRegistryActor;
        scheduler = system.scheduler();
        askTimeout = system.settings().config().getDuration("my-app.routes.ask-timeout");
    }

    private CompletionStage<UserRegistry.GetUserResponse> getUser(String name) {
        return AskPattern.ask(userRegistryActor, ref -> new UserRegistry.GetUser(name, ref), askTimeout, scheduler);
    }

    private CompletionStage<UserRegistry.ActionPerformed> deleteUser(String name) {
        return AskPattern.ask(userRegistryActor, ref -> new UserRegistry.DeleteUser(name, ref), askTimeout, scheduler);
    }

    private CompletionStage<UserRegistry.Users> getUsers() {
        return AskPattern.ask(userRegistryActor, UserRegistry.GetUsers::new, askTimeout, scheduler);
    }

    private CompletionStage<UserRegistry.ActionPerformed> createUser(User user) {
        return AskPattern.ask(userRegistryActor, ref -> new UserRegistry.CreateUser(user, ref), askTimeout, scheduler);
    }

    /**
     * This method creates one route (of possibly many more that will be part of your Web App)
     */
    public Route userRoutes() {
        return pathPrefix("users", () ->
                concat(
                        pathEnd(() ->
                                concat(
                                        get(() ->
                                                onSuccess(getUsers(),
                                                        users -> complete(StatusCodes.OK, users, Jackson.marshaller())
                                                )
                                        ),
                                        post(() ->
                                                entity(
                                                        Jackson.unmarshaller(User.class),
                                                        user ->
                                                                onSuccess(createUser(user), performed -> {
                                                                    log.info("Create result: {}", performed.description);
                                                                    return complete(StatusCodes.CREATED, performed, Jackson.marshaller());
                                                                })
                                                )
                                        )
                                )
                        ),

                        path(PathMatchers.segment(), (String name) ->
                                concat(
                                        get(() ->
                                                rejectEmptyResponse(() ->
                                                        onSuccess(getUser(name), performed ->
                                                                complete(StatusCodes.OK, performed.maybeUser, Jackson.marshaller())
                                                        )
                                                )
                                        ),
                                        delete(() ->
                                                onSuccess(deleteUser(name), performed -> {
                                                            log.info("Delete result: {}", performed.description);
                                                            return complete(StatusCodes.OK, performed, Jackson.marshaller());
                                                        }
                                                )
                                        )
                                )
                        )
                )
        );
    }
}


