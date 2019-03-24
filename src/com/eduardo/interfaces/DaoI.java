package com.eduardo.interfaces;

import java.util.List;

public interface DaoI<E> {
	public boolean cadastrar(E obj);
	public boolean alterar(E obj);
	public boolean deletar(E obj);
	public List<E> pesquisar();
}
