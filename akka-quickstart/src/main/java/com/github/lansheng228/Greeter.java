package com.github.lansheng228;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Greeter extends AbstractBehavior<Greeter.Greet> {

    private Greeter(ActorContext<Greet> context) {
        super(context);
    }

    public static Behavior<Greet> create() {
        return Behaviors.setup(Greeter::new);
    }

    @Override
    public Receive<Greet> createReceive() {
        return newReceiveBuilder().onMessage(Greet.class, this::onGreet).build();
    }

    private Behavior<Greet> onGreet(Greet command) {
        log.info("Hello {}!", command.whom);
        command.replyTo.tell(new Greeted(command.whom, getContext().getSelf()));
        return this;
    }


    @AllArgsConstructor
    public static final class Greet {

        public final String whom;
        public final ActorRef<Greeted> replyTo;
    }


    @AllArgsConstructor
    @EqualsAndHashCode
    @ToString
    public static final class Greeted {

        public final String whom;
        public final ActorRef<Greet> from;
    }
}

