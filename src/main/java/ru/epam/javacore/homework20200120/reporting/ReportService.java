package ru.epam.javacore.homework20200120.reporting;

import ru.epam.javacore.homework20200120.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
