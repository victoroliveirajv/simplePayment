package com.victorjv.pagamento.entities.enums;

public enum TIPO {

    COMUM (1),
    LOJISTA (2);

    private final int cod;

    private TIPO(int cod){
        this.cod = cod;
    }

    public int getCod(){
        return cod;
    }


    public static TIPO getTipo(int cod){

        for (TIPO t : TIPO.values()){

            if (t.getCod() == cod){

                return t;
            }
        }

        throw new RuntimeException("Tipo de conta inexistente");
    }
}
