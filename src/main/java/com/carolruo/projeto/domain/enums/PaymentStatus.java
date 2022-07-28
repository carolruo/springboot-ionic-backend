package com.carolruo.projeto.domain.enums;

public enum PaymentStatus {

    PENDING(1, "Pendente"),
    DISCHARGED(2, "Quitado"),
    CANCELED(3, "Cancelado");

    private int id;
    private String description;

    PaymentStatus(int id, String description) {

        this.id = id;
        this.description = description;
    }

    public int getId() {

        return id;
    }

    public String getDescription() {

        return description;
    }

    public static PaymentStatus toEnum(Integer id) {

        if (id == null) {
            return null;
        }

        //Percorrer todos os valores possíveis dentro do meu enum
        for (PaymentStatus c : PaymentStatus.values()) {
            if (id.equals(c.getId())) return c;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
