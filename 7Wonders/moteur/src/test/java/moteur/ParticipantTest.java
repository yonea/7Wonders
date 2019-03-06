package moteur;

import com.corundumstudio.socketio.SocketIOClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {
    Participant p1;
    SocketIOClient sockettest;
    @BeforeEach
    public void setUp(){

        p1 = new Participant(sockettest);

    }
    @Test
    public void testpourgit(){

    }

}