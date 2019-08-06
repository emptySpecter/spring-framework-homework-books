package ru.otus.spring.shell;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import java.util.List;
import java.util.Map;

public class TableHelper {

    public static <T> String getFormattedTableList(List<T> objects, int width) {
        TableModel model = getTableModelList(objects);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        return tableBuilder.build().render(width);
    }

    public static <T> String getFormattedTableBean(T object, int width) {
        TableModel model = getTableModelBean(object);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        return tableBuilder.build().render(width);
    }

    private static <T> TableModel getTableModelList(List<T> objects) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(objects.get(0), new TypeReference<Map<String, Object>>() {
        });
        int objSize = 0;
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                objSize += ((Map) value).size();
            } else {
                objSize++;
            }
        }
        Object[][] data = new Object[objects.size() + 1][objSize];
        int k = 0;
        for (Map.Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                for (Map.Entry subentry : ((Map<String, Object>) value).entrySet()) {
                    data[0][k++] = entry.getKey() + "." + subentry.getKey();
                }
            } else {
                data[0][k++] = entry.getKey();
            }
        }
        int j = 1;
        for (Object obj : objects) {
            map = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {
            });
            int i = 0;
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Map) {
                    for (Map.Entry subentry : ((Map<String, Object>) value).entrySet()) {
                        data[j][i++] = subentry.getValue();
                    }
                } else {
                    data[j][i++] = entry.getValue();
                }
            }
            j++;
        }
        return new ArrayTableModel(data);
    }

    private static <T> TableModel getTableModelBean(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
        Object[][] data = new Object[map.size()][2];
        int j = 0;
        for (Map.Entry entry : map.entrySet()) {
            data[j][0] = entry.getKey();
            data[j][1] = entry.getValue();
            j += 1;
        }
        return new ArrayTableModel(data);
    }

}
