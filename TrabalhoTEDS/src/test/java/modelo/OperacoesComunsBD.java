package modelo;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import com.ninja_squad.dbsetup.generator.ValueGenerators;
import com.ninja_squad.dbsetup.operation.Operation;


public class OperacoesComunsBD {

    public static final Operation LIMPA_TUDO 
            = deleteAllFrom("bebida");
    
//    public static final Operation RESET_AUTOINCREMENT_MYSQL 
//            = sql("ALTER TABLE bebida AUTO_INCREMENT = 1");
    
//    public static final Operation RESET_AUTOINCREMENT_H2 
//            = sql("ALTER TABLE bebida ALTER COLUMN codigo RESTART WITH 1");
    
    public static final Operation INSERE_DADOS_BASICOS 
            = sequenceOf(
                    insertInto("bebida")
                            .columns("codigo", "nome", "preco", "descricao")
                            .values("2203", "bebida_test3", "20.00", "bebida_test3")
                            .values("2204", "bebida_test4", "20.00", "bebida_test4")
                            .values("2205", "bebida_test5", "20.00", "bebida_test5")
                            .build());
    
    public static final Operation INSERE_DADOS_BASICOS_IDS_ESPECIFICOS 
            = sequenceOf(
                    insertInto("bebida")
                    .withGeneratedValue("codigo",
                        ValueGenerators.sequence().startingAt(1).incrementingBy(1))
                            .columns("codigo", "nome", "preco", "descricao")
                            .values("2203", "bebida_test3", "20.00", "bebida_test3")
                            .values("2204", "bebida_test4", "20.00", "bebida_test4")
                            .values("2205", "bebida_test5", "20.00", "bebida_test5")
                            .build());
    
    public static final Operation INSERE_DADOS_REPETIDOS 
            = sequenceOf(
                    insertInto("bebida")
                            .withGeneratedValue("codigo", 
                                    ValueGenerators.sequence().startingAt(1).incrementingBy(1))
                            .withGeneratedValue("nome", 
                                    ValueGenerators.stringSequence("nome").startingAt(1))
                            .withGeneratedValue("preco", 
                                    ValueGenerators.sequence().startingAt(1).incrementingBy(1))
                            .withGeneratedValue("descricao", 
                                    ValueGenerators.stringSequence("email").startingAt(1))
                            .columns("telefone").repeatingValues("2323232323")
                            .times(10)
                            .build());

    private OperacoesComunsBD() {
    }
}

