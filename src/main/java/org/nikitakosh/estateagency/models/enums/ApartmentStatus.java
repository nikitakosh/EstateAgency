package org.nikitakosh.estateagency.models.enums;

import lombok.Getter;

@Getter
public enum ApartmentStatus {
    IN_PLANNING("В ПЛАНИРОВАНИИ"),
    UNDER_CONSTRUCTION("В РАЗРАБОТКЕ"),
    COMPLETED("ЗАВЕРШЕНО"),
    SOLD_OUT("ПРОДАНО"),
    RESERVED("ЗАРЕЗЕРВИРОВАНО");

    private final String status;

    ApartmentStatus(String s) {
        status = s;
    }

}
