package caso2.actores;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Herrero extends UntypedActor {
    public enum Mensaje {
        CREAR_ESPADA,
        MATERIALES
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private static final long TIEMPO_CREACION_ESPADA = 2000;
    private ActorRef espadachin;
    private ActorRef minero;


    @Override
    public void preStart() {
        minero = getContext().actorOf(Props.create(Minero.class), "minero");
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Herrero] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.CREAR_ESPADA) {
            espadachin = getSender();
            minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
        } else if (o == Mensaje.MATERIALES) {
            log.info("[Herrero] está creando espada...");
            crearEspada();
            log.info("[Herrero] ha creado espada.");
            espadachin.tell(Espadachin.Mensaje.ESPADA_NUEVA, getSelf());
        } else {
            unhandled(o);
        }
    }

    private void crearEspada() throws InterruptedException {
        Thread.sleep(TIEMPO_CREACION_ESPADA);
    }

    @Override
    public void unhandled(Object message) {
        log.info("[Herrero] no sabe qué hacer ante el mensaje: \"{}\".", message);
    }
}
