import akka.actor.AbstractActor;
import akka.actor.Props;

public class WordCounterActor extends AbstractActor {

    public static Props props(String text) {
        return Props.create(WordCounterActor.class);
    }

    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals("printit", p -> {
                    System.out.println("The address of this actor is: " + getSelf());
                })
                .build();
    }
}
