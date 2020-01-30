package ru.epam.javacore.homework20200129.reporting;

import ru.epam.javacore.homework20200129.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
