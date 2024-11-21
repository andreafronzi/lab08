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
import it.unibo.deathnote.impl.DeathNoteImplementation.DeathAttributes;

class TestDeathNote {

    private final int INVALID_RULE_NUMBER = 0;
    private final String DEFAULT_NAME = "AndreaFronzi"; //ci va inserito qualcosa
    private final String ANOTHER_NAME = "MarcoAresu";
    private final String EMPTY_NAME = "";
    //private final String DEFAULT_DEATH_CAUSE = "Heart Attack";
    private final String KART_DEATH_CAUSE = "Karting Accident";
    private final String FLY_DEATH_CAUSE = "Fly Accident";
    private final String DEAFAULT_DEATH_DETAILS = "";
    private final String CASUAL_DEATH_DETAILS = "ran for too long";

    private DeathNote note;
    private DeathNote note2;
    private DeathNote note3;
    private DeathNote note4;

    @BeforeEach
    void setUp(){
        note = new DeathNoteImplementation();
        note2 = new DeathNoteImplementation();
        note3 = new DeathNoteImplementation();
        note4 = new DeathNoteImplementation();
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
            note2.writeName(DEFAULT_NAME);
            assertEquals(note2.getDeathCause(DEFAULT_NAME), DeathNoteImplementation.DeathAttributes.DEFAULT_DEATH_CAUSE);
            note2.writeName(ANOTHER_NAME);
            assertTrue(note2.writeDeathCause(KART_DEATH_CAUSE));
            assertEquals(note2.getDeathCause(ANOTHER_NAME), KART_DEATH_CAUSE);
            final long initialTime = System.currentTimeMillis();
            while((System.currentTimeMillis() - initialTime) < 100); 
            assertFalse(note2.writeDeathCause(FLY_DEATH_CAUSE));
            assertNotEquals(note2.getDeathCause(ANOTHER_NAME), FLY_DEATH_CAUSE);
    }
    }

    @Test
    void TestDeathDetails() throws InterruptedException{
        try{
            note3.writeDetails(DEAFAULT_DEATH_DETAILS);
        } catch (IllegalStateException e){
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isEmpty());
            assertFalse(e.getMessage().isBlank());
            note3.writeName(DEFAULT_NAME);
            assertTrue(note3.getDeathDetails(DEFAULT_NAME).isEmpty());
            note3.writeDetails(CASUAL_DEATH_DETAILS);
            assertTrue(note3.getDeathDetails(DEFAULT_NAME).equals(CASUAL_DEATH_DETAILS));
            note3.writeName(ANOTHER_NAME);
            Thread.sleep(6100L);
            note3.writeDetails(CASUAL_DEATH_DETAILS);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
            assertFalse(note3.getDeathDetails(ANOTHER_NAME).equals(CASUAL_DEATH_DETAILS));
        }
    }

}