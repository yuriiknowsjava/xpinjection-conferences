package edu.yuriiknowsjava.xpinjection.conferences.exceptions;

public class EntityDoesNotExist extends RuntimeException {
    public EntityDoesNotExist(String msg) {
        super(msg);
    }
}
