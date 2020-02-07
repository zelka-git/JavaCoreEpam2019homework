package ru.epam.javacore.homework20200205.reporting;

import ru.epam.javacore.homework20200205.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
