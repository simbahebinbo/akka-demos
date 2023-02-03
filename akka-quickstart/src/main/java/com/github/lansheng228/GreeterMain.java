package com.github.lansheng228;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.github.lansheng228.GreeterMain.Start;
import lombok.AllArgsConstructor;

public class GreeterMain extends AbstractBehavior<Start> {

    private final ActorRef<Greeter.Greet> greeter;

    private GreeterMain(ActorContext<Start> context) {
        super(context);
        greeter = context.spawn(Greeter.create(), "greeter");
    }

    public static Behavior<Start> create() {
        return Behaviors.setup(GreeterMain::new);
    }

    @Override
    public Receive<Start> createReceive() {
        return newReceiveBuilder().onMessage(Start.class, this::onStart).build();
    }

    private Behavior<Start> onStart(Start command) {
        ActorRef<Greeter.Greeted> replyTo =
                getContext().spawn(GreeterBot.create(3), command.name);
        greeter.tell(new Greeter.Greet(command.name, replyTo));
        return this;
    }

    @AllArgsConstructor
    public static class Start {

        public final String name;
    }
}
