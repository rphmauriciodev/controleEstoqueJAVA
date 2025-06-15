package controleEstoque.dao.interfaces;

import java.util.List;

public interface IbaseDAO<T> {
    List<T> listarTodos();
    void inserir(T item);
    void atualizar(T item);
    void remover(int id);
}
