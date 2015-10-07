package caso1.actores;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Herrero extends UntypedActor {
    public enum Mensaje {
        CREAR_ESPADA
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef minero;


    @Override
    public void preStart() {
        minero = getContext().actorOf(Props.create(Minero.class), "minero");
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Herrero] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.CREAR_ESPADA) {
            minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
        }
    }
}
