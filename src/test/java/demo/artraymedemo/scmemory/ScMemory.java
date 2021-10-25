package demo.artraymedemo.scmemory;

/**
 * @author artrayme
 * 10/22/21
 */
public interface ScMemory {
    boolean createScElement(ScElement element);
    boolean deleteScElement(ScElement element);
    boolean checkIsExist(ScElement element);

    void searchByTemplate();
    void generateByTemplate();

    void findSystemIdentifier();
    void resolveSystemIdentifier();

    void subscribeOnEvent();
    void unsubscribeOnEvent();

    void getScLinkContent();
    void setScLinkContent();
}
