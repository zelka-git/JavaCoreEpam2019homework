package main.homework20191230.storage.print;

import main.homework20191230.common.business.exception.checked.PrintStorageException;

public interface StoragePrint {
    void printStorage() throws PrintStorageException;
}
