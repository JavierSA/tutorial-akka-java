package caso2.actores;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Espadachin extends UntypedActor {
    public enum Mensaje {
        ESPADA_NUEVA,
        SALUDO
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void preStart() {
        final ActorRef herrero = getContext().actorOf(Props.create(Herrero.class), "herrero");
        herrero.tell(Herrero.Mensaje.CREAR_ESPADA, getSelf());
        herrero.tell(Mensaje.SALUDO, getSelf());
    }

    @Override
    public void onReceive(Object o) {
        log.info("[Espadachin] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.ESPADA_NUEVA) {
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }
}
