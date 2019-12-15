package org.jxls.examples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.jxls.common.Context;
import org.jxls.entity.Employee;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupingDemo {
    private static final Logger logger = LoggerFactory.getLogger(GroupingDemo.class);

    @Test
    public void test() throws ParseException, IOException {
        logger.info("Running Grouping demo");
        List<Employee> employees = _generateSampleEmployeeData();
        try (InputStream is = GroupingDemo.class.getResourceAsStream("grouping_template.xlsx")) {
            try (OutputStream os = new FileOutputStream("target/grouping_output.xlsx")) {
                Context context = new Context();
                context.putVar("employees", employees);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }
    }

    private List<Employee> _generateSampleEmployeeData() throws ParseException {
        List<Employee> employees = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd", Locale.US);
        employees.add(new Employee("Elsa", dateFormat.parse("1970-Jul-10"), 1500, 0.15));
        employees.add(new Employee("Oleg", dateFormat.parse("1973-Apr-30"), 2300, 0.25));
        employees.add(new Employee("John", dateFormat.parse("1970-Jul-10"), 3500, 0.10));
        employees.add(new Employee("Neil", dateFormat.parse("1975-Oct-05"), 2500, 0.00));
        employees.add(new Employee("Maria", dateFormat.parse("1978-Jan-07"), 1700, 0.15));
        employees.add(new Employee("John", dateFormat.parse("1969-May-30"), 2800, 0.20));
        employees.add(new Employee("Oleg", dateFormat.parse("1988-Apr-30"), 1500, 0.15));
        employees.add(new Employee("Maria", dateFormat.parse("1970-Jul-10"), 3000, 0.10));
        employees.add(new Employee("John", dateFormat.parse("1973-Apr-30"), 1000, 0.05));
        return employees;
    }
}
