package it.unibo.deathnote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Objects;
import java.util.List;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private static final int INVALID_RULE_NUMBER = 0;
    private static final String DEFAULT_NAME = "AndreaFronzi"; //ci va inserito qualcosa
    private final String ANOTHER_NAME = "MarcoAresu";
    private final String EMPTY_NAME = "";
    private final String DEFAULT_DEATH_CAUSE = "Heart Attack";
    private final String KART_DEATH_CAUSE = "Karting Accident";
    private final String FLY_DEATH_CAUSE = "Fly Accident";

    private DeathNote note;
    private DeathNote note2;

    @BeforeEach
    void setUp(){
        note = new DeathNoteImplementation();
        note2 = new DeathNoteImplementation();
    }

    @Test
    void isRuleNumberValid(){
        try{
            this.note.getRule(INVALID_RULE_NUMBER);
        }catch(IllegalArgumentException e){
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testBlankOrEmptyRule(){
        final List<String> rule = DeathNote.RULES;
        for(final String element: rule){
            assertNotNull(element);
            assertFalse(element.isEmpty());
            assertFalse(element.isEmpty());
        }
    }

    @Test
    void testKillHuman(){
        assertFalse(note.isNameWritten(DEFAULT_NAME));
        note.writeName(DEFAULT_NAME);
        assertTrue(note.isNameWritten(DEFAULT_NAME));
        assertFalse(note.isNameWritten(ANOTHER_NAME));
        assertFalse(note.isNameWritten(EMPTY_NAME));
    }

    @Test
    void testWriteNameInTime(){
        try {
            note2.writeDeathCause(DEFAULT_NAME);
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
        }
        note2.writeName(DEFAULT_NAME);
        assertEquals(note.getDeathCause(DEFAULT_NAME), DEFAULT_DEATH_CAUSE);
        note2.writeName(ANOTHER_NAME);
        assertTrue(note2.writeDeathCause(KART_DEATH_CAUSE));
        assertEquals(note2.getDeathCause(ANOTHER_NAME), KART_DEATH_CAUSE);
        final long initialTime = System.currentTimeMillis();
        while((System.currentTimeMillis() - initialTime) < 100); 
        assertFalse(note2.writeDeathCause(FLY_DEATH_CAUSE));
        assertNotEquals(note2.getDeathCause(ANOTHER_NAME), FLY_DEATH_CAUSE);
    }

}