package com.github.lansheng228;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GreeterBot extends AbstractBehavior<Greeter.Greeted> {

    private final int max;
    private int greetingCounter;

    private GreeterBot(ActorContext<Greeter.Greeted> context, int max) {
        super(context);
        this.max = max;
    }

    public static Behavior<Greeter.Greeted> create(int max) {
        return Behaviors.setup(context -> new GreeterBot(context, max));
    }

    @Override
    public Receive<Greeter.Greeted> createReceive() {
        return newReceiveBuilder().onMessage(Greeter.Greeted.class, this::onGreeted).build();
    }

    private Behavior<Greeter.Greeted> onGreeted(Greeter.Greeted message) {
        greetingCounter++;
        log.info("Greeting {} for {}", greetingCounter, message.whom);
        if (greetingCounter == max) {
            return Behaviors.stopped();
        } else {
            message.from.tell(new Greeter.Greet(message.whom, getContext().getSelf()));
            return this;
        }
    }
}
