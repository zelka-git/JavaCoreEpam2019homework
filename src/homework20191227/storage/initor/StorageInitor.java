package homework20191227.storage.initor;

import homework20191227.common.business.exception.checked.InitStorageException;

import java.io.IOException;

public interface StorageInitor {
    void initStorage() throws Exception;
}
