package Persistence;

import Model.Platforms.Exchange;
import Exceptions.InvalidDataException;

public interface GenericRepository<T> {

    public void add(T entity) throws InvalidDataException;

    public T get(int id) throws InvalidDataException;

    public void update(T entity) throws InvalidDataException;

    public void delete(T entity) throws InvalidDataException;

    public int getSize();

}