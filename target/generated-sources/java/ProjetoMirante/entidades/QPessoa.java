package ProjetoMirante.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPessoa is a Querydsl query type for Pessoa
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPessoa extends EntityPathBase<Pessoa> {

    private static final long serialVersionUID = -1074031269L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPessoa pessoa = new QPessoa("pessoa");

    public final DatePath<java.util.Date> dataCadastro = createDate("dataCadastro", java.util.Date.class);

    public final DatePath<java.util.Date> dataNascimento = createDate("dataNascimento", java.util.Date.class);

    public final StringPath documento = createString("documento");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final QOperador operador;

    public final StringPath tipoPessoa = createString("tipoPessoa");

    public QPessoa(String variable) {
        this(Pessoa.class, forVariable(variable), INITS);
    }

    public QPessoa(Path<? extends Pessoa> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPessoa(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPessoa(PathMetadata<?> metadata, PathInits inits) {
        this(Pessoa.class, metadata, inits);
    }

    public QPessoa(Class<? extends Pessoa> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.operador = inits.isInitialized("operador") ? new QOperador(forProperty("operador")) : null;
    }

}

