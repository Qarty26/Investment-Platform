package Persistence;

import Model.Platforms.Exchange;
import Exceptions.InvalidDataException;

import java.util.ArrayList;

public interface GenericRepository<T> {

    public void add(T entity) throws InvalidDataException;

    public T get(int id) throws InvalidDataException;
    public ArrayList<T> getAll();
    public void update(T entity) throws InvalidDataException;

    public void delete(T entity) throws InvalidDataException;

    public int getSize();

}