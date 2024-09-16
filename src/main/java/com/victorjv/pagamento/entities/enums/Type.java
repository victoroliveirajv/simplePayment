package com.victorjv.pagamento.entities.enums;


public enum Type {

    COMMON (1),
    LOGIST(2);

    private final int cod;

    private Type(int cod){
        this.cod = cod;
    }

    public int getCod(){
        return cod;
    }

    public static Type getType(int cod){
        for (Type t : Type.values()){
            if (t.getCod() == cod){
                return t;
            }
        }
        throw new RuntimeException("Tipo de conta inexistente");
    }
}
