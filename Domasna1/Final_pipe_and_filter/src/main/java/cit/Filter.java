package cit;

public interface Filter<T> {
    T execute(T input);
}
