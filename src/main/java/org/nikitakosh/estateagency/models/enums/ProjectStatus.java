package org.nikitakosh.estateagency.models.enums;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    IN_PLANNING("В ПЛАНИРОВАНИИ"),
    UNDER_CONSTRUCTION("В РАЗРАБОТКЕ"),
    COMPLETED("ЗАВЕРШЕНО");
    private final String status;
    ProjectStatus(String s) {
        status = s;
    }

}
