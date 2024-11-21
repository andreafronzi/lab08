package it.unibo.deathnote.impl;

import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote{

    private static final int FIRST_RULE = 1;
    private static final long IN_TIME_DEATH_CAUSE = 40L;
    private static final long IN_TIME_DEATH_DETAIL = 6040L;


    private final Map<String, DeathAttributes> book;
    private String actualName;

    public DeathNoteImplementation(){
        this.book = new HashMap<String, DeathAttributes>();
    }

    @Override
    public String getDeathCause(final String name) {
        if(!isNameWritten(name)){
            final String msg = "Invalid name provides as parameter";
            throw new IllegalArgumentException(msg);
        }
        final DeathAttributes attributes = this.book.get(name);
        return attributes.getCause();
    }

    @Override
    public String getDeathDetails(final String name) {
        if(!isNameWritten(name)){
            throw new NullPointerException();
        }
        final DeathAttributes attributes = this.book.get(name);
        return attributes.getDetails();        
    }

    @Override
    public String getRule(final int ruleNumber) {
        if(ruleNumber < FIRST_RULE || ruleNumber >= DeathNote.RULES.size()){
            final String msg = "Invalid rule index";
            throw new IllegalArgumentException(msg);
        }
        return DeathNote.RULES.get(ruleNumber);
    }

    @Override
    public boolean isNameWritten(final String name) {
        return this.book.containsKey(name);
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if(Objects.isNull(cause) || !isNameWritten(actualName)){
            final String msg = "No name in book or Passed null reference as death cause";
            throw new IllegalStateException(msg);
        }
        final DeathAttributes attributes = this.book.get(this.actualName);
        final long actualTime = System.currentTimeMillis();
        if(actualTime - attributes.timeOfWritingName < IN_TIME_DEATH_CAUSE){
            attributes.setCause(cause);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeDetails(final String details) {
        if(Objects.isNull(details) || !isNameWritten(this.actualName)){
            final String msg = "No name in book or passed null reference as death detail";
            throw new IllegalStateException(msg);
        }
        final DeathAttributes attributes = this.book.get(this.actualName);
        if(System.currentTimeMillis() - attributes.timeOfDeath < IN_TIME_DEATH_DETAIL){
            attributes.setDetails(details);
            return true;
        }
        return false;
    }

    @Override
    public void writeName(final String name) {
        if(Objects.isNull(name)){
            final String msg = "Passed null reference as name";
            throw new NullPointerException(msg);
        }
        this.actualName = name;
        final DeathAttributes attributes = new DeathAttributes();
        this.book.put(name, attributes);
        attributes.timeOfWritingName = System.currentTimeMillis();
    }


    public static final class DeathAttributes{
        
        public static final String DEFAULT_DEATH_CAUSE = "Heart Attack";
        private static final String DEAFAULT_DEATH_DETAILS = "";

        private String cause;
        private String details;
        private long timeOfWritingName;
        private long timeOfDeath;

        
        public DeathAttributes(final String cause, final String details){
            this.cause = cause;
            this.details = details;
            this.timeOfWritingName = System.currentTimeMillis();
            this.timeOfDeath = System.currentTimeMillis();
        }

        public DeathAttributes(){
            this(DEFAULT_DEATH_CAUSE, DEAFAULT_DEATH_DETAILS);
        }

        public String getCause() {
            return this.cause;
        }

        public String getDetails() {
            return this.details;
        }


        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((cause == null) ? 0 : cause.hashCode());
            result = prime * result + ((details == null) ? 0 : details.hashCode());
            return result;
        }

        public void setCause(final String cause) {
            this.cause = cause;
            this.timeOfDeath = System.currentTimeMillis();
        }

        public void setDetails(final String details) {
            this.details = details;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DeathAttributes other = (DeathAttributes) obj;
            if (cause == null) {
                if (other.cause != null)
                    return false;
            } else if (!cause.equals(other.cause))
                return false;
            if (details == null) {
                if (other.details != null)
                    return false;
            } else if (!details.equals(other.details))
                return false;
            return true;
        } 

    }

}