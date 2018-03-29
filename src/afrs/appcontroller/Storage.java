package afrs.appcontroller;

/**
 * Interface that all storage objects inherit from
 */
public interface Storage {

    Object getInstance(Object ID);
    void setupMap();

}
