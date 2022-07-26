package dao;

public interface VMAuditDao {
    void writeAuditEntry(String entry) throws VMPersistenceException;
}
