package ru.epam.javacore.homework20200113.reporting;

import ru.epam.javacore.homework20200113.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
