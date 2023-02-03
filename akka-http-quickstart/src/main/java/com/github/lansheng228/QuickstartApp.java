package com.github.lansheng228;

import akka.NotUsed;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.server.Route;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletionStage;

@Slf4j
public class QuickstartApp {
    static void startHttpServer(Route route, ActorSystem<?> system) {
        String host = "0.0.0.0";
        int port = 9999;
        CompletionStage<ServerBinding> futureBinding =
                Http.get(system).newServerAt(host, port).bind(route);

        futureBinding.whenComplete((binding, exception) -> {
            if (binding != null) {
                InetSocketAddress address = binding.localAddress();
                log.info("Server listen on {}:{}", host, port);
                system.log().info("Server online at http://{}:{}/",
                        address.getHostName(),
                        address.getPort());
            } else {
                system.log().error("Failed to bind HTTP endpoint, terminating system", exception);
                system.terminate();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Behavior<NotUsed> rootBehavior = Behaviors.setup(context -> {
            ActorRef<UserRegistry.Command> userRegistryActor =
                    context.spawn(UserRegistry.create(), "UserRegistry");

            UserRoutes userRoutes = new UserRoutes(context.getSystem(), userRegistryActor);
            startHttpServer(userRoutes.userRoutes(), context.getSystem());

            return Behaviors.empty();
        });

        ActorSystem.create(rootBehavior, "HelloAkkaHttpServer");
    }
}


