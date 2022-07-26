package service;

import dao.VMAuditDao;
import dao.VMPersistenceException;

public class AuditDaoStubImpl implements VMAuditDao {
    @Override
    public void writeAuditEntry(String entry){
        // do nothing
    }
}
