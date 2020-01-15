package main.homework20200113.storage.print;

import main.homework20200113.common.business.exception.checked.PrintStorageException;

public interface StoragePrint {
    void printStorage() throws PrintStorageException;
}
