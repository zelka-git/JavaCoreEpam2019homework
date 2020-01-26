package ru.epam.javacore.homework20200124.reporting;

import ru.epam.javacore.homework20200124.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
