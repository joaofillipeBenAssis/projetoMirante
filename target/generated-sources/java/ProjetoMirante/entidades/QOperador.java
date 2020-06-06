package ProjetoMirante.entidades;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOperador is a Querydsl query type for Operador
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOperador extends EntityPathBase<Operador> {

    private static final long serialVersionUID = 1970722120L;

    public static final QOperador operador = new QOperador("operador");

    public final BooleanPath ativo = createBoolean("ativo");

    public final DatePath<java.util.Date> dataCadastro = createDate("dataCadastro", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath login = createString("login");

    public final StringPath nome = createString("nome");

    public final StringPath perfil = createString("perfil");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath senha = createString("senha");

    public QOperador(String variable) {
        super(Operador.class, forVariable(variable));
    }

    public QOperador(Path<? extends Operador> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperador(PathMetadata<?> metadata) {
        super(Operador.class, metadata);
    }

}

