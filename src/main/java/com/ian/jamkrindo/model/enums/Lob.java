package com.ian.jamkrindo.model.enums;

import lombok.Getter;

@Getter
public enum Lob {
    KBG_SURETYSHIP("KBG dan Suretyship"),
    KONSUMTIF("Konsumtif"),
    KUR("KUR"),
    PEN("PEN"),
    PRODUKTIF("Produktif");

    private final String value;

    Lob(String value) {
        this.value = value;
    }

}
