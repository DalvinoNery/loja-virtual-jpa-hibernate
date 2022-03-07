package modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    @Column(length = 64)
    private CategoriaId id;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "Teste");
    }

    public String getNome() {
        return this.id.getNome();
    }
    
}

