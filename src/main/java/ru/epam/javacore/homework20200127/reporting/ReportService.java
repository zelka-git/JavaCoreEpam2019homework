package ru.epam.javacore.homework20200127.reporting;

import ru.epam.javacore.homework20200127.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
