package main.homework20191227.storage.print;

import main.homework20191227.common.business.exception.checked.PrintStorageException;

public interface StoragePrint {
    void printStorage() throws PrintStorageException;
}
