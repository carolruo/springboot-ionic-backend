package com.carolruo.projeto.domain.enums;

public enum CustomerType {

    INDIVIDUAL_PERSON(1, "Pessoa física"),
    LEGAL_PERSON(2, "Pessoa Jurídica");

    private int id;
    private String description;

    CustomerType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static CustomerType toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        //Percorrer todos os valores possíveis dentro do meu enum
        for (CustomerType c : CustomerType.values()) {
            if (id.equals(c.getId())) return c;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
