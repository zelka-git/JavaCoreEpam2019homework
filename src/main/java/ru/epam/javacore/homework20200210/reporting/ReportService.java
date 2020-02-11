package ru.epam.javacore.homework20200210.reporting;

import ru.epam.javacore.homework20200210.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
