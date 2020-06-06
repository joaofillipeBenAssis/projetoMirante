package ProjetoMirante.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTelefone is a Querydsl query type for Telefone
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTelefone extends EntityPathBase<Telefone> {

    private static final long serialVersionUID = 1110791070L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTelefone telefone = new QTelefone("telefone");

    public final DatePath<java.util.Date> dataCadastro = createDate("dataCadastro", java.util.Date.class);

    public final StringPath ddd = createString("ddd");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath numero = createString("numero");

    public final QOperador operador;

    public final QPessoa pessoa;

    public final StringPath tipoTelefone = createString("tipoTelefone");

    public QTelefone(String variable) {
        this(Telefone.class, forVariable(variable), INITS);
    }

    public QTelefone(Path<? extends Telefone> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTelefone(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTelefone(PathMetadata<?> metadata, PathInits inits) {
        this(Telefone.class, metadata, inits);
    }

    public QTelefone(Class<? extends Telefone> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.operador = inits.isInitialized("operador") ? new QOperador(forProperty("operador")) : null;
        this.pessoa = inits.isInitialized("pessoa") ? new QPessoa(forProperty("pessoa"), inits.get("pessoa")) : null;
    }

}

