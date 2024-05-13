package org.nikitakosh.estateagency.models.enums;

import lombok.Getter;

@Getter
public enum ApartmentType {
    PRIVATE_HOUSE("ЧАСТНЫЙ ДОМ"), FLAT("КВАРТИРА");
    private final String type;

    ApartmentType(String type) {
        this.type = type;
    }

}
