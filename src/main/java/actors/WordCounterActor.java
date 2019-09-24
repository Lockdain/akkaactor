package actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import mail.Message;

public class WordCounterActor extends AbstractActor {

    public static Props props(String text) {
        return Props.create(WordCounterActor.class);
    }

    private static void apply(Message r) {
        System.out.println(r.message);
    }

    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("printit", p -> {
                    System.out.println("The address of this actor is: " + getSelf());
                })
                .match(Message.class, WordCounterActor::apply)
                .build();
    }
}
