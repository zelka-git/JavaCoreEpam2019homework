package ru.epam.javacore.homework20200207.reporting;

import ru.epam.javacore.homework20200207.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
