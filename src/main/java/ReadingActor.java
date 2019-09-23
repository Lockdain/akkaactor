import akka.actor.Props;

public class ReadingActor {
    private String text;

    public static Props props(String text) {
        return Props.create(ReadingActor.class, text);
    }
}
