package com.victorjv.pagamento.entities.enums;


public enum TYPE {

    COMMON (1),
    LOGIST(2);

    private final int cod;

    private TYPE(int cod){
        this.cod = cod;
    }

    public int getCod(){
        return cod;
    }

    public static TYPE getTipo(int cod){
        for (TYPE t : TYPE.values()){
            if (t.getCod() == cod){
                return t;
            }
        }
        throw new RuntimeException("Tipo de conta inexistente");
    }
}
