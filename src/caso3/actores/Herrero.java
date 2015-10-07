package caso3.actores;


import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import caso3.Main;

import java.util.ArrayList;

public class Herrero extends UntypedActor {
    public enum Mensaje {
        CREAR_ESPADA,
        MATERIALES
    }

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private static final long TIEMPO_CREACION_ESPADA = 2000;
    private ArrayList<ActorRef> espadachines;


    @Override
    public void preStart() {
        espadachines = new ArrayList<>();
    }

    @Override
    public void onReceive(Object o) throws InterruptedException {
        log.info("[Herrero] ha recibido el mensaje: \"{}\".", o);

        if (o == Mensaje.CREAR_ESPADA) {
            espadachines.add(getSender());
            Main.minero.tell(Minero.Mensaje.OBTENER_MATERIALES, getSelf());
        } else if (o == Mensaje.MATERIALES) {
            log.info("[Herrero] está creando espada...");
            crearEspada();
            log.info("[Herrero] ha creado espada.");
            if (!espadachines.isEmpty()) {
                espadachines.get(0).tell(Espadachin.Mensaje.ESPADA_NUEVA, getSelf());
                espadachines.remove(0);
            }
        } else {
            unhandled(o);
        }
    }

    private void crearEspada() throws InterruptedException {
        Thread.sleep(TIEMPO_CREACION_ESPADA);
    }
}
