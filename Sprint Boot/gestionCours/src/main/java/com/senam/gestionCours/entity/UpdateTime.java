package com.senam.gestionCours.entity;

import java.time.Instant;

public class UpdateTime {
    public UpdateTime() {
    }

    public Instant getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Instant dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Instant getDateModifier() {
        return dateModifier;
    }

    public void setDateModifier(Instant dateModifier) {
        this.dateModifier = dateModifier;
    }

    private Instant dateAdd;
    private Instant dateModifier;
}
