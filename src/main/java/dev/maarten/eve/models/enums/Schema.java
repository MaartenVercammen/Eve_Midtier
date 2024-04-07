package dev.maarten.eve.models.enums;

import lombok.Getter;

@Getter
public enum Schema {
    HTTP("http"),
    HTTPS("https");

    public final String value;

    Schema(String value) {
        this.value = value;
    }
}
